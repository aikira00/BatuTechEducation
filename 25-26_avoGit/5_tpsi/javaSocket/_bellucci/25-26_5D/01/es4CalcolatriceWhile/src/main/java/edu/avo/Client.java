package edu.avo;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 60000);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        //aspetto che server mi dica benvenuto
        String benvenuto = in.readLine();
        System.out.println("Il server mi dice: " + benvenuto);

        //invio primo numero
        out.println(JOptionPane.showInputDialog("Inserisci il primo numero: "));
        String op = JOptionPane.showInputDialog("Inserisci +, -, *, :, =");
        while(!op.equals("=")) {
            //invio operazione
            out.println(op);
            //send second number
            out.println(JOptionPane.showInputDialog("Inserisci il secondo numero: "));
            op = JOptionPane.showInputDialog("Inserisci +, -, *, :, =");
        }
        //invio operazione =
        out.println(op);
        String res = in.readLine();
        JOptionPane.showMessageDialog(null, res);
        System.out.println("Il server mi dice: " + res);
    }

}
