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

public class Server 
{
    public static void main(String[] args) throws IOException
    {
        ServerSocket server = new ServerSocket(9999);
        Socket socket = null;
        
        while(true)
        {
            //il server stabilisce la connessione
            socket = server.accept();
            System.out.println("Connessione stabilita!");
            
            //riceve testo dal client
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader input = new BufferedReader(isr);
            String messaggioRicevuto = input.readLine();
            System.out.println("\nIl server riceve:" + messaggioRicevuto);
            
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            
            //divide la stringa ricevuta in parti
            String parti[] = messaggioRicevuto.split(";");
            
            String primaParte = parti[0];
            String secondaParte = parti[1]; 
            String terzaParte = parti[2];
            
            //divide a sua volta i valori della seconda e terza parte per svolgere le funzioni richieste e fare dei controlli
            int valore1 = Integer.parseInt(secondaParte.substring(0, 1));
            int valore2 = Integer.parseInt(secondaParte.substring(2, 3));
            int valore3 = Integer.parseInt(secondaParte.substring(4, 7));
            
            String stringa1 = secondaParte.substring(0, 1);
            String stringa2 = secondaParte.substring(2, 3);
            String stringa3 = secondaParte.substring(4, 7);
            
            int valore4 = Integer.parseInt(terzaParte.substring(0, 1));
            int valore5 = Integer.parseInt(terzaParte.substring(2, 3));
            int valore6 = Integer.parseInt(terzaParte.substring(4, 7));
            
            //ERR;01 --> la data non e' corretta
            if(primaParte.equalsIgnoreCase("CHECK"))
            {
                if(secondaParte.length() != 8 || valore1 > 31 || valore1 < 1
                   || valore2 > 12 || valore2 < 1 || valore3 < 0)
                {
                    System.out.println("\nERR;01");
                    out.println("\nERR;01");
                }else
                    System.out.println("\nLa data e' corretta.");
                    out.println("\nLa data e' corretta.");
            }
            
            //Controlla quale delle 2 date e' la maggiore
            else if(primaParte.equalsIgnoreCase("GREATER"))
            {
                if(valore3 > valore6 || valore2 > valore5 || valore1 > valore4)
                {
                    System.out.println("\nLa prima data e' maggiore.");
                    out.println("\nLa prima data e' maggiore.");
                }else
                    {
                        System.out.println("\nLa seconda datae' maggiore.");
                        out.println("\nLa seconda datae' maggiore.");
                    }
            }
            
            //stampa il nome del mese in base al numero del mese
            else if(primaParte.equalsIgnoreCase("MONTH"))
            {
                if(valore2 == 1)
                {
                    System.out.println("\nMese: Gennaio.");
                    out.println("\nMese: Gennaio.");
                }
                else if(valore2 == 2)
                {
                    System.out.println("\nMese: Febbraio.");
                    out.println("\nMese: Febbraio.");
                }
                else if(valore2 == 3)
                {
                    System.out.println("\nMese: Marzo.");
                    out.println("\nMese: Marzo.");
                }
                else if(valore2 == 4)
                {
                    System.out.println("\nMese: Aprile.");
                    out.println("\nMese: Aprile.");
                }
                else if(valore2 == 5)
                {
                    System.out.println("\nMese: Maggio.");
                    out.println("\nMese: Maggio.");
                }
                else if(valore2 == 6)
                {
                    System.out.println("\nMese: Giugno.");
                    out.println("\nMese: Giugno.");
                }
                else if(valore2 == 7)
                {
                    System.out.println("\nMese: Luglio.");
                    out.println("\nMese: Luglio.");
                }
                else if(valore2 == 8)
                {
                    System.out.println("\nMese: Agosto.");
                    out.println("\nMese: Agosto.");
                }
                else if(valore2 == 9)
                {
                    System.out.println("\nMese: Settembre.");
                    out.println("\nMese: Settembre.");
                }
                else if(valore2 == 10)
                {
                    System.out.println("\nMese: Ottobre.");
                    out.println("\nMese: Ottobre.");
                }
                else if(valore2 == 11)
                {
                    System.out.println("\nMese: Novembre.");
                    out.println("\nMese: Novembre.");
                }else
                    {
                        System.out.println("\nMese: Dicembre.");
                        out.println("\nMese: Dicembre.");
                    }
            }
            
            //formatta la data in modo che giorno, mese ed anno siano divisi da "/"
            else if(primaParte.equalsIgnoreCase("SLASH"))
            {
                System.out.println(stringa1 + "/" + stringa2 + "/" + stringa3);
                out.println(stringa1 + "/" + stringa2 + "/" + stringa3);
            }
            
            //inverte la posizione della data mettendo prima l'anno poi il mese ed infine il giorno
            else if(primaParte.equalsIgnoreCase("TURNDATE"))
            {
                System.out.println(stringa3 + stringa2 + stringa1);
                out.println(stringa3 + stringa2 + stringa1);
            }else
                {
                    System.out.println("\nERR;99"); //ERR;99 --> Errore genrico
                    out.println("\nERR;99");
                }
            socket.close();
        }
    }
}
