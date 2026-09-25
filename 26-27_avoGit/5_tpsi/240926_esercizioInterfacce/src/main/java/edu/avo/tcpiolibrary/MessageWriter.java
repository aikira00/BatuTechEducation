package edu.avo.tcpiolibrary;

import java.io.PrintWriter;
public class MessageWriter {
    private PrintWriter writer;
    private String forClose;

    public MessageWriter(PrintWriter writer, String forClose){
        this.writer = writer;
        this.forClose = forClose;
    }

    public void sendMessage(String message){
        writer.println(message);
        // non so se è stato impostato il true per flush, lo sforzo
        writer.flush();
    }

    public void close(){
        writer.println(forClose);
        writer.close();
    }
}
