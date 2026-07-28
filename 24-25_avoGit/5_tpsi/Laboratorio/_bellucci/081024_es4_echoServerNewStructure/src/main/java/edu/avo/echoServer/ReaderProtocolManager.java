package edu.avo.echoServer;

import edu.avo.tcpiolibrary.IMessageConsumer;

public class ReaderProtocolManager implements IMessageConsumer {
    ICommandConsumer cmdConsumer;

    public ReaderProtocolManager(ICommandConsumer cmdConsumer) {
        this.cmdConsumer = cmdConsumer;
    }

    public void consumeMessage(String message){
        // received message: "MESSAGE messaggio inviato"
        //split upon space
        //String[] messageParts = message.split(" ");
        String echoMsg = message.replaceAll(Commands.MESSAGE, "").trim();

        //switch case for echo and quit, better an if
        if(echoMsg.equalsIgnoreCase(Commands.CLOSE)){
            cmdConsumer.close();
        }
        else{
            cmdConsumer.echoMessage(echoMsg);
        }
        /* switch(echoMsg){
            case "QUIT"->{
                cmdConsumer.close();
            }
            default -> {
                cmdConsumer.echoMessage(echoMsg);
            }
        } */
    }
}