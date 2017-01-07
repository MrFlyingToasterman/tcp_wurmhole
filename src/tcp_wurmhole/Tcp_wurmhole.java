/*
 * GPLv3 free (as in freedom) software
 */
package tcp_wurmhole;

/**
 *
 * @author Darius Musiolik
 */
public class Tcp_wurmhole {
    
    public static whutils whutil = new whutils();

    public static void main(String[] args) {
        
        boolean menu = true;
        
        do {
        
            System.out.println("Welcome to my tcp_wurmhole!\n\nDo you wish to run the Server or the client ?\n"
                    + "[1] Server\n"
                    + "[2] Client\n"
                    + "[3] Exit\n\n");
            String input = whutil.scan("Enter: ");
            
            switch (input) {
                case "1":
                    Server.startserver();
                    break;
                case "2":
                    Client.startclient();
                    break;
                case "3":
                    System.exit(0);
                    break;
                default:
                    menu = true;
                    break;
            }
        
        } while(menu);
        
    }
    
}
