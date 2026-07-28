package edu.avo.chatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger(ChatServer.class.getName());
        logger.setLevel(Level.FINEST);
        ServerSocket socket=new ServerSocket(60000);
        Server server;
        logger.log(Level.INFO, "Server avviato e in ascolto sulla porta: " + 60000);

        Map<String, SenderProtocolManager> users = new HashMap<String,SenderProtocolManager>();
        while(true){
            logger.log(Level.INFO, "In attesa di una connessione...");
            server=new Server(socket.accept(), Commands.USER_QUIT, users, logger);
        }

    }
}
