package edu.avo;

public class ReaderProtocoloManager {
    Application app;
    public ReaderProtocoloManager(Application app){
        this.app=app;
    }

    public void consume(String message){
        String[] parts = message.split("#");

        switch(parts[0]){
            case "INFO TRASERIMENTO" -> {

            }

            case "DATI" -> {

            }

            case default -> {
                app.
            }
        }
    }
}
