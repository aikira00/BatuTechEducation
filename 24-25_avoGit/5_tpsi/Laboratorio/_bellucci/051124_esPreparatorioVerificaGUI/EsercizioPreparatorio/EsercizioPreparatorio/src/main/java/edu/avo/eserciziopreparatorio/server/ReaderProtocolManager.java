/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziopreparatorio.server;

import edu.avo.tcpiolibrary.IMessageConsumer;

/**
 *
 * @author palma
 */
public class ReaderProtocolManager implements IMessageConsumer {

    private ICommandConsumer consumer;

    public ReaderProtocolManager(ICommandConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public void consumeMessage(String message) {
        String[] array = message.split("§");
        switch (array[0]) {
            case "Esplicita" -> {
                if (array.length == 2) {
                    String[] parameters = array[1].split("#");
                    if (parameters.length == 3) {
                        try {
                            consumer.performEsplicita(Double.parseDouble(parameters[0]),
                                    Double.parseDouble(parameters[1]),
                                    Double.parseDouble(parameters[2]));
                        } catch (NumberFormatException ex) {
                            consumer.performError(1);
                        }
                    } else {
                        consumer.performError(2);
                    }
                } else {
                    consumer.performError(2);
                }
            }
            case "Ortogonale" -> {
                if (array.length == 2) {
                    String[] parameters = array[1].split("#");
                    if (parameters.length == 5) {
                        try {
                            consumer.performOrtogonale(Double.parseDouble(parameters[0]),
                                    Double.parseDouble(parameters[1]),
                                    Double.parseDouble(parameters[2]),
                                    Double.parseDouble(parameters[3]),
                                    Double.parseDouble(parameters[4]));
                        } catch (NumberFormatException ex) {
                            consumer.performError(1);
                        }
                    } else {
                        consumer.performError(2);
                    }
                } else {
                    consumer.performError(2);
                }
            }
            case "Parallela" -> {
                if (array.length == 2) {
                    String[] parameters = array[1].split("#");
                    if (parameters.length == 5) {
                        try {
                            consumer.performParallela(Double.parseDouble(parameters[0]),
                                    Double.parseDouble(parameters[1]),
                                    Double.parseDouble(parameters[2]),
                                    Double.parseDouble(parameters[3]),
                                    Double.parseDouble(parameters[4]));
                        } catch (NumberFormatException ex) {
                            consumer.performError(1);
                        }
                    } else {
                        consumer.performError(2);
                    }
                } else {
                    consumer.performError(2);
                }
            }
            case "Fine" -> {
                consumer.performFine();
            }
            default ->
                consumer.performError(0);
        }
    }

}
