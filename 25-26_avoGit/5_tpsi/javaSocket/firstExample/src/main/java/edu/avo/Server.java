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

                System.out.println("Client localAddress: " + clientSocket.getLocalAddress());
                System.out.println("Client localPort: " + clientSocket.getLocalPort());
                System.out.println("Client local socket address: " + clientSocket.getLocalSocketAddress());

                System.out.println("Client port: " + clientSocket.getPort());
                System.out.println("Client inetAddress: " + clientSocket.getInetAddress());
                System.out.println("Client remoteAddress: " + clientSocket.getRemoteSocketAddress());

                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String message = in.readLine();
                System.out.println("Messaggio ricevuto dal client: " + message);
                out.println("Messaggio ricevuto dal server: " + message);

                //lettura intero
                String receivedMessage = in.readLine();
                System.out.println("Server riceve numero intero: " + receivedMessage);
                System.out.println("tipo di dato " + receivedMessage.getClass());
                int num = Integer.parseInt(receivedMessage);
                System.out.println("conversione int: " + num);


                // Lettura float
                receivedMessage = in.readLine();
                System.out.println("Server riceve float: " + receivedMessage);
                System.out.println("tipo di dato " + receivedMessage.getClass());
                float numF = Float.parseFloat(receivedMessage);
                System.out.println("conversione float: " + numF);

                clientSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}