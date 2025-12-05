package edu.avo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Server {

    private static boolean isSymbol(char c){
        return c < '0' || c > '9';
    }
    /**
     * Scompone una stringa contenente un'espressione matematica in tre parti:
     * primo numero, operatore e secondo numero.
     *
     * @param message la stringa contenente l'espressione matematica nel formato "numero operatore numero"
     *                (ad esempio: "5+3", "10-2", "7*4", "8/2")
     * @return un array di String contenente tre elementi:
     * - [0]: il primo numero come stringa
     * - [1]: l'operatore matematico come stringa
     * - [2]: il secondo numero come stringa
     * Restituisce null se non viene trovato alcun operatore valido
     *
     */
    private static String[] splitMessage(String message) {
        for (int i = 0; i < message.length(); i++) {
            if (isSymbol(message.charAt(i))) {
                return new String[]{message.substring(0, i), message.substring(i, i+1), message.substring(i + 1)};
            }
        }
        return null;
    }

    /**
     * Calcola il risultato di un'espressione matematica semplice e restituisce
     * una stringa formattata con l'operazione e il risultato.
     *
     * @param message la stringa contenente l'espressione matematica nel formato "numero operatore numero"
     *                Operatori supportati: +, -, *, /
     * @return una stringa formattata contenente l'espressione originale seguita da " = " e il risultato
     * Se l'operatore non è riconosciuto, restituisce l'espressione seguita da " = Invalid operation"
     */

    private static String compute(String message) {
        String result = message + " = ";
        String[] parts = splitMessage(message);
        float numberOne = Float.parseFloat(parts[0]);
        float numberTwo = Float.parseFloat(parts[2]);
        String op = parts[1];
        switch (op) {
            case "+":
                result += (numberOne + numberTwo);
                break;
            case "-":
                result += (numberOne - numberTwo);
                break;
            case "*":
                result += (numberOne * numberTwo);
                break;
            case "/":
                result += (numberOne / numberTwo);
                break;
            default:
                result += "Invalid operation";
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(60000);
        Socket clientSocket = serverSocket.accept();
        System.out.println("Client connected to the server " + clientSocket.getInetAddress());

        //creo stream per scrittura sul client
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        //creo stream per lettura dal client
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Waiting for message from client");
        //server riceve stringa fino a che client manda "quit"
        String receivedMessage = null;
        String result = null;
        while (!"QUIT".equalsIgnoreCase(receivedMessage = in.readLine())) {
            System.out.println("Message received: " + receivedMessage);
            out.println(compute(receivedMessage));
        }
        System.out.println("received QUIT! Closing connection");
        clientSocket.close();
        serverSocket.close();
    }
}