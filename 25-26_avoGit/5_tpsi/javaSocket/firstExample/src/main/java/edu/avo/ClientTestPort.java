package edu.avo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientTestPort {
    public static void main(String[] args) throws Exception {
        Socket socket1 = new Socket("localhost", 60000);
        Socket socket2 = new Socket("localhost", 60000);
        Socket socket3 = new Socket("localhost", 60000);


        System.out.println("Client localAddress: " + socket1.getLocalAddress());
        System.out.println("Client localPort: " + socket1.getLocalPort());
        System.out.println("Client local socket address: " + socket1.getLocalSocketAddress());

        System.out.println("Client port: " + socket1.getPort());
        System.out.println("Client inetAddress: " + socket1.getInetAddress());
        System.out.println("Client remoteAddress: " + socket1.getRemoteSocketAddress());

        System.out.println("Client localAddress: " + socket1.getLocalAddress());
        System.out.println("Client localPort: " + socket1.getLocalPort());
        System.out.println("Client local socket address: " + socket1.getLocalSocketAddress());

        System.out.println("Client port: " + socket2.getPort());
        System.out.println("Client inetAddress: " + socket2.getInetAddress());
        System.out.println("Client remoteAddress: " + socket2.getRemoteSocketAddress());

        System.out.println("Client localAddress: " + socket2.getLocalAddress());
        System.out.println("Client localPort: " + socket2.getLocalPort());
        System.out.println("Client local socket address: " + socket2.getLocalSocketAddress());

        System.out.println("Client port: " + socket3.getPort());
        System.out.println("Client inetAddress: " + socket3.getInetAddress());
        System.out.println("Client remoteAddress: " + socket3.getRemoteSocketAddress());

        socket1.close();
        socket2.close();
        socket3.close();


    }
}
