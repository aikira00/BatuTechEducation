/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package edu.avo.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author MULTI01
 */
public class Client {

    public static void main(String[] args) throws IOException, InterruptedException {
        Socket s=new Socket("localhost",60000);
        PrintWriter out=new PrintWriter(s.getOutputStream(),true);
        BufferedReader in=new BufferedReader(new InputStreamReader(s.getInputStream()));
        System.out.println("In attesa di inizare il calcolo...");
        String intervallo=in.readLine();
        String [] array=intervallo.split(" ");
        int min=Integer.parseInt(array[0]);
        int max=Integer.parseInt(array[1]);
        Crivello c=new Crivello(min,max);
        System.out.println("Inizia il calcolo...");
        c.start();
        c.join();
        System.out.println("Calcolo concluso...");
        List<Integer> primi=c.getPrimi();
        System.out.println("Numero di primi: "+primi.size()+" Intervallo["+min+";"+max+"]");
        String result="";
        for(int valore:primi){
            result+=valore+",";
        }
        result=result.substring(0,result.length()-1);
        out.println(result);
        System.out.println("Dati inviati al server");
        out.close();
        in.close();
        s.close();
    }
}
