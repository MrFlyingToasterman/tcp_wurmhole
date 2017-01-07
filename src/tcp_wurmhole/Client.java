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

    //This void starts the client
    public static void startclient() {

        //I need to catch a possible exception
        try {

            //Creating socket for connection with localhost
            Socket client = new Socket("127.0.0.1", 4444);

            //Streaming
            //Out Streams
            OutputStream out = client.getOutputStream();
            PrintWriter pwriter = new PrintWriter(out);
            //In Streams
            InputStream in = client.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            //Sending STuff to Server
            pwriter.write("Say no more fam");
            pwriter.flush();

            //Close reader and writer
            reader.close();
            pwriter.close();

        } catch (Exception e) {
        }

    }
}
