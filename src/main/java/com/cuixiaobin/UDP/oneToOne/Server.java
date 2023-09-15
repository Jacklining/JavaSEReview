package com.cuixiaobin.UDP.oneToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

@Data
@NoArgsConstructor
public class Server {
    public static void main(String[] args) throws IOException {

        byte[] bytes = new byte[1024*4];
        DatagramPacket dp = new DatagramPacket(bytes,0,bytes.length);
        DatagramSocket socket = new DatagramSocket(0002, InetAddress.getByAddress(new byte[]{127,0,0,2}));
        socket.receive(dp);
        System.out.println(new String(dp.getData(),0,dp.getData().length).trim());

        socket.close();

    }
}
