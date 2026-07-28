package edu.avo.chatClient;

public interface ICommandConsumer {

    public void userJoin(String messagetoSend);
    public void userList(String userId);
    public void userQuit(String userLoggedOut);
    public void receivedPrivateMessage(String messageToSend, String destionationUser);
    public void receivedPublicMessage(String messageToSend, String destionationUser);
}
