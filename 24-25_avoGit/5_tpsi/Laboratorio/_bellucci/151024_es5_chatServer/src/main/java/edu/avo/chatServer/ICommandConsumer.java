package edu.avo.chatServer;

import java.util.Set;
import java.util.Collection;

public interface ICommandConsumer {
    void message(String messageToSend);
    void privateMessage(String destination, String messageToSend);
    void close();
}
