package com.cuixiaobin.UDP.MoreToMore;

import java.io.IOException;
import java.net.*;

//多发多收
public class Server01 {

    public static void main(String[] args) throws IOException {
        String msg="";
       while (!msg.equals("不和你聊了")) {
           DatagramSocket datagramSocket = new DatagramSocket(0002, InetAddress.getByAddress(new byte[]{127,0,0,2}));
           byte[] bytes = new byte[1024];
           DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length);
           datagramSocket.receive(datagramPacket);
            msg = new String(bytes,0,bytes.length).trim();
           System.out.println(msg);
           datagramSocket.close();
       }
    }
}
