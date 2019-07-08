package socket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketUtil {
    
    public static void send(Socket socket,String string) {
        if (socket != null) {
            try {
                PrintWriter pw = new PrintWriter(
                        new OutputStreamWriter(
                                socket.getOutputStream()));
                pw.println(string);
                pw.flush();
            } catch (IOException ex) {
                System.out.println("error!!");
            }
        }
    }
}
