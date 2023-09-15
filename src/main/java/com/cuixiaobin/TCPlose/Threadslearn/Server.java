package com.cuixiaobin.TCPlose.Threadslearn;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        System.out.println("服务器启动成功：");

        ServerSocket serverSocket = new ServerSocket(8888);

        while (true) {
            Socket socket = serverSocket.accept();
            System.out.println("有人上线了" + ":" + socket.getRemoteSocketAddress());
            new ServerReaderThread(socket).start();
}
    }

}
