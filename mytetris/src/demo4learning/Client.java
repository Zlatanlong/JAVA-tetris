package demo4learning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import socket.GameClient;

public class Client extends Thread {

    Socket socket;

    public Client() {
        try {
            this.socket = new Socket("127.0.0.1", 60000);
            new Thread(this).start();

            PrintWriter pw = new PrintWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream()));
            Scanner scanner = new Scanner(System.in);
            String iSay;
            while (!(iSay = scanner.nextLine()).equals("bye")) {
                pw.println(iSay);
                pw.flush();
            }
            pw.println("bye");
            pw.flush();
            pw.close();

        } catch (IOException ex) {
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream()));
            String code;
            while (!(code = br.readLine()).equals("bye")) {
                System.out.println("服务器说：");
                System.out.println(code);
            }
            System.out.println("服务端已经关闭了对话！");
            br.close();
            socket.close();
        } catch (Exception e) {
            System.out.println("客户端已经关闭");
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return socket;
    }

    public static void main(String[] args) {
        Client cli = new Client();
    }
}
