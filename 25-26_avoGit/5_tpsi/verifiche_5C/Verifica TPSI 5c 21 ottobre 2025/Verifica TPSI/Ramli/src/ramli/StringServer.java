/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stringex;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RAMLI ADAM FILA B
 */
public class StringServer {
    public static void main(String[] args){
        try {
            Map<String, SessionString> SessionList = new ConcurrentHashMap<>();
            ServerSocket ss =  new ServerSocket(16384);
            Socket s;
            while(true){
                s = ss.accept();
                Thread sessioneStringhe = new SessionString(SessionList, true,s);
                sessioneStringhe.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(StringServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}
