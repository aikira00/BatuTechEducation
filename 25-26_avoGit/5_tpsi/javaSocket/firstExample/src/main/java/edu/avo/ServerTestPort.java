package edu.avo;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerTestPort {
    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(60000);
        System.out.println("Server in ascolto sulla porta 60000\n");

    // Primo client
        Socket client1 = serverSocket.accept();
        System.out.println("=== CLIENT 1 ===");
        System.out.println("Porta locale server: " + client1.getLocalPort()); // Sempre 60000
        System.out.println("Porta remota client: " + client1.getPort()); // Effimera (es. 54321)
        System.out.println("Quintupla: " + client1.getLocalSocketAddress() + " <-> " + client1.getRemoteSocketAddress());
        System.out.println("Inet: " + client1.getInetAddress() );

    // NON chiudo client1, così resta attivo

    // Secondo client (ServerSocket torna in accept)
        Socket client2 = serverSocket.accept();
        System.out.println("\n=== CLIENT 2 ===");
        System.out.println("Porta locale server: " + client2.getLocalPort()); // Ancora 60000!
        System.out.println("Porta remota client: " + client2.getPort()); // Effimera diversa (es. 54322)
        System.out.println("Quintupla: " + client2.getLocalSocketAddress() + " <-> " + client2.getRemoteSocketAddress());
        System.out.println("Inet: " + client1.getInetAddress() );


// Terzo client
        Socket client3 = serverSocket.accept();
        System.out.println("\n=== CLIENT 3 ===");
        System.out.println("Porta locale server: " + client3.getLocalPort()); // Sempre 60000!
        System.out.println("Porta remota client: " + client3.getPort());
        System.out.println("Inet: " + client1.getInetAddress() );

        client1.close();
        client2.close();
        client3.close();
        serverSocket.close();
    }
}
