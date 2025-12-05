/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package allegati;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author MULTI01
 */
public class Server {

    public static void main(String[] args) throws IOException {
        
        try{
            ServerSocket ss = new ServerSocket(7890);
            while(true){
                System.out.println("Aspetto ...");
                Socket s = ss.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String input = in.readLine();
                System.out.println("Client ha iniviato: " + input);
                float num = Float.parseFloat(input);
                
                //float numReais= (float) (num * 6.17);
                float numReais= (float) (num * 2);
                System.out.println("Server risultato: " + numReais);
                //tornare all'inizio
                s.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
