/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziopersistenza;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author MULTI01
 */
public class Application implements CommandConsumer {

    private SenderProtocolManager sender;
    private Persistence db;
    private Map<String, Integer> tokens;
    private String username;
    private Random random;
    private int counter;
    
    public Application(SenderProtocolManager sender, Persistence db, Map<String, Integer> tokens) {
        this.sender = sender;
        this.db = db;
        this.tokens = tokens;
        username = null;
        random = new Random();
        counter = 0;
    }

    @Override
    public void performLogin(){
        sender.sendLogin();
    }

    
    
    @Override
    public void performLogin(String username, String password) {
        if (!tokens.containsKey(username)) {
            if (db.loginOk(username, password)) {
                this.username = username;
                createToken();
                sender.sendLoginOk(tokens.get(username));
            } else {
                sender.sendLoginKo();
            }
        } else {
            sender.sendLoginError();
        }
    }

    @Override
    public void performTextSave(String text, int token) {
        if (tokens.get(username) == token) {
            if (db.saveText(text, username)) {
                sender.sendTextSaved();
                counter++;
                if (counter == 5) {
                    counter = 0;
                    sender.sendLoginNow();
                }
            } else {
                sender.sendError();
            }
        } else {
            sender.sendWrongToken();
        }
    }

    @Override
    public void performList(int token){
        if (tokens.get(username) == token) {
            List<String> list=db.getTextList(username);
            sender.sendList(list);
        }else{
            sender.sendWrongToken();
        }
    }
    
    @Override
    public void performUnknownCommand() {
        sender.sendUnknownCommand();
    }
    
    @Override
    public void performParametersError() {
        sender.sendParametersError();
    }

    private void createToken() {
        int token;
        while (tokens.containsValue(token = random.nextInt(1000000, 2000001)));
        tokens.put(username, token);
    }

    @Override
    public void performClose() {
        sender.sendClose();
    }

}
