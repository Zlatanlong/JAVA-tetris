package socket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameServer extends Thread {

    Socket client;

    public GameServer() {
        System.out.println("服务器开启！");
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(50000);
            client = serverSocket.accept();
            System.out.println("----------------------------");
            System.out.println("有新的客户端连接到服务器喽！");
        } catch (IOException ex) {
            Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Socket getSocket() {
        return client;
    }
}
