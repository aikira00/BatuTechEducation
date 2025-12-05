import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 60000);


            System.out.println("Client localAddress: " + socket.getLocalAddress());
            System.out.println("Client localPort: " + socket.getLocalPort());
            System.out.println("Client local socket address: " + socket.getLocalSocketAddress());

            System.out.println("Client port: " + socket.getPort());
            System.out.println("Client inetAddress: " + socket.getInetAddress());
            System.out.println("Client remoteAddress: " + socket.getRemoteSocketAddress());

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.print("Inserisci un messaggio da inviare al server: ");
            String message = userInput.readLine();
            out.println(message);

            int numInt = 2450;
            System.out.println("Invio il numero intero: " + numInt);
            out.println(numInt);

            float numFloat = 3.14F;
            System.out.println("Invio il float: " + numFloat);
            out.println(numFloat);

            String response = in.readLine();
            System.out.println(response);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}