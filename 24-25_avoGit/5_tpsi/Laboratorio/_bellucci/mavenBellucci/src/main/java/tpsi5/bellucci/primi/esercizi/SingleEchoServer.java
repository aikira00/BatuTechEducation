package tpsi5.bellucci.primi.esercizi;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class SingleEchoServer {
    public static void main(String[] args) {

            try (
                    ServerSocket ss = new ServerSocket(60000);
                    Socket sc = ss.accept();
                    PrintWriter out = new PrintWriter(sc.getOutputStream());
                    BufferedReader in = new BufferedReader(new InputStreamReader(sc.getInputStream()));
            ){


                out.println("Benvenuto");
                String line;
                while((line=in.readLine())!= null){
                    System.out.println("Ho ricevuto: " +  line);
                    out.println("Ricevuto: " + line);
                }

            }
            catch(Exception e){
                System.out.println("SERVER: an error occured");
                e.getMessage();
            }
    }
}
