/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package edu.avo.eserciziopersistenza;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author MULTI01
 */
public class EsercizioPersistenza {

    public static void main(String[] args) throws IOException {
        ServerSocket socket=new ServerSocket(60000);
        Map<String,Integer> tokens=new HashMap<>(9);
        while (true){
            new MyServer(socket.accept(),"quit",new FileServer("db"),tokens );
        }
    }
}
