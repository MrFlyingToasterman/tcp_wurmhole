/*
 * GPLv3 free (as in freedom) software
 */
package tcp_wurmhole;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Darius Musiolik
 */
public class Server {
    
    public static whutils whutil = new whutils();
    
    //This void will run the Server
    public static void startserver() {
        
        //I need to catch a possible exception
        try {
            //Creating Socket that will listen to the Port 4444
            ServerSocket wurmhole = new ServerSocket(4444); 
            whutil.log("Server starting", 0);
            
            //Creating incomming connection
            Socket client = wurmhole.accept();
            
        } catch (Exception e) {
        }
        
    }
    
}
