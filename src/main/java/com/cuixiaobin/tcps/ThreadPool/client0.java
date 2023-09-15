package com.cuixiaobin.tcps.ThreadPool;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class client0 {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 8001, InetAddress.getByName("127.0.1.0"), 8010);

        Scanner scanner = new Scanner(System.in);

        while (true) {

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String s = scanner.nextLine();
            if ("exit".equals(s)) {
                out.close();
                socket.close();
                break;
            }

            out.writeUTF(s);
            out.flush();

        }

    }


}
