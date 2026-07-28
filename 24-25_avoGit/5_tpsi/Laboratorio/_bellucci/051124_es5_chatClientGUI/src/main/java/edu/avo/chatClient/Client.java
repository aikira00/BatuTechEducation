package edu.avo.chatClient;

import edu.avo.gui.ChatGui;
import edu.avo.tcpiolibrary.MyReader;
import edu.avo.tcpiolibrary.MySender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public Client(Socket socket, String toClose, Logger logger){
        PrintWriter out = null;
        try{
            String userId = socket.getLocalPort() +  "@" + socket.getInetAddress().getHostAddress();
            logger.log(Level.INFO, "My user id " + userId);


            out = new PrintWriter(socket.getOutputStream(), true);
            MySender sender = new MySender(out, logger);
            SenderProtocolManager spm = new SenderProtocolManager(sender, toClose);
            ChatGui appGui = new ChatGui("Gui App di " + userId);
            ICommandConsumer app = new App(spm, appGui, logger);

            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            ReaderProtocolManager rpm=new ReaderProtocolManager(app);
            MyReader myReader=new MyReader(in, toClose, rpm, logger);
            logger.log(Level.INFO, "Inizio my reader...");
            myReader.start();
        }
        catch(IOException ex){
            throw new RuntimeException(ex);
        }

    }
}
