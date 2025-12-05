/*
Simone Caccherano
5CI - 21/10/2025
VERIFICA TPSI: Fila A
*/
package verifica_tpsi_caccherano;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.net.*;

public class Client 
{
    public static void main(String[] args) throws IOException
    {
        //Invio richiesta al server
        Socket socket = new Socket("127.0.0.1", 9999);
        
        //Il client invia i dati al server
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        out.println("CHECK;21102025");
        
        //riceve testo dal Server
        InputStreamReader isr = new InputStreamReader(socket.getInputStream());
        BufferedReader input = new BufferedReader(isr);
        String risposta = input.readLine();
        System.out.println("\nIl client riceve:" + risposta);
        
        socket.close();
    }
}
