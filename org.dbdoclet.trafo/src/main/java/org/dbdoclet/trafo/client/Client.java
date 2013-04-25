package org.dbdoclet.trafo.client;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        
        try {

            Socket socket = new Socket("127.0.0.1", 2009);
            PrintWriter writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
            
            for (String arg : args) {
                writer.println(arg);
            }
            
            writer.close();
            socket.close();
            
        } catch (Exception oops) {
            oops.printStackTrace();
        }
    }
}
