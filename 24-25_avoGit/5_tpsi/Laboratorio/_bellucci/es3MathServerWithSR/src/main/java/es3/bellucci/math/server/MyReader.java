package es3.bellucci.math.server;

import java.io.BufferedReader;
import java.io.IOException;

public class MyReader {
    private BufferedReader in;

    public MyReader(BufferedReader in){
        this.in = in;
    }

    public String readLine(){
        try{
            return in.readLine();
        }
        catch(IOException e){
            throw new RuntimeException(e.getMessage());
        }
    }

}