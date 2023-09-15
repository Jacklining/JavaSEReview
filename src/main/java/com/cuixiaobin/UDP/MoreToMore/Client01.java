package com.cuixiaobin.UDP.MoreToMore;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client01 {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        String next = "";
        while (!next.equals("不和你聊了")) {

            next = scanner.next();
            byte[] bytes = next.getBytes(StandardCharsets.UTF_8);
            DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByAddress(new byte[]{127, 0, 0, 2}), 0002);

            DatagramSocket socket = new DatagramSocket(0001,InetAddress.getByAddress(new byte[]{127,0,0,3}));
            socket.send(dp);
            socket.close();

        }

    }
}
