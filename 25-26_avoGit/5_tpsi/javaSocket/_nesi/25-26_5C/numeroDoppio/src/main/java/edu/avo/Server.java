package edu.avo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Server {
    public static void main(String[] args) throws IOException {
      System.out.println("Server starting...");
      ServerSocket serverSocket = new ServerSocket(60001);
      Socket client = null;
      while (true) {
          client = serverSocket.accept();
          //leggo numero
          int num = client.getInputStream().read();
          //stampa per debug
          System.out.println("Ho ricevuoto il numero " + num);
          //raddoppio numero, nessun controllo su dati inviati da client per ora
          num = num * 2;
          //invio numero
          client.getOutputStream().write(num);
          client.close();
      }
      //si dovrebbe chiudere il serversocket...vedremo poi
    }
}