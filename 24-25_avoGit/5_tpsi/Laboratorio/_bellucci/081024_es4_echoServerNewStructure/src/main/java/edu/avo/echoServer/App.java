package edu.avo.echoServer;

public class App implements ICommandConsumer{

    private SenderProtocolManager spm;

    public App(SenderProtocolManager spm){
        this.spm = spm;
    }

    public void echoMessage(String message){
        spm.echoMessage(message);
    }

    public void close(){
        spm.close();
    }
}
