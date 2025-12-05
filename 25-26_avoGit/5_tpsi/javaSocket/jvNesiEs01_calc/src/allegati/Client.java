/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package allegati;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Scanner;

/**
 *
 * @author MULTI01
 */
public class Client {
    public static void main(String[] args) throws IOException{
        try{
            Socket s = new Socket("Localhost", 7890);
            while(true){// qui while inutile, devo spostare anche creazione di socket o gestire accept server in altro while
                Scanner scanner = new Scanner(System.in);
                System.out.print("Inserisci il valore in euro: ");
                String num = scanner.nextLine();
                //s.getOutputStream().wr
                PrintWriter out= new PrintWriter(s.getOutputStream(), true);
                out.println(num);

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}