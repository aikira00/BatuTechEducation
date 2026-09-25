package edu.avo.tcpiolibrary;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;
import java.net.SocketTimeoutException;

public class MessageReader implements Runnable {
    private BufferedReader reader;
    private String forClose;
    private boolean stop;
    private MessageConsumer consumer;

    public MessageReader(BufferedReader reader, String forClose, MessageConsumer consumer){
        this.reader = reader;
        this.forClose  = forClose;
        this.consumer = consumer;
        this.stop= false;
    }

    public void start(){
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run(){
        String message;
        while(!stop){
            try {
                if((message=reader.readLine())!= null){
                    consumer.consumeMessage(message);
                    if(forClose.equals(message)){
                        stop=true;
                    }
                }
                else{
                    //se message è null
                    consumer.consumeMessage(forClose);
                    stop=true;
                }
            }catch(SocketTimeoutException | SocketException ex){
                consumer.consumeMessage(forClose);
                stop = true;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
