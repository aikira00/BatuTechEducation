package bellucci.es3.client;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MathClient {

    public static boolean validateNumber(String number){
        try{
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validateOperator(String operator){

        if(operator.equals("+") || operator.equals("-") || operator.equals("*")|| operator.equals("/")) {
            return true;
        }
        return false;

    }

    public static String getUserInput() throws IOException {
        //leggo valori in input
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Inserisci primo numero: ");
        String firstNumber = in.readLine();
        while(!validateNumber(firstNumber)){
            System.out.println("Numero invalido, inserisci numero valido");
            firstNumber = in.readLine();
        }
        System.out.println("Inserisci secondo numero: ");
        String secondNumber = in.readLine();
        while(!validateNumber(secondNumber)){
            System.out.println("Numero invalido, inserisci numero valido");
            secondNumber = in.readLine();
        }
        System.out.println("Inserisci operatore, valori ammessi [+, -, *, /]: ");
        String operator = in.readLine();
        while(!validateOperator(operator)){
            System.out.println("operatore invalido, inserisci operatore  valido - valori ammessi [+, -, *, /]");
            operator = in.readLine();
        }

        return firstNumber + operator + secondNumber;
    }


    public static void main(String[] args)  {

        System.out.println("Client invia stringa CCCC...OPCC...");
        BufferedReader in = null;
        PrintWriter out = null;
        Socket s = null;
        String calcToSend = "";
        try{

            //contatto server
            s = new Socket("127.0.0.1", 60000);
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            out = new PrintWriter(s.getOutputStream(), true);

            String line = in.readLine();
            JOptionPane.showMessageDialog(null, line);
            line = "";
            while(line != null && !line.equalsIgnoreCase("quit")){
                calcToSend = getUserInput();
                //invioo calcToSEnd al server
                System.out.println("Invio calcolo al server" + calcToSend);
                out.println(calcToSend);
                //leggo calcolo
                line = in.readLine();
                System.out.println("server inviato" + line);
                JOptionPane.showMessageDialog(null, line);
                line = JOptionPane.showInputDialog("Inserire il testo (quit per terminare)"); //TODO rivedere meglio qui! più carino se torna a chiedere input utente qui
            }

            System.out.println("Ricevuto quit? " + line);
            System.out.println("Mando quit al server " + line);
            out.println(line);
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
            } catch (IOException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }

    }
}