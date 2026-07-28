/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.newserveraritmetico;

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
 * @author MULTI01
 */
public class Server {

    public Server(Socket socket, String toClose) {
        PrintWriter out=null;
        try {
            out = new PrintWriter(socket.getOutputStream());
            MySender sender=new MySender(out);
            BufferedReader reader=new BufferedReader
                (new InputStreamReader(socket.getInputStream()));
            SenderProtocolManager spm=new SenderProtocolManager(sender, toClose);
            ICommandConsumer app=new App(spm);
            ReaderProtocolManager rpm=new ReaderProtocolManager(app);
            MyReader myReader=new MyReader(reader, toClose, rpm);
            myReader.start();
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        } 
    }
    
}
