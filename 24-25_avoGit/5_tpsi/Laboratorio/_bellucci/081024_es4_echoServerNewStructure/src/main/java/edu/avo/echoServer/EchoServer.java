package edu.avo.echoServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EchoServer {
    public static void main(String[] args) throws IOException {
        Logger logger = Logger.getLogger(EchoServer.class.getName());
        logger.setLevel(Level.FINEST);
        ServerSocket socket=new ServerSocket(60000);
        Server server;
        logger.log(Level.INFO, "Server avviato e in ascolto sulla porta: " + 60000);
        while(true){
            logger.log(Level.INFO, "In attesa di una connessione...");
            server=new Server(socket.accept(),Commands.CLOSE, logger);
        }
    }
}
