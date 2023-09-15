package com.cuixiaobin.tcps.o2m;

import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket socket = new ServerSocket(8001, 10, InetAddress.getByAddress(new byte[]{127, 0, 0, 1}));

        while (true) {
            Socket accept = socket.accept();
            System.out.println(accept.getRemoteSocketAddress()+"=>"+"上线了");

            Runnable runnable = new Runnable() {
                @SneakyThrows
                @Override
                public void run() {
                    while (true) {

                        try {

                            InputStream inputStream = accept.getInputStream();
                            DataInputStream dataInputStream = new DataInputStream(inputStream);

                            String s = dataInputStream.readUTF();

                            if (s.equals("exit")) {
                                dataInputStream.close();
                                inputStream.close();
                              int i = 1/0;

                            }

                            System.out.println(accept.getRemoteSocketAddress()+"说=>："+s);

                        } catch (Exception e) {
                            System.out.println(accept.getRemoteSocketAddress()+"=>下线了");

                            accept.close();
                            break;
                        }

                    }
                }
            };


            Thread thread = new Thread(runnable);

            thread.start();

            //Socket不是一个数据，而是一个通道，建立连接之后，如果没有关闭，都可以用这个通道一直处理数据
            //accept的方法会进行阻塞，当获取到传来的连接请求后就继续往下执行代码


        }


    }

}
