package com.bill.test.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.netty
 * @Description: TODO
 *          1. Channel 
 *
 *       在Netty里，Channel是通讯的载体，而ChannelHandler负责Channel中的逻辑处理。它是Netty网络通信的主体，由它负责同对端进行网络通信、注册和数据操作等功能。
 *
 *          状态主要包括：打开、关闭、连接
 *          主要的IO操作，读(read)、写(write)、连接(connect)、绑定(bind)。
 *          所有的IO操作都是异步的，调用诸如read,write方法后，并不保证IO操作完成，但会返回一个凭证，在IO操作成功，取消或失败后会记录在该凭证中。
 *      2. ChannelPipeline
 *
 *          可以理解为ChannelHandler的容器：一个Channel包含一个ChannelPipeline，所有ChannelHandler都会注册到ChannelPipeline中，并按顺序组织起来。
 *          channel事件消息在ChannelPipeline中流动和传播，相应的事件能够被ChannelHandler拦截处理、传递、忽略或者终止。
 *
 *          Netty的ChannelPipeline包含两条线路：Upstream和Downstream。Upstream对应上行，接收到的消息、被动的状态改变，都属于Upstream。
 *              Downstream则对应下行，发送的消息、主动的状态改变，都属于Downstream。
 *         3.  ChannelHandler
 *
 *          ChannelHandler负责I/O事件或者I/O操作进行拦截和处理，用户可以通过ChannelHandlerAdapter来选择性的实现自己感兴趣的事件拦截和处理。
 *           由于Channel只负责实际的I/O操作，因此数据的编解码和实际处理都需要通过ChannelHandler进行处理。
 * @date Date : 2019年10月12日 11:26
 */
@Slf4j
public class NettyServer {
    public void bind(int port) throws Exception{
        EventLoopGroup bossGroup = new NioEventLoopGroup(); //bossGroup就是parentGroup，是负责处理TCP/IP连接的
        EventLoopGroup workerGroup = new NioEventLoopGroup(); //workerGroup就是childGroup,是负责处理Channel(通道)的I/O事件

        ServerBootstrap sb = new ServerBootstrap();
        sb.group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .option(ChannelOption.SO_BACKLOG, 128) //初始化服务端可连接队列,指定了队列的大小128
                .childOption(ChannelOption.SO_KEEPALIVE, true) //保持长连接
                .childHandler(new ChannelInitializer<SocketChannel>() {  // 绑定客户端连接时候触发操作
                    @Override
                    protected void initChannel(SocketChannel sh) throws Exception {
                        sh.pipeline()
                                .addLast(new RpcDecoder(RpcRequest.class)) //解码request
                                .addLast(new RpcEncoder(RpcResponse.class)) //编码response
                                .addLast(new ServerHandler()); //使用ServerHandler类来处理接收到的消息
                    }
                });
        //绑定监听端口，调用sync同步阻塞方法等待绑定操作完
        ChannelFuture future = sb.bind(port).sync();

        if (future.isSuccess()) {
            System.out.println("服务端启动成功");
        } else {
            System.out.println("服务端启动失败");
            future.cause().printStackTrace();
            bossGroup.shutdownGracefully(); //关闭线程组
            workerGroup.shutdownGracefully();
        }

        //成功绑定到端口之后,给channel增加一个 管道关闭的监听器并同步阻塞,直到channel关闭,线程才会往下执行,结束进程。
        future.channel().closeFuture().sync();
    }
}
