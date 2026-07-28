/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package edu.avo.mains;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author palma
 */
public class Main {

    public static void main(String[] args) throws IOException {
        int port = Integer.parseInt(JOptionPane.showInputDialog("Insert port number"));
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();
        MyServer myServer = new MyServer(socket);
        myServer.start();
    }
}
