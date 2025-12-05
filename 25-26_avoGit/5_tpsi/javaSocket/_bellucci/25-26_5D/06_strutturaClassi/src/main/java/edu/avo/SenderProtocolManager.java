package edu.avo;

import java.io.PrintWriter;
import java.util.List;

public class SenderProtocolManager {
    private PrintWriter out;

    public SenderProtocolManager(PrintWriter out) {
        this.out = out;
    }

    public void sendLoginOK(int token){
        out.println("LOGIN OK#"+token);
    }

    public void sendLoginKo(){
        out.println("LOGIN KO");
    }

    public void sendLoginNow(){
        out.println("LOGIN NOW");
    }

    public void sendTextSaved(String text){
        out.println("TEXT SAVED#"+text);
    }

    public void sendList(List<String> list){
        String message = "LIST#";
        for(String s : list){
            message += s +"°";
        }
        message = message.substring(0, message.length()-1);
        out.println(message);
    }

    public void sendParametersError(){
        out.println("PARAMETERS ERROR");
    }

    public void sendUnknownommand(){
        out.println("UNKNOWN COMMAND");
    }
}
