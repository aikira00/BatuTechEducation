package edu.avo.echoServer;

public interface ICommandConsumer {
    void  echoMessage(String echoMessage);
    void close();
}
