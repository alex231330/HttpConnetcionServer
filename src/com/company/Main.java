package com.company;

import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.*;

import com.company.ServerHandler;

public class Main {
    static Socket s;

    public static void main(String[] args) throws Throwable {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream is = null;
                OutputStream os = null;
                try {
                    is = s.getInputStream();
                    os = s.getOutputStream();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                while (true) {
                    try {
                        String s = br.readLine().toString();
                        if (s != null) {
                            break;
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                String res = "Resp";
                // http://localhost:8080/
                try {
                    os.write(res.getBytes());
                    os.flush();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        ServerSocket server = new ServerSocket(8080);
        s = server.accept();
        System.out.println("Client connected");
        thread.start();

    }
}
