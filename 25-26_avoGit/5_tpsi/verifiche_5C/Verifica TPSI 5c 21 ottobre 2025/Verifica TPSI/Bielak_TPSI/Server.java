import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server 
{
    public static void main(String args[])
    {
        try
        {
            ServerSocket ss = new ServerSocket(7777);
            System.out.println("Server in ascolto sulla porta 7777...");
            
            while(true)
            {
                Socket s = ss.accept();
                System.out.println("Connessione accettata da: " + s.getInetAddress());
                
                new ServerThread(s).start();
            }
            
        }
        catch(IOException e)
        {
            System.out.println("500 Internal Server Error - Errore nel server: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
