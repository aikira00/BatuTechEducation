/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziotokenserver;

/**
 *
 * @author palma
 */
public class ReceiverProtocolManager {

    private final Application application;

    public ReceiverProtocolManager(Application application) {
        this.application = application;
    }

    public void consumeMessage(String message) {
        String[] parts = message.split("#");
        if (parts.length > 0) {
            switch (parts[0]) {
                case "LOGIN" -> {
                    if (parts.length == 3) {
                        application.performLogin(parts[1], parts[2]);
                    } else {
                        application.performParametersError();
                    }
                }
                case "MSG" -> {
                    if (parts.length == 3) {
                        try {
                            application.performMsg(Integer.parseInt(parts[1]), parts[2]);
                        } catch (NumberFormatException ex) {
                            application.performParametersError();
                        }
                    } else {
                        application.performParametersError();
                    }
                }
                case "LIST" -> {
                    if (parts.length == 2) {
                        application.performList(Integer.parseInt(parts[1]));
                    } else {
                        application.performParametersError();
                    }
                }
                default -> {
                    application.performUnknowCommand();
                }
            }
        } else {
            application.performUnknowCommand();
        }
    }
}
