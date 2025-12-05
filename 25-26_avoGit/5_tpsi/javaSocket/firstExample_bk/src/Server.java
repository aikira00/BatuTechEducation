import java.io.*;
import java.net.*;

public class Server {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(60000);
            System.out.println("Server in ascolto sulla porta 60000...");

            while(true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connesso: " + clientSocket.getInetAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String message = in.readLine();
                System.out.println("Messaggio ricevuto dal client: " + message);

                out.println("Messaggio ricevuto dal server: " + message);

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}