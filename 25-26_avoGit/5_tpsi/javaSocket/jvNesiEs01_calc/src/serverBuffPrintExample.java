import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class serverBuffPrintExample {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(5005);
            System.out.println("Server in ascolto sulla porta 5005");

            Socket client = serverSocket.accept();


            BufferedReader inClient = new BufferedReader(new InputStreamReader(client.getInputStream()));


            //lettura intero
            String receivedMessage = inClient.readLine();
            System.out.println("Server riceve numero intero: " + receivedMessage);
            System.out.println("tipo di dato " + receivedMessage.getClass());
            int num = Integer.parseInt(receivedMessage);
            System.out.println("conversione int: " + num);


            // Lettura float
            receivedMessage = inClient.readLine();
            System.out.println("Server riceve float: " + receivedMessage);
            System.out.println("tipo di dato " + receivedMessage.getClass());
            float numF = Float.parseFloat(receivedMessage);
            System.out.println("conversione float: " + numF);


            // Lettura stringa
            receivedMessage = inClient.readLine();
            System.out.println("Server riceve stringa: " + receivedMessage);


            client.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
