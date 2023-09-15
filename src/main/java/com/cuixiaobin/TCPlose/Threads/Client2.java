package com.cuixiaobin.TCPlose.Threads;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {

    public static void main(String[] args) {

        try {
            //Socket的对象，并绑定地址
            Socket socket = new Socket("127.0.0.1", 8001);
            //获取Socket输入输出流
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String next = "";
            Scanner scanner = new Scanner(System.in);

            while (!next.equals("exit")) {
                next = scanner.next();
                out.writeUTF(next);
            }

            System.out.println("祝您生活愉快，再见");
            out.close();
            socket.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
