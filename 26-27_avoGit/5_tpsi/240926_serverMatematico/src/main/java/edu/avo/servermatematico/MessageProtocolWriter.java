package edu.avo.servermatematico;

import edu.avo.tcpiolibrary.MessageWriter;

public class MessageProtocolWriter {
    private MessageWriter writer;

    public MessageProtocolWriter(MessageWriter writer){
        this.writer = writer;
    }

    public void add(int n1, int n2, int result){
        writer.sendMessage(n1+"+"+n2+"="+result);
    }

    public void sub(int n1, int n2, int result){
        writer.sendMessage(n1+"-"+n2+"="+result);
    }

    public void mul(int n1, int n2, int result){
        writer.sendMessage(n1+"*"+n2+"="+result);
    }

    public void error(){
        writer.sendMessage("PFFFFFFFFFF");
    }

    public void close(){
        writer.close();
    }

}
