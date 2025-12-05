package edu.avo;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.IOException;

public class Client {
    public static void main(String[] args) throws IOException{

        Socket clientSocket = new Socket("127.0.0.1", 60000);
        System.out.println("Client connected to the server " + clientSocket.getInetAddress());

        //creo stream per scrittura sul client
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

        //creo stream per lettura dal client
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Sending message to the server...");
        String message = null;
        while(!"QUIT".equalsIgnoreCase(message = JOptionPane.showInputDialog("Inserisci CC...OPCC.."))){
            System.out.println("Message sent: " + message);
            out.println(message);

            //recupero risultato dal server
            String result = in.readLine();
            System.out.println("Message received: " + result);
            JOptionPane.showMessageDialog(null, result);
        }
        //chiudo comunicazione
        System.out.println("Mando QUIT al server");
        out.println(message);
        clientSocket.close();
    }
}
