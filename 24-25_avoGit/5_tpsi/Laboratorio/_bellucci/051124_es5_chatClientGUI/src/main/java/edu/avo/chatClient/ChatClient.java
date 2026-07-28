package edu.avo.chatClient;

import edu.avo.gui.ChatGui;
import edu.avo.tcpiolibrary.MyReader;
import edu.avo.tcpiolibrary.MySender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatClient {

    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger(ChatClient.class.getName());
        logger.setLevel(Level.FINEST);
        Socket socket=new Socket("localhost", 60000);
        Client client;
        logger.log(Level.INFO, "Client avviato, conesso su: " + 60000);

        Map<String, SenderProtocolManager> users = new HashMap<String,SenderProtocolManager>();

        logger.log(Level.INFO, "In attesa di una connessione...");
        client=new Client(socket, Commands.USER_QUIT, logger);

    }
}
