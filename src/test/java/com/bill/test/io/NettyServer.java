package com.bill.test.io;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.io
 * @Description: TODO Netty是一个异步事件驱动的网络应用框架，用于快速开发可维护的高性能服务器和客户端
 * @date Date : 2019年06月21日 15:44
 */
public class NettyServer {
    public static void main(String[] args) {
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        /**boos对应，IOServer.java中的接受新连接线程，主要负责创建新连接**/
        NioEventLoopGroup boos = new NioEventLoopGroup();
        /**worker对应 IOClient.java中的负责读取数据的线程，主要用于读取数据以及业务逻辑处理**/
        NioEventLoopGroup worker = new NioEventLoopGroup();
        serverBootstrap
                .group(boos, worker)
                /**NioServerSocketChannel也是对NIO类型的连接的抽象,对应BIO编程模型中的ServerSocket**/
                .channel(NioServerSocketChannel.class)
                /**NioSocketChannel，这个类呢，就是Netty对NIO类型的连接的抽象,对应BIO编程模型中的Socket**/
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        ch.pipeline().addLast(new StringDecoder());
                        ch.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                            @Override
                            protected void channelRead0(ChannelHandlerContext ctx, String msg) {
                                System.out.println("NettyServer输出："+msg);
                            }
                        });
                    }
                })
                .bind(8000);
        /**
         * 总结：
         * 要启动一个Netty服务端，必须要指定三类属性，分别是线程模型、IO模型、连接读写处理逻辑，
         * 有了这三者，之后在调用bind(8000)，我们就可以在本地绑定一个8000端口启动起来
         */

        bind(serverBootstrap,1000);
    }

    /**
     * 自动绑定递增端口
     * @param serverBootstrap
     * @param port
     */
    private static void bind(final ServerBootstrap serverBootstrap, final int port) {
        serverBootstrap.bind(port).addListener(new GenericFutureListener<Future<? super Void>>() {
            public void operationComplete(Future<? super Void> future) {
                if (future.isSuccess()) {
                    System.out.println("端口[" + port + "]绑定成功!");
                } else {
                    System.err.println("端口[" + port + "]绑定失败!");
                    bind(serverBootstrap, port + 1);
                }
            }
        });
    }
}
