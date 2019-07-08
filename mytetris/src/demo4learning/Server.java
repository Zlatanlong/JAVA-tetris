package demo4learning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import socket.GameServer;

public class Server extends Thread {

    Socket socket;
    private javax.swing.JTextArea text2;
    PrintWriter pw;

    public Server(javax.swing.JTextArea text) {
        try {
            this.text2 = text;
            System.out.println("服务器开启！");
            
            ServerSocket serverSocket = new ServerSocket(60000);
            
            socket = serverSocket.accept();
            
            System.out.println("----------------------------");
            System.out.println("有新的客户端连接到服务器喽！");
            new Thread(this).start(); // 启动听的线程

            pw = new PrintWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream()));
//            Scanner scanner = new Scanner(System.in);
//            String iSay;
//            while (!(iSay = scanner.nextLine()).equals("bye")) {
//                pw.println(iSay);
//                pw.flush();
//            }
//            pw.println("bye");
//            pw.flush();
//            pw.close();

        } catch (IOException ex) {
            Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void push(String str) {
        try {
            pw = new PrintWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream()));
            pw.println(str);
            pw.flush();
        } catch (IOException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
        }
    }
    
    
    
    @Override
    public void run() {
        // 对于某个线程对象来说，run函数中就是线程运行时需要执行的内容
        System.out.println("我开始收听客户端！");
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            String code;
            while (!(code = br.readLine()).equals("bye")) {
                System.out.println(code);
                text2.setText(text2.getText() + "\n" + code);
            }
            System.out.println("客户端已经关闭了对话！");
            br.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("服务器已经关闭");
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public static void main(String[] args) {
//        Server ser = new Server();
    }
}
