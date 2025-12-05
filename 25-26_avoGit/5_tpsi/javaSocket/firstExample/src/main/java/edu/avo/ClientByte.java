package edu.avo;


import java.io.*;
import java.net.*;

public class ClientByte {
    public static void main(String[] args) {
        try {
            Socket client = new Socket("localhost", 5005);
            //creo output stream per scrivere to server
            OutputStream out = client.getOutputStream();
            int num = 300;//(int)(Math.random()*10);
            System.out.println("Mando primo numero " + num + " al server");
            out.write(num);
            num = 265;//(int)(Math.random()*10);
            System.out.println("Mando secondo numero " + num + " al server");
            out.write(num);
            client.close();
        }
        catch(UnknownHostException exUH){
            System.out.println("An error occurred, host non conosciuto");
            exUH.printStackTrace();
        }
        catch(IOException exIO){
            System.out.println("An error occurred, I/O");
            exIO.printStackTrace();
        }
    }
}
