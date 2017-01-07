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
        
        do {
        
            //I need to catch a possible exception
            try {
                //Creating Socket that will listen to the Port 4444
                ServerSocket wurmhole = new ServerSocket(4444); 
                whutil.log("Server starting", 1);

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
                String saved_information = "";
                //Need a loop ? Why not Zoidberg...
                for(;;) {
                    info_get = reader.readLine();
                    whutil.log("Getting from client =>" + info_get + "<", 1);
                    if (info_get == null) {
                        break;
                    } else {
                        saved_information = saved_information + info_get;
                    }
                }
                //Print complete information
                whutil.log(saved_information, 1);
                //Check if saved_information was a Servercommand
                 whutil.log("Searching for Servercommand in saved_information", 1);
                checkcommand(saved_information);
                //Close reader and writer
                reader.close();
                pwriter.close();

            } catch (Exception e) {
            }
            
        }while(serverpoweron);
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
