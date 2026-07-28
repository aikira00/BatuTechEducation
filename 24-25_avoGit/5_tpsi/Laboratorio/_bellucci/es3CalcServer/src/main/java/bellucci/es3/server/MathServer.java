package bellucci.es3.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MathServer {

    public static String[] split(String line){
        String[] tokens = new String[3];
        for(int i = 0; i<line.length(); i++){
            //line.charAt(i)<'0' || line.charAt(i)>'9' migliore??
            if(line.charAt(i)<'0'){
                tokens[0] = line.substring(0, i);
                tokens[1] = line.substring(i,i+1);
                tokens[2] = line.substring(i+1);
                break;
            }
        }
        return tokens;
    }

    public static String computeMath(String line){
        int n = 0;
        int m = 0;
        int result = 0;

        //splitto
        String[] tokens = split(line);

        //valido numeri
        try {
            n = Integer.parseInt(tokens[0]);
            m = Integer.parseInt(tokens[2]);
        }
        catch(NumberFormatException e){
            System.out.println("Invalid number");
            return "";
        }

        //calcolo
        switch(tokens[1]){
            case "+":
                result = n + m;
                break;
            case "-":
                result = n - m;
                break;
            case "*":
                result = n * m;
                break;
            case "/":
                result = n / m;
                break;
            default:
                System.out.println("Invalid operation");
                return "";
        }
        return String.valueOf(result);
    }

    public static void main(String[] args)  {
        ServerSocket ss = null;
        Socket s = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try{
            //avvio server porta 60000
            ss  = new ServerSocket(60000);
            //accetto connessione da client
            s = ss.accept();

            //preparo gli streams
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);

            out.println("Benvenuto nel math server! Invia il calcolo, sono pronto!");
            //leggo stringa CC...OP...CCC
            String line = in.readLine();
            System.out.println("Ricevuto: " + line);
            String result = "";
            while(line!= null && !line.equalsIgnoreCase("quit")){
                result = line + "=" + computeMath(line);
                System.out.println("Calcolato: " + result);
                //mando risultato al client
                out.println(result);
                //aspetto altra richiesta di calcolo
                line = in.readLine();
            }

            System.out.println("Ricevuto quit dal client? " + line);

            in.close();
            out.close();
            s.close();

        }
        catch(IOException e){
            System.out.println("Server, exception occured " + e.getMessage());
        }
        finally {
            // Chiusura delle risorse
            try {
                if (in != null) in.close();
                if (out != null) out.close();
                if (s != null) s.close();
                if (ss != null) ss.close();
            } catch (IOException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}