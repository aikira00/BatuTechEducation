package edu.avo.echoServer;

import edu.avo.tcpiolibrary.MySender;

public class SenderProtocolManager {
    private MySender sender;
    private String toClose;

    public SenderProtocolManager(MySender sender, String toClose) {
        this.sender = sender;
        this.toClose = toClose;
    }

    public void echoMessage(String message){
        sender.send("SERVER RECEIVED => " + message);
    }

    public void close(){
        sender.send(toClose);
        sender.close(); // close the stream
    }
}
