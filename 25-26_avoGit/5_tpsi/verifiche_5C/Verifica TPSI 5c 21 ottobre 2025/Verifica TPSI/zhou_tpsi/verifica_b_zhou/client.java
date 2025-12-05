/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verifica_b_zhou;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Admin
 */
public class client {
    
    public static void main(String[] args) throws IOException 
    {
    try{
    Socket s= new Socket("localhost",8888);
    s.getOutputStream().write((char)30);
    System.out.println(s.getInputStream().read());
    s.close();
    
    }catch(UnknownHostException e)
    {
     e.printStackTrace();
    
    }catch(IOException e)
    {
     e.printStackTrace();
    }
    }
    
    
}
