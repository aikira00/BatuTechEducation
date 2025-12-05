package edu.avo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

public class SingleServer implements Runnable {

    private Socket socket;
    private List<String> posti;
    private String titolo;

    public SingleServer(Socket socket, List<String> posti, String titolo){
        this.socket = socket;
        this.posti = posti;
        this.titolo = titolo;
    }

    public void run(){
        PrintWriter out;
        BufferedReader in;
        try {
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            SenderProtocolManager sender = new SenderProtocolManager(out);
            Application application = new Application(sender, posti, titolo);
            ReaderProtocolManager receiver = new ReaderProtocolManager(application);

            //client connesso, invia subito posti liberi
            application.performPostiLiberi();
            String message;
            while ((message = in.readLine()) != null && !"QUIT".equals(message.trim())) {
                receiver.consumeMessage(message.trim());
            }

            in.close();
            out.close();
            socket.close();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
