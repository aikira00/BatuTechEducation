/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package edu.avo.clientverificaa;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author palma
 */
public class ClientVerificaA {

    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("127.0.0.1",60000);
        Map<String,String> db=new HashMap();
        db.put("user1", "pass1");
        db.put("user2", "pass2");
        Client client=new Client(socket);
        client.start();
    }
}
