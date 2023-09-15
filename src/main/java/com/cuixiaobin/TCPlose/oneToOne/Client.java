package com.cuixiaobin.TCPlose.oneToOne;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {


        Socket socket = new Socket("127.0.0.2",8002);

        DataOutputStream in = new DataOutputStream(socket.getOutputStream());

        BufferedInputStream inStream = new BufferedInputStream(new FileInputStream("H:\\IO\\iozh.txt"));
        byte[] bytes = inStream.readAllBytes();
        in.write(bytes);
        socket.close();

    }
}
