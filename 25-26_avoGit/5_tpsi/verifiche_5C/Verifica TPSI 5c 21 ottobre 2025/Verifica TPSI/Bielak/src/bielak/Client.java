import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client  ///non so perchè ma server mi risponde in ritardo, stampa il comando precedente
{
    public static void main(String[] args)
    {
        Socket socket = null;
        BufferedReader lettura = null;
        PrintWriter invio = null;
        Scanner scanner = new Scanner(System.in);
        
        try
        {
            socket = new Socket("localhost", 7777);
            System.out.println("connesso al server sulla porta 7777");
            
            lettura = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            invio = new PrintWriter(socket.getOutputStream(), true);
            
            mostraIstruzioni();
            
            String comando;
            
            while(true)
            {
                System.out.println("\nInserisci comando: ");
                comando = scanner.nextLine().trim();
                
                invio.println(comando);
                
                String risposta = lettura.readLine();
                System.out.println("Risposta server: " + risposta);
                
                if(comando.equalsIgnoreCase("EXIT"))
                {
                    break;
                }
            }
                
                scanner.close();
                lettura.close();
                invio.close();
                socket.close();
                System.out.println("Connessione chiusa.");
        }            
            catch(IOException e)
            {
                System.err.println("Errore di connessione al server:");
                e.printStackTrace();
            }
        
    }
    
    private static void mostraIstruzioni()
    {
        System.out.println("Istruzion:\n");
        System.out.println("comando,data");
    }
}
