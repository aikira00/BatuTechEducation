package es3.bellucci.math.server;

import java.io.PrintWriter;

public class MySender {
    private PrintWriter out;

    public MySender(PrintWriter out){
        this.out = out;
    }

    public void send(String message){
        out.println(message);
    }
}
