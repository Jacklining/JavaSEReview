package com.cuixiaobin.tcps.o2o;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class client {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8888, InetAddress.getByAddress(new byte[]{127,0,0,1}),0001);

        DataOutputStream dos =new DataOutputStream(socket.getOutputStream());

        dos.writeUTF("hello");

        dos.close();
        socket.close();
    }
}
