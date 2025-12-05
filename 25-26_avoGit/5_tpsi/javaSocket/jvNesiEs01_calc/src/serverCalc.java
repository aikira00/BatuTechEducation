import java.io.*;
import java.net.*;

public class serverCalc {

    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5005);
            while(true) {
                System.out.println("ServerSocket ready");
                Socket client = ss.accept();
                //creo input stream per leggere dati client
                InputStream inClient = client.getInputStream();
                int numOne = inClient.read();
                System.out.println("Server riceve primo operando: " + numOne);
                int numTwo = inClient.read();
                System.out.println("Server riceve secondo operando: " + numTwo);
                client.close();
            }

        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }
}
