/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package edu.avo.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author MULTI01
 */
public class Server {

    public static void main(String[] args) throws InterruptedException, IOException {
        ServerSocket server=new ServerSocket(60000);
        System.out.println("Server in attesa di connessioni...");
        int k=2;
        int n=1250000;
        SingleServer [] clients=new SingleServer[16];
        for(int i=0; i<clients.length;i++){
            clients[i]=new SingleServer(server.accept(), k, n);
            System.out.println("Connesso server n°"+(i+1));
            k=n+1;
            n=n+1250000;            
        }
        long t1=System.currentTimeMillis();
        System.out.println("Avvio calcolo sui client");
        for (int i = 0; i < clients.length; i++) {            
            clients[i].start();
        }
        for (int i = 0; i < clients.length; i++) {            
            clients[i].join();
        }
        System.out.println((System.currentTimeMillis()-t1)/1000);
        /*System.out.println("Stampa dei dati in arrivo dai client");
        for(int i=0; i<clients.length;i++){
            System.out.println("Dati del client "+(i+1));
            System.out.println(clients[i].getList());
        }*/
        server.close();
    }
}
