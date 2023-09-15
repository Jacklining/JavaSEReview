package com.cuixiaobin.TCPlose.Threads;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThreads {

    public void start(ServerSocket socket) throws IOException {

        Socket accept = socket.accept();

        String next = "";
        while (!next.equals("exit")) {
            InputStream inputStream = accept.getInputStream();
            DataInputStream dataInputStream = new DataInputStream(inputStream);

            next = dataInputStream.readUTF();
            System.out.println(accept.getInetAddress().getHostAddress() + "=>"+ ":" + next);

        }

        System.out.println(accept.getRemoteSocketAddress().toString()+"=》下线了");

    }
}
