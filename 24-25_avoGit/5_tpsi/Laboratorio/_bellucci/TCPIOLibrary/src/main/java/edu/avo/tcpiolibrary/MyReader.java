package edu.avo.tcpiolibrary;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyReader implements Runnable {

    private BufferedReader in;
    private String toClose;
    private boolean stop;
    private IMessageConsumer consumer;
    private Logger logger;

    public MyReader(BufferedReader in, String toClose, IMessageConsumer consumer, Logger logger) {
        this.in = in;
        this.toClose = toClose;
        this.consumer = consumer;
        this.logger = logger;
        stop = false;
    }

    @Override
    public void run() {
        String message;
        try {
            while (!stop) {
                message = in.readLine();
                logger.log(Level.INFO, "Ricevuto: => " + message);
                if(message!=null && !message.isEmpty()){
                    consumer.consumeMessage(message);
                    if(message.equals(toClose)){
                        stop=true;
                    }
                }
            }
            in.close();
        } catch(SocketException ex){
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void start(){
        Thread thread =new Thread(this);
        thread.start();
    }
}