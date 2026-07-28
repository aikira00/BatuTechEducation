
import java.io.*;
import java.net.*;

public class es1EchoClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 6000);
            System.out.println("Porta remota: " + socket.getPort());
            System.out.println("Porta locale: " + socket.getLocalPort());
            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            System.out.print("Inserisci un messaggio da inviare al server: ");
            String message = userInput.readLine();

            out.println(message);

            String response = in.readLine();
            System.out.println(response);

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
