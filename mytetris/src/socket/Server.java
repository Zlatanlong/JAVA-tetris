package socket;

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

public class Server implements Runnable {

    Socket client;
    int count = 0;

    @Override
    public void run() {
        count++;
        System.out.println("----------------------------");
        System.out.println("有新的客户端连接到服务器喽！");
        System.out.println("当前连接数：" + count);
        System.out.println("----------------------------");
        try {
            ServerListen serverListen = new ServerListen(client);
            serverListen.start();
            PrintWriter pw = new PrintWriter(
                    new OutputStreamWriter(
                            client.getOutputStream()));
//            Scanner scanner = new Scanner(System.in);
//            String msg = scanner.nextLine();
//            while (true) {
//                pw.println(msg);
//                if (msg.equals("bye")) {
//                    break;
//                }
//                pw.flush();
//                msg = scanner.nextLine();
//            }

            Thread.sleep(10000000);
            pw.println("bye");
            pw.flush();

            serverListen.interrupt();
            System.out.println(serverListen.isInterrupted());
            pw.close();
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Server() {
        System.out.println("服务器开启！");
        try {
            ServerSocket serverSocket = new ServerSocket(50000);
//            while (true) {
            client = serverSocket.accept();
            System.out.println("lianjie");
            new Thread(this).start();
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(String string) {
        if (client != null) {
            try {
                PrintWriter pw = new PrintWriter(
                        new OutputStreamWriter(
                                client.getOutputStream()));
                pw.println(string);
                pw.flush();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        Server ser = new Server();
    }
}
