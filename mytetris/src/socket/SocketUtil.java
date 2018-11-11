package socket;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketUtil {
    
    public static void send(Socket client,String string) {
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
}
