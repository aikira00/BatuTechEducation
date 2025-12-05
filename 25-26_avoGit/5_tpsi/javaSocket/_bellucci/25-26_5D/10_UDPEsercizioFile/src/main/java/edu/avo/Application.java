package edu.avo;

import java.io.File;

public class Application {

    private SenderProtocolManager sender;
    private String localhost;
    private int port;
    private String fileName;
    private int fileParts;

    public Application(String localhost, int port, SenderProtocolManager sender){
        this.sender=sender;
        this.localhost=localhost;
        this.port=port;
    }

    public void performCreateFile(String fileName){
        File file = new File(fileName);
        String returnMsg ="";
        returnMsg = file.exists() ?  "FILE ALREADY EXISTS " + fileName : "FILE CREATED " + fileName;
        sender.send(returnMsg, localhost, port);
    }
}
