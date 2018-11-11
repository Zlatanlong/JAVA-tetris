package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client implements Runnable{
    Socket client;
    
    public Client() {
        System.out.println("Client:已经连接到服务器！");
        System.out.println("----------------------------");
        try {
            client = new Socket("127.0.0.1", 50000);
            new Thread(this).start();
            PrintWriter pw = new PrintWriter(
                    new OutputStreamWriter(
                    client.getOutputStream()));
            
            Scanner scanner = new Scanner(System.in);
            String iSay;
            while (!(iSay = scanner.nextLine()).equals("bye")) {
                pw.println(iSay);
                pw.flush();
            }
            pw.println("bye");
            pw.flush();
            pw.close();
            System.out.println("客户端关闭");
            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Client cli = new Client();
    }

    /**
     * 这个线程用来不断地读
     */
    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                    client.getInputStream()));
            String msg;
            while (!(msg=br.readLine()).equals("bye")) {                
                System.out.println("Serve say:" + msg);
                Thread.sleep(10);
            }
            System.out.println("服务端已经关闭了对话！");
            br.close();
            client.close();
        } catch (Exception e) {
            System.out.println("客户端已经关闭");
        }
    }
}
