package edu.avo;

import edu.avo.tcpiolibrary.MessageWriter;
public class SenderProtocolManager {

    private MessageWriter sender;

    public SenderProtocolManager(MessageWriter writer){
        this.sender = writer;
    }

    public void sendOutcomeLogin(boolean outcome, int token){
        StringBuilder out = new StringBuilder();
        out.append("EsitoLogin#");
        if(outcome){
            out.append("true" + token);
        }
        else{
            out.append("false" + token);
        }
        sender.sendMessage(out.toString());
    }
}
