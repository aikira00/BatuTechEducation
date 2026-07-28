/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziopreparatorio.server;


import edu.avo.eserciziopreparatorio.client.EsercizioPreparatorioClient;
import edu.avo.tcpiolibrary.IMessageConsumer;
import edu.avo.tcpiolibrary.MyReader;
import edu.avo.tcpiolibrary.MySender;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author palma
 */
public class Server {

    public Server(Socket socket){
        try {
            Logger logger = Logger.getLogger(EsercizioPreparatorioClient.class.getName());
            logger.setLevel(Level.FINEST);
            PrintWriter out=new PrintWriter(socket.getOutputStream());
            MySender sender=new MySender(out, logger);
            SenderProtocolManager spm=new SenderProtocolManager(sender, "Fine");
            App app=new App(spm);
            IMessageConsumer rpm=new ReaderProtocolManager(app);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            MyReader reader=new MyReader(in,"Fine",rpm, logger);
            reader.start();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
