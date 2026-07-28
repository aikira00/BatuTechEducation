package edu.avo.tcpiolibrary;

import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySender {
    private PrintWriter out;
    private Logger logger;

    public MySender(PrintWriter out, Logger logger) {
        this.out = out;
        this.logger = logger;
    }

    public void send(String message){
        logger.log(Level.INFO, "Invio msg => \"{0}\"", message);
        out.println(message);
        out.flush();
    }

    public void close(){
        out.close();
    }
}