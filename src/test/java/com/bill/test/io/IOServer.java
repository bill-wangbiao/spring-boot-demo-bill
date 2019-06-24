package com.bill.test.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author : wangbiao
 * @version V1.0
 * @Project: spring-boot-demo-bill
 * @Package com.bill.test.io
 * @Description: TODO
 * @date Date : 2019年06月21日 10:34
 */
public class IOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(8000);
        /**接收新连接线程**/
        new Thread(){
            public void run(){
                while (true){
                    try {
                        /**阻塞方法获取新的连接**/
                        Socket socket=serverSocket.accept();
                        /**每一个新的连接都创建一个线程，负责读取数据**/
                        new Thread(){
                            public void run(){
                                byte[] data=new byte[1024];
                                try {
                                    InputStream inputStream = socket.getInputStream();
                                    while(true){
                                        int len;
                                        /**按照字节流方式读取数据**/
                                        while ((len=inputStream.read(data))!=-1){
                                            System.out.println(new String(data,0,len));
                                        }
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }.start();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
