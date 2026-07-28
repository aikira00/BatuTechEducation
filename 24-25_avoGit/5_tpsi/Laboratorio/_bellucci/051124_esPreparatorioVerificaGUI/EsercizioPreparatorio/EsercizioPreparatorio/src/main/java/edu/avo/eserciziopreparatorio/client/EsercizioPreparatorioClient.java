/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package edu.avo.eserciziopreparatorio.client;

import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author palma
 */
public class EsercizioPreparatorioClient {

    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("localhost",60000);
        Client client=new Client(socket);
        
    }
}
