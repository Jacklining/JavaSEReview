package com.cuixiaobin.tcps.ThreadPool;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class client2 {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1", 8001, InetAddress.getByName("127.0.1.2"), 8012);

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
