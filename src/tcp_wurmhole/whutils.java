/*
 * GPLv3 free (as in freedom) software
 */
package tcp_wurmhole;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

/**
 *
 * @author Darius Musiolik
 */
public class whutils {
    
    public String logfile = "";
    
    public void log(String msg, int type) {
        
        /*
            0 = No preset
            1 = Information
            2 = Warning
            3 = Error
        */
        
        String preset;
        
        switch (type) {
            case 0:
                preset = "";
                break;
            case 1:
                preset = "INFO: ";
                break;
            case 2:
                preset = "WARNING: ";
                break;
            case 3:
                preset = "ERROR: ";
                break;
            default:
                preset = "Error while reading preset in void log (whutils): ";
                break;
        }
        System.out.println(preset + msg + gimme_time());
        logfile = logfile + "\n" + preset + msg + gimme_time() + "\n";
    }
    
    public String scan(String msg) {
        Scanner scan = new Scanner(System.in);
        System.out.print(msg);
        return scan.nextLine();
    }
    
    public String gimme_time() {
        return " [" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime()) + "] ";
    }
    
}
