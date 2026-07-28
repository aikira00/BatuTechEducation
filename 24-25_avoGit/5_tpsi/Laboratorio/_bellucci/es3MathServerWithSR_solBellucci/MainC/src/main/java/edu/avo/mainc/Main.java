/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package edu.avo.mainc;

import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author palma
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String address = JOptionPane.showInputDialog("Insert address");
        int port = Integer.parseInt(JOptionPane.showInputDialog("Insert port number"));
        MyClient client = new MyClient(address, port);
        client.start();
    }
}
