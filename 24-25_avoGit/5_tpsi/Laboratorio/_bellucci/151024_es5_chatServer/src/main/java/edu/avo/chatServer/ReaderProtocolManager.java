package edu.avo.chatServer;

import edu.avo.tcpiolibrary.IMessageConsumer;
public class ReaderProtocolManager implements IMessageConsumer {
    private ICommandConsumer commandConsumer;

    public ReaderProtocolManager(ICommandConsumer cmd){
        this.commandConsumer = cmd;
    }

    public void consumeMessage(String message){
        // process messages from clients
        // MESSAGE#
        //PRIVATE_MESSAGE#
        //USERQUIT
        String[] msgParts = message.split(Commands.SPLIT);
        switch (msgParts[0]){
            case Commands.MESSAGE -> {
                commandConsumer.message(msgParts[1]); //questo metodo sarà implementato nell'app
            }
            case Commands.PRIVATE_MESSAGE -> {
                // client sends a private message to a destination porta@indirizzoIp
                commandConsumer.privateMessage(msgParts[1], msgParts[2]);
            }
            case Commands.USER_QUIT ->{
                // client closes connection
                commandConsumer.close();
            }
        }

    }
}
