package edu.avo.servermatematico;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import edu.avo.tcpiolibrary.MessageReader;
import edu.avo.tcpiolibrary.MessageWriter;

public class ServerMatematico {
    public static void main(String[] args) throws IOException {
        ServerSocket ss = new ServerSocket(65000);
        Socket s = ss.accept();
        PrintWriter out = new PrintWriter(s.getOutputStream());
        MessageWriter writer = new MessageWriter(out, "quit" );
        MessageProtocolWriter writerProtocol = new MessageProtocolWriter(writer);
        Application app = new Application(writerProtocol);
        MessageProtocolReader protocolReader = new MessageProtocolReader(app, "quit");
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        MessageReader reader = new MessageReader(in, "quit", protocolReader);
        reader.start();
    }
}
