/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziopreparatorio.client;


import edu.avo.eserciziopreparatoriogui.Gui;
import edu.avo.tcpiolibrary.MyReader;
import edu.avo.tcpiolibrary.MySender;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.*;

/**
 *
 * @author palma
 */
public class Client {
    
    public Client(Socket socket){
        try {
            Logger logger = Logger.getLogger(EsercizioPreparatorioClient.class.getName());
            logger.setLevel(Level.FINEST);
            PrintWriter out=new PrintWriter(socket.getOutputStream());
            MySender sender=new MySender(out, logger);
            SenderProtocolManager spm=new SenderProtocolManager(sender, "Fine");
            Gui gui=new Gui("Rette");
            ICommandConsumer app=new App(spm, gui);
            ReaderProtocolManager rpm=new ReaderProtocolManager(app);
            BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            MyReader reader=new MyReader(in,"Fine",rpm, logger);
            reader.start();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}
