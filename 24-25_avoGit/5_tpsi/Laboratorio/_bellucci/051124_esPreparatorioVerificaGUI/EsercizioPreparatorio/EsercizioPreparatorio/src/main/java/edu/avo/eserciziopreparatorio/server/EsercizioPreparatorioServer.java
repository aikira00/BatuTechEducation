/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package edu.avo.eserciziopreparatorio.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 *
 * @author palma
 */
public class EsercizioPreparatorioServer {
    public static void main(String[] args) throws IOException {
        ServerSocket socket=new ServerSocket(60000);
        Server server;        
        while(true){
            server=new Server(socket.accept());
        }
    }
}
