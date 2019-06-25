package com.bill.test.rocketmq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.rocketmq
 * @Description: TODO
 * @date Date : 2019年06月25日 16:06
 */
public class BrokerServer implements Runnable{
    public static int SERVICE_PORT=9999;

    private final Socket socket;

    public BrokerServer(Socket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
        try (
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out=new PrintWriter(socket.getOutputStream())
            ){
            while(true){
                String str=in.readLine();
                if(str==null){
                    continue;
                }
                System.out.println("接收到原始数据："+str);
                /**CONSUME表示要消费一条消息**/
                if(str.equals("CONSUME")){
                    /**从消息队列中消费一条消息**/
                    String message=Broker.consume();
                    out.println(message);
                    out.flush();
                }else {
                    /**其他情况都表示生产消息放到消息队列中**/
                    Broker.produce(str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        ServerSocket server=new ServerSocket(SERVICE_PORT);
            while (true){
                BrokerServer brokerServer=new BrokerServer(server.accept());
                new Thread(brokerServer).start();
            }
    }
}
