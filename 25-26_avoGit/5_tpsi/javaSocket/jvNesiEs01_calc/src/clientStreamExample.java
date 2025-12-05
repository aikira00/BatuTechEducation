import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class clientStreamExample {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 5005);
            //creo output stream per scrivere to server
            OutputStream out = client.getOutputStream();
            int num = (int)(Math.random()*10);
            System.out.println("Mando un numero di 1 byte al server");
            out.write(num);
            System.out.println("Mando un numero molto grande al server");
            num = 320; // binario 101000000
            out.write(num);
            out.flush();  // Forza l'invio dei dati

            // mandiamo un float, 4 bytes
            float numFloat = 3.5F;

            // Converte il float in un array di byte
            byte[] floatBytes = ByteBuffer.allocate(4).putFloat(numFloat).array();
            System.out.println("Mando un float al server, un flusso di 4 bytes");
            // Scrive i byte nel socket
            out.write(floatBytes);
            out.flush();  // Forza l'invio dei dati

            //INVIO UN CARATTERE ASCII compatibile UTF-8 0...255
            // Carattere da inviare
            char character = 'A'; // ASCII value 65
            System.out.println("Mando il carattere: " + character);

            // Converti il carattere in un byte e invialo
            out.write((int) character);

            out.flush(); // Assicura che tutti i dati siano inviati

            //INVIO UN CARATTERE ASCII compatibile UTF-8 0...255
            // Carattere da inviare
            character = 'ñ'; // UTF 8 value <255
            System.out.println("Mando il carattere: " + character);

            // Converti il carattere in un byte e invialo
            out.write((int) character);

            out.flush(); // Assicura che tutti i dati siano inviati


            //INVIO UN CARATTERE ASCII compatibile UTF-8 0...255
            // Carattere da inviare
            character = 'ᕊ'; // UTF 8 value >255
            System.out.println("Mando il carattere: " + character);

            // Converti il carattere in un byte e invialo
            out.write((int) character);

            out.flush(); // Assicura che tutti i dati siano inviati

            // Invia una stringa
            String message = "Hello, server! ᕊ";
            System.out.println("Mando il messaggio: " + message);
            byte[] messageBytes = message.getBytes();
            //byte[] messageBytes = message.getBytes("UTF-8");
            // Invia la lunghezza della stringa prima dei dati !!!!!!!!!!
            out.write(messageBytes.length);
            out.write(messageBytes);
            out.flush();  // Forza l'invio dei dati
            client.close();
        }
        catch(UnknownHostException exUH){
            System.out.println("An error occurred, host non conosciuto");
            exUH.printStackTrace();
        }
        catch(IOException exIO){
            System.out.println("An error occurred, I/O");
            exIO.printStackTrace();
        }
    }
}
