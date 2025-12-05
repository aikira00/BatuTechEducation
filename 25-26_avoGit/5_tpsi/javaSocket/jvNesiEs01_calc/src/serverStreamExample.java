import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;

public class serverStreamExample {
    public static void main(String[] args) {
        try {
            ServerSocket ss = new ServerSocket(5005);
            while(true) {
                System.out.println("ServerSocket ready");
                Socket client = ss.accept();
                //creo input stream per leggere dati client
                InputStream inClient = client.getInputStream();
                int numOne = inClient.read();
                System.out.println("Server riceve numero 1 byte int: " + numOne);
                numOne = inClient.read(); //riceve 101000000
                System.out.println("Server riceve un numero molto grande: " + numOne);

                //leggere valore float
                byte[] floatBytes = new byte[4];
                inClient.read(floatBytes);
                // Converte i byte ricevuti in un float
                float numFloatOne = ByteBuffer.wrap(floatBytes).getFloat();
                System.out.println("Server riceve numero 4 byte float " + numFloatOne);

                //ricevo ASCII compatibile utf-8 0...255
                // Leggi un byte e converti in carattere
                int receivedByte = inClient.read();
                char receivedChar = (char) receivedByte;
                System.out.println("Server riceve il carattere: " + receivedChar);

                //ricevo ASCII compatibile utf-8 0...255
                // Leggi un byte e converti in carattere
                receivedByte = inClient.read();
                receivedChar = (char) receivedByte;
                System.out.println("Server riceve il carattere utf-8 ñ: " + receivedChar);

                //ricevo ASCII NON compatibile utf-8 0...255
                // Leggi un byte e converti in carattere
                receivedByte = inClient.read();
                receivedChar = (char) receivedByte;
                System.out.println("Server riceve il carattere utf-8 ᕊ: " + receivedChar);

                // Ricevi una stringa
                int messageLength = inClient.read(); // Leggi la lunghezza della stringa
                byte[] messageBytes = new byte[messageLength];
                inClient.read(messageBytes); // Leggi i byte della stringa
                String receivedMessage = new String(messageBytes);
                //receivedMessage = new String(messageBytes, "UTF-8");
                System.out.println("Server riceve stringa: " + receivedMessage);

                client.close();
            }

        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }
}
