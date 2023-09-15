package com.cuixiaobin.UDP.oneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;

@Data
@NoArgsConstructor
public class Client {

    public static void main(String[] args) throws IOException {

        BufferedInputStream bis = new BufferedInputStream(new FileInputStream("H:\\IO\\iozh.txt"));

        byte[] data = bis.readAllBytes();
//        String all = new String(data);


        System.out.println("*****************数据读取成功**************");


//        DatagramPacket packet = new DatagramPacket(data, data.length, InetAddress.getByAddress(new byte[]{127, 0, 0, 2}), 0002);
        DatagramPacket packet = new DatagramPacket(data,0,data.length);


        DatagramSocket socket = new DatagramSocket(0001, InetAddress.getByAddress(new byte[]{127, 0, 0, 1}));

        socket.send(packet);
        socket.close();
    }

}
