package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytetris.GamePanel;

public class GameServer extends Thread {

    Socket client;
    GamePanel gamePanel;

    
    public GameServer(GamePanel gamePanel) {
        try {
            this.gamePanel = gamePanel;
            System.out.println("服务器开启！");
            ServerSocket serverSocket = new ServerSocket(50000);
            client = serverSocket.accept();
            System.out.println("----------------------------");
            System.out.println("有新的客户端连接到服务器喽！");
        new Thread(this).start();
        } catch (IOException ex) {
            Logger.getLogger(GameServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        System.out.println("我开始收听客户端！");
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            client.getInputStream()));
            String code;
            while (!(code = br.readLine()).equals("bye")) {
                gamePanel.getPressed(code);
                gamePanel.repaint();
            }
            System.out.println("客户端已经关闭了对话！");
            br.close();
            client.close();
        } catch (Exception e) {
            System.out.println("服务器已经关闭");
            e.printStackTrace();
        }
    }

    public Socket getSocket() {
        return client;
    }
}
