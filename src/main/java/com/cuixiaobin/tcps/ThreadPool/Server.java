package com.cuixiaobin.tcps.ThreadPool;

import lombok.SneakyThrows;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.concurrent.*;

public class Server {

    public static ArrayList<Socket> sockets = new ArrayList<Socket>();

    public static void main(String[] args) throws IOException {


//        ArrayList<Socket> sockets = new ArrayList<Socket>();
        ServerSocket serverSocket = new ServerSocket(8001, 10, InetAddress.getByName("127.0.0.1"));

        while (true) {

            Socket accept = serverSocket.accept();
            System.out.println(accept.getRemoteSocketAddress()+"=>:上线了。");
            sockets.add(accept);

            ThreadsPool threadsPool = new ThreadsPool(accept);
            threadsPool.poolrun();
        }


    }


}


class ThreadsPool {
    Socket accept;


    public ThreadsPool(Socket accept) {
        this.accept = accept;
    }

    public void poolrun() {

        ExecutorService executor = Executors.newFixedThreadPool(10);
        Socket accept = this.accept;

        executor.execute(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {

                InputStream inputStream = accept.getInputStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);


                while (true) {
                    try {

                        String s = dataInputStream.readUTF();

                        if (s.equals("exit")) {
                            System.out.println( accept.getRemoteSocketAddress()+"=》下线了");
                            Server.sockets.remove(accept);
                            throw new Exception("啦啦啦");
                        }

                        System.out.println(s);

                        for (int i = 0; i < Server.sockets.size(); i++) {
                            if (Server.sockets.get(i)!=accept){
                                Socket socket= Server.sockets.get(i);
                                DataOutputStream dis = new DataOutputStream(socket.getOutputStream());
                                dis.writeUTF(s);
                            }
                        }


                    } catch (Exception e) {
                        dataInputStream.close();
                        inputStream.close();
                        accept.close();
                        break;
                    }
                }
            }
        });


    }
}