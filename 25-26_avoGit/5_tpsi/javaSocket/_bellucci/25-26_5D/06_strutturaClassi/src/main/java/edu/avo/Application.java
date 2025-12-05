package edu.avo;

import java.util.Random;

public class Application {

    private SenderProtocolManager sender;
    private DbManager dbManager;
    private int token;
    private int msgCounter;
    private Random random;

    public Application(SenderProtocolManager sender, DbManager db) {
        this.sender = sender;
        this.dbManager = db;
        this.random = new Random();
        this.token = 0;
        this.msgCounter = 0;

    }

    public void performLogin(String username, String password){
        boolean loginOk = dbManager.loginOk(username, password);
        if(loginOk){
            token = random.nextInt(1000000, 2000000);
            sender.sendLoginOK(token);
        }
        else{
            sender.sendLoginKo();
        }
    }

    public void performList(int token){
        if(this.token == token){

        }

    }

    public void performMsg(int token, String text){
        if(this.token == token){
            if(this.msgCounter <5){
                this.msgCounter++;
                dbManager.
            }
        }
    }

    public void performParametersError(){
        sender.sendParametersError();
    }
    public void performUnknownCommand(){
        sender.sendUnknownommand();
    }


}
