
import java.io.*;
import java.net.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class client_verifica {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Socket sc = new Socket("localhost", 6666);
            
            PrintWriter invia = new PrintWriter(sc.getOutputStream(), true);
            invia.println("maria");
            
            InputStreamReader leggi = new InputStreamReader(sc.getInputStream());
            BufferedReader ricevi = new BufferedReader(leggi);
            
            InputStreamReader leggiTastiera = new InputStreamReader(sc.getInputStream());
            BufferedReader tastiera = new BufferedReader(leggiTastiera);
            
            String messaggioServer = ricevi.readLine();
            if(messaggioServer != null){
                String parti[] = messaggioServer.split[";"];
            } else if(parti.lenght() > 1){
                System.out.println("SERVER" + parti[1]);
            }
            
            boolean continua = true; 
            while (continua){
                System.out.println("Inserisci comando: ");
                String comando = tastiera.readLine();
            }

            System.out.println("Client chiude la comunicazione");
            sc.close();
            
        } catch (IOException ex) {
            System.out.println("ECCEZIONE RILEVATA!!!");
        }
    }
}
