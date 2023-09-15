package com.cuixiaobin.tcps.o2o;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(8888,10, InetAddress.getByAddress(new byte[]{127,0,0,1}));
        Socket accept = socket.accept();
        InputStream inputStream = accept.getInputStream();

        DataInputStream dis = new DataInputStream(inputStream);

        String s = dis.readUTF();

        System.out.println(s);

        System.out.println("accept.getRemoteSocketAddress():"+accept.getRemoteSocketAddress());
        //获取远程端socket的ip+端口号
        System.out.println("accept.getLocalAddress():"+accept.getLocalAddress());
        //获取本机socket的ip
        System.out.println("accept.getPort():"+accept.getPort());
        //获取远程socket的端口号
        System.out.println(accept.getLocalPort());
        dis.close();
        accept.close();
        socket.close();
    }

}
