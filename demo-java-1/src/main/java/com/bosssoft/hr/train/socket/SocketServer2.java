package com.bosssoft.hr.train.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: Suweihuan
 * @date: 2020/7/12 17:53
 */
public class SocketServer2 {
    public static void main(String[] args) {
        System.out.println("服务器开启");
        try {
            ServerSocket serverSocket=new ServerSocket(8888);
            while (true){
                Socket accept = serverSocket.accept();
                SocketThread socketThread=new SocketThread(accept);
                Thread thread=new Thread(socketThread);
                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
class SocketThread implements Runnable{
    private Socket socket;
    public SocketThread(Socket socket){
        this.socket=socket;
    }
    @Override
    public void run() {
        BufferedReader bufferedReader=null;
        PrintWriter printWriter=null;
        try{
            bufferedReader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter=new PrintWriter(socket.getOutputStream(), true);
            while (true){
                try {
                    String s=bufferedReader.readLine();
                    if (s!=null){
                        System.out.println("服务器接收到命令:"+s);
                        printWriter.println("hello client");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }     
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if (bufferedReader!=null){
                    bufferedReader.close();
                }
                if (printWriter!=null){
                    printWriter.close();
                }
                if (socket!=null){
                    socket.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }
}

