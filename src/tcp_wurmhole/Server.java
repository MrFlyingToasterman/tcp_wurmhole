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
    public static boolean kill = false;
    public static boolean sendlog = false;
    
    //This void will run the Server
    public static void startserver() {
 

        //I need to catch a possible exception
        try {
            //Creating Socket that will listen to the Port 4444
            ServerSocket wurmhole = new ServerSocket(4444);
            whutil.log("Starting Server on Port 4444", 1);

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
                info_get = reader.readLine();
                whutil.log("Get from Client >" + info_get + "< ", 1);
                //Check if saved_information was a Servercommand
                whutil.log("Searching for Servercommand in info_get(String)", 1);
                checkcommand(info_get);
                
                
                //More Server Commands
                if (kill == true) {
                    //Sending log
                    pwriter.println("Server is commiting suicide!");
                    pwriter.flush();
                    System.exit(0);
                }
                else if (sendlog == true) {
                    //Sending log
                    whutil.log("Server sending logfile!", 2);
                    pwriter.println("Logfile -> \n" + whutil.logfile);
                    pwriter.flush();
                    //Do not send everytime
                    sendlog = false;
                }
                else {
                    //Sending echo only if no command was found!
                    whutil.log("Sending echo to Client", 1);
                    pwriter.write("Echo -> " + info_get + "\n");
                    pwriter.flush();
                }
                
                
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
            case "gimme_log":
                whutil.log("Servercommand for logsend found!", 2);
                sendlog = true;
                break;
            case "kill":
                whutil.log("Servercommand for killing process found!", 2);
                whutil.log("Server is commiting suicide", 2);
                kill = true;
                break;
            default:
                whutil.log("No Servercommand found", 2);
                break;
        }
    }
    
}
