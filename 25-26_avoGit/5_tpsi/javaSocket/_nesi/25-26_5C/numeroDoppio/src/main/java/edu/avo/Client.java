package edu.avo;

import javax.swing.*;
import java.io.IOException;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Client starting...");
        Socket client = new Socket("127.0.0.1", 60001);
        String num = JOptionPane.showInputDialog("Inserisci un numero");
        //invio numero al server
        client.getOutputStream().write(Integer.parseInt(num));
        //aspetto numero
        int numeroDoppio = client.getInputStream().read();
        System.out.println("Numero: " + numeroDoppio);
        JOptionPane.showMessageDialog(null, numeroDoppio);
        client.close();

        //si dovrebbe chiudere il serversocket...vedremo poi
    }
}