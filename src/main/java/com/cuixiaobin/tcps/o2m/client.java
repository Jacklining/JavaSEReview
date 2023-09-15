package com.cuixiaobin.tcps.o2m;

import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;


//基本：
public class client {

    public static void main(String[] args) throws IOException {


        Socket socket = new Socket("127.0.0.1", 8001);

        Scanner scanner = new Scanner(System.in);

        Thread thread = new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                DataInputStream in = new DataInputStream(socket.getInputStream());
                while (true) {

                   try {

                       String s = in.readUTF();
                       System.out.println(s);

                       if (!socket.isConnected()){
                           throw new Exception("断开连接");
                       }

                   }catch (Exception e) {

                       in.close();
                   }
                }
            }
        });

        thread.start();

        while (true) {

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String s = scanner.nextLine();
            if ("exit".equals(s)) {
                out.close();
                socket.close();
                break;
            }

            out.writeUTF(s);
            out.flush();

        }

    }
}
