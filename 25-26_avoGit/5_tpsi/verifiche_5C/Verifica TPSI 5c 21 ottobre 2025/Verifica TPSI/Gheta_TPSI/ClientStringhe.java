/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verificagheta;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Admin
 */
public class ClientStringhe {
     public static void main(String[]args)throws IOException{
         Scanner input = new Scanner(System.in);
         while(true){
             try (Socket socket = new Socket("localhost", 5555)) {
                 PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 System.out.println("inserici comando e stringa (es: COUNT;abcdef): ");
                 String richiesta = input.nextLine();
                 out.println(richiesta);
                 
                 String risposta = in.readLine();
                 System.out.println("Risposta: " + risposta);
             }
         }
     }
}
