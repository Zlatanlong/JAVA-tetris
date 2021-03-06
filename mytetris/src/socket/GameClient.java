package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import mytetris.GamePanel;

public class GameClient extends Thread {

    Socket clientSocket;
    GamePanel gamePanel;

    public GameClient(GamePanel gamePanel, String ip) {
        try {
            this.gamePanel = gamePanel;
            this.clientSocket = new Socket(ip, 50000);
            new Thread(this).start();
        } catch (IOException ex) {
            Logger.getLogger(GameClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        try {
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            clientSocket.getInputStream()));
            String code;
            while (!(code = br.readLine()).equals("bye")) {
                gamePanel.getPressed(code);
                gamePanel.repaint();
            }
            System.out.println("服务端已经关闭了对话！");
            br.close();
            clientSocket.close();
        } catch (Exception e) {
            System.out.println("客户端已经关闭");
            e.printStackTrace();
        }
    }
    
    public Socket getSocket() {
        return clientSocket;
    }
}
