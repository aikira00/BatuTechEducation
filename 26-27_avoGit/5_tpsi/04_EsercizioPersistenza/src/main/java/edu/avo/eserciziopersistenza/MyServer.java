/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziopersistenza;

import edu.avo.tcpiolibrary.MyReader;
import edu.avo.tcpiolibrary.MySender;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;

/**
 *
 * @author MULTI01
 */
public class MyServer {
    
    public MyServer(Socket socket, String forClose, Persistence db, Map<String,Integer> tokens) throws IOException{
        PrintWriter out=new PrintWriter(socket.getOutputStream());
        MySender sender=new MySender(out);
        SenderProtocolManager spm=new SenderProtocolManager(sender, forClose);
        CommandConsumer app=new Application(spm,db, tokens);
        ReaderProtocolManager rpm=new ReaderProtocolManager(app);
        BufferedReader in=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        MyReader reader=new MyReader(in, rpm, forClose);
        reader.start();
        app.performLogin();
    }
}
