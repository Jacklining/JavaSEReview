package com.cuixiaobin.TCPlose.MoreToMore;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(8001, 1, InetAddress.getByAddress(new byte[]{127, 0, 0, 2}));

        String next = "";
        while (!next.equals("exit")) {
            Socket socket = serverSocket.accept();

            InputStream inputStream = socket.getInputStream();

            DataInputStream dataInputStream = new DataInputStream(inputStream);

            byte[] bytes = dataInputStream.readAllBytes();
            next = new String(bytes).trim();
            System.out.println(next);

//            FileReader


            socket.close();
        }


    }


}
