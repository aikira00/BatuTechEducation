package verificagheta;
// Gheta Raul Marco FILA B

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerStringhe {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5555);
        System.out.println("Server avviato sulla porta 5555...");

        while (true) {
            try (Socket socket = serverSocket.accept();
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

                System.out.println("Client connesso!");

                String richiesta = in.readLine();
                System.out.println("Ricevuto: " + richiesta);

                String risposta = StringProtocol.elabora(richiesta);
                out.println(risposta); 
                System.out.println("Risposta inviata: " + risposta);
            }
        }
    }
}
