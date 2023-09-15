package com.cuixiaobin.TCPlose.Threads;

import ch.qos.logback.core.encoder.JsonEscapeUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        try {
            System.out.println("服务器启动：");
            InetAddress byAddress = InetAddress.getByAddress(new byte[]{127, 0, 0, 2});
            ServerSocket serverSocket = new ServerSocket(8001, 10, byAddress);

            while (true) {
                Socket accept = serverSocket.accept();
                InputStream inputStream = accept.getInputStream();
                System.out.println("卡主");
                byte[] bytes = inputStream.readAllBytes();
                System.out.println(new String(bytes));
            }


//            Thread threads = new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Socket socket = serverSocket.accept();
//                        InputStream inputStream = socket.getInputStream();
//                        String outer = "";
//                        while (!outer.equals("exit")) {
//                            byte[] bytes = inputStream.readAllBytes();
//                            outer = new String(bytes);
//                            System.out.println(outer);
//
//                            inputStream.close();
//                            socket.close();
//                        }
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//
//                    System.out.println("祝您生活愉快，再见");
//
//                }
//            });
//
//            threads.start();


        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

}
