/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziopersistenza;

import edu.avo.tcpiolibrary.MessageConsumer;

/**
 *
 * @author MULTI01
 */
public class ReaderProtocolManager implements MessageConsumer {

    private CommandConsumer app;

    public ReaderProtocolManager(CommandConsumer app) {
        this.app = app;
    }

    @Override
    public void consumeMessage(String message) {
        String[] array = message.split(":");
        try {
            switch (array[0]) {
                case "login" -> {
                    if (array.length == 3) {
                        app.performLogin(array[1], array[2]);
                    } else {
                        app.performParametersError();
                    }
                }
                case "msg" -> {
                    if (array.length == 3) {
                        app.performTextSave(array[2], Integer.parseInt(array[1]));
                    } else {
                        app.performParametersError();
                    }
                }
                case "list" -> {
                    if (array.length == 2) {
                        app.performList(Integer.parseInt(array[1]));
                    } else {
                        app.performParametersError();
                    }
                }
                case "quit" -> {
                    app.performClose();
                }
                default -> {
                    app.performUnknownCommand();
                }
            }
        } catch (NumberFormatException ex) {
            app.performParametersError();
        }
    }

}
