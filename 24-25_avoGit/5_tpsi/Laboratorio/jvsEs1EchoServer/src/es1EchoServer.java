import java.io.*;
import java.net.*;

public class es1EchoServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(6000);
            System.out.println("Server in ascolto sulla porta 6000...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connesso: " + clientSocket.getInetAddress());
                System.out.println("Porta remota: " + clientSocket.getPort());
                System.out.println("Porta locale: " + clientSocket.getLocalPort());

                // server riceve una seconda richiesta dal client che processa dopo
                Thread.currentThread().sleep(5000);

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String message = in.readLine();
                System.out.println("Messaggio ricevuto dal client: " + message);

                out.println("Messaggio ricevuto dal server: " + message);

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}