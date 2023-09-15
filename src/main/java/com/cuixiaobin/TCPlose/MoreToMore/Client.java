package com.cuixiaobin.TCPlose.MoreToMore;

import java.io.*;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        String next = "";
        while (!next.equals("exit")){

            Socket socket= new Socket("127.0.0.2",8001);
            next = sc.nextLine();

            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            byte[] bytes = next.getBytes(StandardCharsets.UTF_8);
            output.writeUTF(new String(bytes).trim());
            socket.close();
        }


    }


}
