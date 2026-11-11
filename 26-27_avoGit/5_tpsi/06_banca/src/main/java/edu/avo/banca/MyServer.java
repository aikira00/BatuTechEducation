package edu.avo.banca;

import edu.avo.dblibrary.Persistenza;
import edu.avo.tcpiolibrary.MessageReader;
import edu.avo.tcpiolibrary.MessageWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class MyServer {

    public MyServer(Socket socket, String forClose, Persistenza db, Map<String, Integer> tokens) throws IOException {
        // UTF-8 esplicito: ° e «già» non sono ASCII
        PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true);
        MessageWriter sender = new MessageWriter(out, forClose);
        SenderProtocolManager spm = new SenderProtocolManager(sender);
        CommandConsumer app = new Application(spm, db, tokens);
        ReaderProtocolManager rpm = new ReaderProtocolManager(app);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        MessageReader reader = new MessageReader(in, forClose, rpm);
        reader.start();
    }
}
