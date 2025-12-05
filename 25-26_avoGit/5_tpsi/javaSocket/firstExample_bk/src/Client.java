import java.io.*;
import java.net.*;

public class Client {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 60000);

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