package edu.avo.chatServer;


import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App implements ICommandConsumer{

    private SenderProtocolManager spm;
    private String userId;
    private Map<String, SenderProtocolManager> users;
    private Logger logger;

    public App(String userId, SenderProtocolManager spm, Map<String, SenderProtocolManager> users, Logger logger) {
        this.spm = spm;
        this.userId = userId;
        this.users = users;
        this.logger = logger;

        // USERS_LIST COMMAND, al nuovo utente manda lista utenti
        userList(users.keySet());

        //USER_JOIN agli altri utenti manda nuovo utente
       for(SenderProtocolManager currentSpm : users.values()){
           userJoin(userId, currentSpm);
       }

        SenderProtocolManager put = (SenderProtocolManager) users.put(userId, spm);
        if(put != null) {
            logger.log(Level.INFO, "User {0} re-mapped and joined the chat server", userId);
        }
        else{
            logger.log(Level.INFO, "NEW User {0} has joined the chat server for the first time", userId);
        }
    }

    private void userList(Set<String> users){
        spm.sendUserList(users);
    }

    private void userJoin(String userId, SenderProtocolManager spm){
        spm.sendUserJoin(userId);
    }

    public void message(String message){
        for(SenderProtocolManager currentSpm : users.values()){
            currentSpm.sendMessage(userId, message);
            /*if(!spm.equals(currentSpm)) {
                currentSpm.sendMessage(userId, message);
            }*/
        }
    }

    public void privateMessage(String destination, String messageToSend){
        SenderProtocolManager currentSpm = users.get(destination);
         currentSpm.sendPrivateMessage(userId, messageToSend);
         spm.sendPrivateMessage(destination, messageToSend);
    }

    public void close(){
        spm.close();
        users.remove(userId);
        logger.log(Level.INFO, "User removed from the chat {0}", userId);
        for(SenderProtocolManager currentSpm : users.values()){
            currentSpm.sendUserQuit(userId);
            /*if(!spm.equals(currentSpm)) {
                currentSpm.sendMessage(userId, message);
            }*/
        }
    }
}
