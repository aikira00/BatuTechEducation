package edu.avo.banca;

import edu.avo.dblibrary.PersistenzaDb;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SimulazioneBanca {

    // XAMPP: utente root senza password, db creato con bancaA.sql
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/Banca?user=root&password=";

    public static void main(String[] args) throws IOException {
        ServerSocket socket = new ServerSocket(60000);   // la 5000 su macOS è del Ricevitore AirPlay
        Map<String, Integer> tokens = new ConcurrentHashMap<>();   // condivisa fra i thread dei client
        while (true) {
            Socket client = socket.accept();
            try {
                // una PersistenzaDb per client: non è pensata per più thread
                new MyServer(client, "Logout", new PersistenzaDb(CONNECTION_STRING), tokens);
            } catch (IOException | RuntimeException ex) {
                // es. db spento: si chiude solo questa connessione, il server resta su
                System.err.println("Client non servito: " + ex.getMessage());
                client.close();
            }
        }
    }
}
