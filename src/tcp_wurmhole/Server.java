/*
 * GPLv3 free (as in freedom) software
 */
package tcp_wurmhole;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Darius Musiolik
 */
public class Server {
    
    public static whutils whutil = new whutils();
    public static boolean serverpoweron = true;
    
    //This void will run the Server
    public static void startserver() {
 

        //I need to catch a possible exception
        try {
            //Creating Socket that will listen to the Port 4444
            ServerSocket wurmhole = new ServerSocket(4444);
            whutil.log("Server starting", 1);

            do {
                whutil.log("Server ready", 1);
                //Creating incomming connection
                Socket client = wurmhole.accept();
                
                //Streaming

                //Out Streams
                OutputStream out = client.getOutputStream();
                PrintWriter pwriter = new PrintWriter(out);
                //In Streams
                InputStream in = client.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                //Reading In streams
                String info_get = null;
                while((info_get = reader.readLine()) != null) {
                    pwriter.write(info_get + "\n");
                    pwriter.flush();
                    whutil.log("Get from Client >" + info_get + "< ", 1);
                }
                //Check if saved_information was a Servercommand
                whutil.log("Searching for Servercommand in saved_information", 1);
                checkcommand(info_get);
                
                //Close reader and writer
                reader.close();
                pwriter.close();

            } while (serverpoweron);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkcommand(String checkthis) {
        
        switch (checkthis) {
            case "poweroff":
                whutil.log("Servercommand for shutdown found!", 2);
                whutil.log("Server shutdown immediately", 2);
                serverpoweron = false;
                break;
            case "kill":
                whutil.log("Servercommand for killing process found!", 2);
                whutil.log("Server is commiting suicide", 2);
                System.exit(0);
                break;
            default:
                whutil.log("No Servercommand found", 2);
                break;
        }
    }
    
}
