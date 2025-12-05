package bellucci.es2.client;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
    public static void main(String[] args) throws IOException, UnknownHostException {
        System.out.println("Hello world!");
        Socket socket = new Socket("localhost", 60000);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String line = in.readLine();
        JOptionPane.showMessageDialog(null, line);
        line = JOptionPane.showInputDialog("Inserire il testo (quit per terminare)");
        while (line != null && !line.equalsIgnoreCase("quit")) {
            out.println(line);
            System.out.println("Client: scrivo " + line);
            line = in.readLine();
            System.out.println("Client: ricevo " + line);
            JOptionPane.showMessageDialog(null, line);
            line = JOptionPane.showInputDialog("Inserire il testo (quit per terminare)");
        }

        System.out.println("Ricevuto quit? " + line);
        in.close();
        out.close();
        socket.close();
    }
}