package com.cuixiaobin.TCPlose.oneToOne;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8002);
        Socket socket = serverSocket.accept();

        InputStream inputStream = socket.getInputStream();

        DataInputStream dataInputStream = new DataInputStream(inputStream);

        byte[] bytes = dataInputStream.readAllBytes();

        System.out.println(new String(bytes));


    }
}
