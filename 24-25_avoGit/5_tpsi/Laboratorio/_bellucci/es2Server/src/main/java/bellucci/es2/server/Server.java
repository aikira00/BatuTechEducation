package bellucci.es2.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        ServerSocket ss = new ServerSocket(60000);
        Socket sc = ss.accept();
        PrintWriter out = new PrintWriter(sc.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(sc.getInputStream()));

        out.println("Benvenuto");
        String line = in.readLine();
        System.out.println("Server: ricevuto " + line);
        while(line!=null && !line.equalsIgnoreCase("quit")){
            System.out.println("Server: invio " + line);
            out.println(line);
            line = in.readLine();
            System.out.println("Server: ricevuto " + line);
        }
        System.out.println("Ricevuto quit? " + line);
        in.close();
        out.close();
        sc.close();
    }
}