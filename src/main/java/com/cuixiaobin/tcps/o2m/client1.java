package com.cuixiaobin.tcps.o2m;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class client1 {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("127.0.0.1",8001, InetAddress.getByAddress(new byte[]{127,0,1,1}),8011);

        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

        Scanner scanner = new Scanner(System.in);

        while (true){
            String s = scanner.nextLine();

            if(s.equals("exit")){
                System.out.println("退出成功");
                dos.close();
                socket.close();
                break;
            }

            dos.writeUTF(s);
            dos.flush();

        }
    }

}
