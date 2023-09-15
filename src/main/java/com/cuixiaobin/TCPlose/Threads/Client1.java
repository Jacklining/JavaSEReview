package com.cuixiaobin.TCPlose.Threads;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client1 {

    public static void main(String[] args) {

        try {            //Socket的对象，并绑定地址
            Socket socket = new Socket(InetAddress.getByAddress(new byte[]{127,0,0,2}),8001);
            System.out.println("通过");
            //获取Socket输入输出流
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String next = "";
            Scanner scanner = new Scanner(System.in);

            while (!next.equals("exit")) {
                next = scanner.next();

                out.writeBytes(next);
//                out.writeUTF(next);
//                out.flush();
            }

            System.out.println("祝您生活愉快，再见");
            out.close();
            socket.close();



        } catch (IOException e) {
            System.out.println("youwenti");
        }
    }


}
