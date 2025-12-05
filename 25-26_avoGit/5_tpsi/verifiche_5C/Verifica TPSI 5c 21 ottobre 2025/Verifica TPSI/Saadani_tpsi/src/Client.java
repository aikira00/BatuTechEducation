
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * Saadani Rayan 5CI FILA B
 */
public class Client {
   public static void main(String[]args)throws IOException
   {
       Socket s = new Socket("localHost",10000);
       PrintWriter out = new PrintWriter(s.getOutputStream(),true);
       out.println("COUNT;abcdefgh");
       InputStreamReader isr = new InputStreamReader(s.getInputStream());
       BufferedReader in = new BufferedReader(isr);
       String risposta = in.readLine();
       System.out.println("il client riceve: " +risposta);
   }
    
}
