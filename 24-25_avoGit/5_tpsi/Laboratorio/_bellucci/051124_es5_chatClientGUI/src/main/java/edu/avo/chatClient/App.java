package edu.avo.chatClient;

import edu.avo.gui.IAppObserver;
import edu.avo.gui.IEventObserver;

import java.util.logging.Logger;

public class App implements ICommandConsumer, IEventObserver {

    private SenderProtocolManager spm;
    private IAppObserver guiAppObserver;
    private Logger logger;

    public App(SenderProtocolManager spm, IAppObserver guiAppObserver, Logger logger) {
        this.spm = spm;
        this.guiAppObserver = guiAppObserver;
        this.logger = logger;
        guiAppObserver.setObserver(this);
    }

    /*metodi ICommandConsumer*/
    public void receivedPublicMessage(String userFrom, String message) {
        guiAppObserver.addMessage(userFrom,message);
    }

    public void receivedPrivateMessage(String userFrom, String message) {
       guiAppObserver.addPrivateMessage(userFrom, message);
    }

    public void userJoin(String userId){
        guiAppObserver.addUser(userId);
    }

    public void userList(String userId){
        guiAppObserver.addUserToList(userId);
    }

    public void userQuit(String userId){
        guiAppObserver.removeUser(userId);
    }

    /* metodi IEventObserser*/
    public void sendPrivateMessage(String userTo, String message) {
        spm.privateMessage(userTo, message);
    }

    public void sendMessage(String message) {
        spm.message(message);
    }

    public void sendQuit(){
        spm.close();
    }
}
