package edu.avo.echoServer;

import edu.avo.tcpiolibrary.MyReader;
import edu.avo.tcpiolibrary.MySender;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {

    public Server(Socket socket, String toClose, Logger logger) {
        PrintWriter out=null;
        try {
            out = new PrintWriter(socket.getOutputStream());
            MySender sender=new MySender(out, logger);
            BufferedReader reader=new BufferedReader
                    (new InputStreamReader(socket.getInputStream()));
            SenderProtocolManager spm=new SenderProtocolManager(sender, toClose);
            ICommandConsumer app=new App(spm);
            ReaderProtocolManager rpm=new ReaderProtocolManager(app);
            MyReader myReader=new MyReader(reader, toClose, rpm, logger);
            logger.log(Level.INFO, "Inizio my reader...");
            myReader.start();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

}