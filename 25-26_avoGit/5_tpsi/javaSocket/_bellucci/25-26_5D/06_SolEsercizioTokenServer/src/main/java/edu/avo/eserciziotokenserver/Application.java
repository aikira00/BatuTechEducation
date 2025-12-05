/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziotokenserver;

import java.util.List;
import java.util.Random;

/**
 *
 * @author palma
 */
public class Application {

    private final SenderProtocolManager sender;
    private final DbManager dbManager;
    private int msgCounter;
    private int token;
    private final Random random;

    public Application(SenderProtocolManager sender, DbManager dbManager) {
        this.sender = sender;
        this.dbManager = dbManager;
        msgCounter = 0;
        random = new Random();
    }

    public void performLogin(String username, String password) {
        boolean okLogin = dbManager.okLogin(username, password);
        if (okLogin) {
            dbManager.setDbName(username);
            token = random.nextInt(10000000, 20000000);
            msgCounter = 0;
            sender.sendLoginOk(token);
        } else {
            sender.sendLoginKo();
        }
    }

    public void performMsg(int token, String text) {
        if (this.token == token) {
            if (msgCounter < 5) {
                msgCounter++;
                dbManager.saveText(text);
                sender.sendMsgResponse(text);
            } else {
                msgCounter = 0;
                sender.sendLoginNow();
            }
        } else {
            sender.sendLoginNow();
        }
    }

    public void performList(int token) {
        if (this.token == token) {
            msgCounter++;
            List<String> list = dbManager.getList();
            sender.sendListResponse(list);
        } else {
            sender.sendLoginNow();
        }
    }

    public void performUnknowCommand() {
        sender.sendUnknowCommand();
    }

    public void performParametersError() {
        sender.sendParametersError();
    }

}
