import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.DataOutputStream;
import java.net.Socket;

public class clientBuffPrintExample {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 5005);

            // Stampa e scrittura della stringa
            PrintWriter outServer = new PrintWriter(client.getOutputStream(), true);

            int numInt = 2450;
            System.out.println("Invio il numero intero: " + numInt);
            outServer.println(numInt);

            float numFloat = 3.14F;
            System.out.println("Invio il float: " + numFloat);
            outServer.println(numFloat);

            String message = "Hello, server!";
            System.out.println("Invio la stringa: " + message);
            outServer.println(message);

            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
