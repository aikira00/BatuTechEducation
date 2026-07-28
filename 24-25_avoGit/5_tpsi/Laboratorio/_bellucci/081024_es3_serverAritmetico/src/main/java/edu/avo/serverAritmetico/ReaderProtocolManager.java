package edu.avo.serverAritmetico;

import edu.avo.tcpiolibrary.IMessageConsumer;

public class ReaderProtocolManager implements IMessageConsumer {
    ICommandConsumer consumer;

    public ReaderProtocolManager(ICommandConsumer consumer) {
        this.consumer = consumer;
    }


    @Override
    public void consumeMessage(String message) {
        String [] array=message.split(" ");
        switch(array[0]){
            case "ADD"->{
                consumer.add(Integer.parseInt(array[1]), Integer.parseInt(array[2]));
            }
            case "SUB"->{
                consumer.sub(Integer.parseInt(array[1]), Integer.parseInt(array[2]));
            }
            case "MUL"->{
                consumer.mul(Integer.parseInt(array[1]), Integer.parseInt(array[2]));
            }
            case "DIV"->{
                consumer.div(Integer.parseInt(array[1]), Integer.parseInt(array[2]));
            }
            case "QUIT"->{
                consumer.close();
            }
        }
    }
}