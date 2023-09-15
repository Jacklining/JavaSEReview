package com.cuixiaobin;

import org.junit.Test;

import java.net.*;

public class T_17NetWork2 {

    @Test
    public void test1() {

        try {
            //1.服务端与用户端联通

            //UDP通信代码：

            //数据：
            byte[] buf = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
            //数据包：
            DatagramPacket dp;
            //数据收发人：
            //发：8085,127.0.0.1
            DatagramSocket dataSend = new DatagramSocket(8085,InetAddress.getByAddress(new byte[]{127,0,0,1}));
            DatagramSocket dataGet = new DatagramSocket(8086,InetAddress.getByAddress(new byte[]{127,0,0,2}));

            //收：
            dp = dp = new DatagramPacket(buf, 5, 5, InetAddress.getByAddress(new byte[]{127, 0, 0, 2}), 8086);

            //send
            dataSend.send(dp);

            //get
            dataGet.receive(dp);






        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
