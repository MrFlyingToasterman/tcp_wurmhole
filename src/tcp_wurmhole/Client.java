/*
 * GPLv3 free (as in freedom) software
 */
package tcp_wurmhole;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Darius Musiolik
 */
public class Client {
    
    public static whutils whutil = new whutils();

    //This void starts the client
    public static void startclient() {
        
        System.out.println("Press enter for default value.\n");
        String server_ip = whutil.scan("Server IP[default 127.0.0.1]: ");
        if (server_ip.equals("")) {
            server_ip = "127.0.0.1";
        }
        String server_port = whutil.scan("Server Port[default 4444]: ");
        if (server_port.equals("")) {
            server_port = "4444";
        }
        //Convert to int
        int port = Integer.parseInt(server_port);

        //I need to catch a possible exception
        try {
            boolean stay = true;
            System.out.println("Connecting to Server " + server_ip + ":" + server_port + "\n"
                    + "Type poweroff to stop the Serverloop or kill to kill the Server.");
            
            do {
                
                String msg_send = whutil.scan("Enter your message: ");
                
                if (msg_send.equals("kill")) {
                    stay = false;
                }
                
                //Creating socket for connection with localhost
                Socket client = new Socket(server_ip, port);

                //Streaming
                //Out Streams
                OutputStream out = client.getOutputStream();
                PrintWriter pwriter = new PrintWriter(out);
                //In Streams
                InputStream in = client.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                //Sending Stuff to Server
                pwriter.write(msg_send + "\n");
                pwriter.flush();

                //Reading In streams
                String info_get = null;

                //while for multiple lines
                String info_saved = "";
                while((info_get = reader.readLine()) != null) {
                    info_saved = info_saved + info_get + "\n";
                }
                
                System.out.println("Get from Server: " + info_saved);

                //Close reader and writer
                reader.close();
                pwriter.close();

                }while(stay);

        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Error: Lost connection to Server!");
        }

    }
}