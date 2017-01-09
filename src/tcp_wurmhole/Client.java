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

        //I need to catch a possible exception
        try {
            boolean stay = true;
            System.out.println("Connecting to Server 127.0.0.1:4444\n"
                    + "Type poweroff to stop the Serverloop or kill to kill the Server.");
            
            do {
                
                String msg_send = whutil.scan("Enter your message: ");
                

                //Creating socket for connection with localhost
                Socket client = new Socket("127.0.0.1", 4444);

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
                info_get = reader.readLine();
                pwriter.write(info_get);
                pwriter.flush();
                System.out.println("Get from Server >" + info_get + "< ");

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