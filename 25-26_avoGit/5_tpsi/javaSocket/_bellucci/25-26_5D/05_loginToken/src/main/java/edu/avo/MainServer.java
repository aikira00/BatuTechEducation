package edu.avo;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public class MainServer {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(9080);
        System.out.println("Server avviato sulla porta 9080");

        //carico hashmap da file di configurazione, finto DB?
        HashMap<String, String> credentials = new HashMap<>();
        credentials.put("username", "admin");
        credentials.put("user1", "user1pwd");
        credentials.put("user2", "user2pwd");

        while(true){
            Socket socket = ss.accept();
            System.out.println("Nuova connessione accettata");
            // passo socket al thread insieme a mappa utente/password
            SingleServer server = new SingleServer(socket, credentials);
            new Thread(server).start();
        }
        //ss.close();

    }
}