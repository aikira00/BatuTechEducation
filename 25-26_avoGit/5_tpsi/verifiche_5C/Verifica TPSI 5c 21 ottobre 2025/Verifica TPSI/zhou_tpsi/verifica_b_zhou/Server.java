/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verifica_b_zhou;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Admin
 */
public class Server {
    
    public static void main(String[] args) throws IOException 
    {
      new Server().runServer();  
    }    
        
     public  void runServer()throws IOException 
    {
      ServerSocket ss= new ServerSocket(8888); 
      Socket s;
      while(true)
      {
       s = ss.accept();
       new ServerThread(s).start();
      }      
 
    }
}
