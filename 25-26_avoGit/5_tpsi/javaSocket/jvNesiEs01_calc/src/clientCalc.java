import java.io.IOException;
import java.io.OutputStream;
import java.net.UnknownHostException;
import java.net.*;
public class clientCalc {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 5005);
            //creo output stream per scrivere to server
            OutputStream out = client.getOutputStream();
            int num = (int)(Math.random()*10);
            System.out.println("Mando primo numero " + num + " al server");
            out.write(num);
            num = (int)(Math.random()*10);
            System.out.println("Mando secondo numero " + num + " al server");
            out.write(num);
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