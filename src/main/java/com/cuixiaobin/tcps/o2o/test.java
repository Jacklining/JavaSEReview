package com.cuixiaobin.tcps.o2o;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class test {

    public static void main(String[] args) throws IOException {

        ServerSocket socket = new ServerSocket(8881,10, InetAddress.getByAddress(new byte[]{127,0,0,10}));
    }
}
