package edu.avo;

public class ReceiverProtocolManager {

    private Application application;

    public ReceiverProtocolManager(Application application) {
        this.application = application;
    }

    public void consumeMessage(String message){
        String[] parts = message.split("#");

        if(parts.length > 0){
            switch(parts[0]){
                case "LOGIN"-> {
                    if(parts.length != 3){
                        application.performParametersError();
                    }
                    else{
                        String username = parts[1];
                        String password = parts[2];
                        application.performLogin(username, password);
                    }
                }

                case "MSG" -> {
                    if(parts.length != 3){ application.performParametersError();
                    }
                    else{
                        int token;
                        try{
                            token =  Integer.valueOf(parts[1]);
                        }
                        catch(NumberFormatException e){
                            throw new RuntimeException("Invalid token");
                        }
                        String text = parts[2];
                        application.performMsg(token, text);


                    }
                }

                case "LIST" -> {
                    if(parts.length != 2){ application.performParametersError();
                    }
                    else{
                        int token;
                        try{
                            token =  Integer.valueOf(parts[1]);
                        }
                        catch(NumberFormatException e){
                            throw new RuntimeException("Invalid token");
                        }
                        application.performList(token);
                    }
                }

                default -> {
                    application.performUnknownCommand();
                }
            }
        }
        else{
            application.performUnknownCommand();
        }
    }
}
