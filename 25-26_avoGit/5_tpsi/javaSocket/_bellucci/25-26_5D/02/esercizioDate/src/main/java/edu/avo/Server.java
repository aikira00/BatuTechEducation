package edu.avo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.TextStyle;
import java.util.Date;
import java.util.Locale;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Server {
    /* server riceve
    TEMPOPASSATO AAAA-MM-GG => TEMPOPASSATO N (dove N numero giorni)
    GIORNOSETTIMANA AAAA-MM-GG => GIORNOSETTIMANA Giorno
    DIFFERENZA AAAA-MM-GG AAAA-MM-GG=> DIFFERENZA N (dove N numero giorni)
    QUIT chiudo connessione
     */
    public static String processMessage(String message){
        System.out.println("Message processed: " + message);
        String[] parts = message.split(" ");
        String result = null;

        switch (parts[0]){
            case "TEMPOPASSATO":
                result = "TEMPOPASSATO " + Period.between(LocalDate.parse(parts[1]),LocalDate.now()).getDays();
                break;
            case "GIORNOSETTIMANA":
                result = "GIORNOSETTIMANA " + LocalDate.parse(parts[1]).getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.ITALY);
                break;
            case "DIFFERENZA":
                result = "DIFFERENZA " + Period.between(LocalDate.parse(parts[1]),LocalDate.parse(parts[2])).getDays();
                break;
            default:
                result = message + " ERRRRRorE non valido";
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
            out.println(processMessage(receivedMessage));
        }
        System.out.println("received QUIT! Closing connection");
        clientSocket.close();
        serverSocket.close();
    }
}