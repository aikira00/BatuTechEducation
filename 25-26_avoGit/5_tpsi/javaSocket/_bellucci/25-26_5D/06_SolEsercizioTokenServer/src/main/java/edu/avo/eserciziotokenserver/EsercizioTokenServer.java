/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package edu.avo.eserciziotokenserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author palma
 */
public class EsercizioTokenServer {

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(60000);
        Map<String, String> db = new HashMap();
        db.put("user1", "pass1");
        db.put("user2", "pass2");
        SingleServer single;
        Thread thread;
        while (true) {
            single = new SingleServer(server.accept(), db);
            thread = new Thread(single);
            thread.start();
        }
    }
}
