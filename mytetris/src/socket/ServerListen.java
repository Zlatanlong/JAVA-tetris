package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class ServerListen extends Thread {

    Socket clientSocket;

    public ServerListen(Socket socket) {
        this.clientSocket = socket;
    }

    public void run() {
        try {
            System.out.println("服务器读开始");
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String msg;
            while (!isInterrupted()) {
                if ((msg = br.readLine()).equals("bye")) {
                    break;
                }
                System.out.println("此次"+isInterrupted());
                System.out.println("client say:" + msg);
                Thread.sleep(100);
            }
            br.close();
            System.out.println("----------------------------客户端结束连接！");
            clientSocket.close();
        } catch (Exception e) {
            System.out.println("服务器已经关闭了对话");
        }
    }
}
