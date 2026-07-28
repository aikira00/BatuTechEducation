package edu.avo.chatClient;

import edu.avo.tcpiolibrary.IMessageConsumer;

import java.util.Arrays;

public class ReaderProtocolManager implements IMessageConsumer {

    private ICommandConsumer consumer;

    public ReaderProtocolManager(ICommandConsumer consumer) {
        this.consumer = consumer;
    }
    @Override
    public void consumeMessage(String message) {
        // USER_LIST#
        //USER_JOIN#
        //MESSAGE#
        //PRIVATE_MESSAGE#
        String[] msgParts = message.split(Commands.SPLIT);

        switch (msgParts[0]) {
            case Commands.MESSAGE -> {
                consumer.receivedPublicMessage(msgParts[1],msgParts[2]);
            }

            case Commands.PRIVATE_MESSAGE -> {
                consumer.receivedPrivateMessage(msgParts[1],msgParts[2]);
            }

            case Commands.USER_JOIN -> {
                consumer.userJoin(msgParts[1]);
            }

            case Commands.USERS_LIST -> {
                for(String user: Arrays.copyOfRange(msgParts, 1, msgParts.length)){
                    consumer.userList(user);
                }

            }

            case Commands.USER_QUIT -> {
                consumer.userQuit(msgParts[1]);
            }
        }
    }
}
