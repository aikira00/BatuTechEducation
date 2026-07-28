package edu.avo.chatClient;

import edu.avo.tcpiolibrary.MySender;

public class SenderProtocolManager {
    private MySender sender;
    private String toClose;

    public SenderProtocolManager(MySender sender, String toClose) {
        this.sender = sender;
        this.toClose = toClose;
    }

    public void message(String message) {
        sender.send(Commands.MESSAGE + Commands.SPLIT + message);
    }

    public void privateMessage(String userDest, String message) {
        sender.send(Commands.PRIVATE_MESSAGE + Commands.SPLIT + String.join(Commands.SPLIT, userDest, message));
    }

    public void close(){
        sender.send(toClose);
        sender.close();
    }

}
