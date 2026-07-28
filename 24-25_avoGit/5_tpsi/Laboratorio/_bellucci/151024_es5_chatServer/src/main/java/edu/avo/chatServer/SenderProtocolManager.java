package edu.avo.chatServer;

import edu.avo.tcpiolibrary.MySender;

import java.util.Collection;
import java.util.Set;

public class SenderProtocolManager {
    private MySender sender;
    private String toClose;

    public SenderProtocolManager(MySender sender, String toClose) {
        this.sender = sender;
        this.toClose = toClose;
    }

    public void sendUserList(Set<String> usersIds){
        StringBuilder msg = new StringBuilder(Commands.USERS_LIST + Commands.SPLIT);
        msg.append(String.join(Commands.SPLIT, usersIds));
        sender.send(msg.toString());
    }

    public void sendUserJoin(String userId){
        StringBuilder msg = new StringBuilder(Commands.USER_JOIN + Commands.SPLIT + userId);
        sender.send(msg.toString());
    }

    public void sendMessage(String userId, String message){
        StringBuilder msg = new StringBuilder(Commands.MESSAGE + Commands.SPLIT + userId + Commands.SPLIT + message);
        sender.send(msg.toString());
    }

    public void sendPrivateMessage(String userId, String message){
        StringBuilder msg = new StringBuilder(Commands.PRIVATE_MESSAGE + Commands.SPLIT + userId + Commands.SPLIT + message);
        sender.send(msg.toString());//dietro questo abbiamo il Mysender con lo stream di output del server
    }

    public void sendUserQuit(String userLoggedOut){
        sender.send(Commands.USER_QUIT + Commands.SPLIT + userLoggedOut);
    }

    public void close(){
        //sender.send(toClose); not sure about close e userQuit
        sender.close(); // close the stream
    }
}
