/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziopreparatorio.client;

import edu.avo.tcpiolibrary.IMessageConsumer;

/**
 *
 * @author palma
 */
public class ReaderProtocolManager implements IMessageConsumer{

    ICommandConsumer consumer;

    public ReaderProtocolManager(edu.avo.eserciziopreparatorio.client.ICommandConsumer consumer) {
        this.consumer = consumer;
    }
    
    @Override
    public void consumeMessage(String message) {
        String []array=message.split("§");
        
        switch(array[0]){
            case "Esplicita"->{   
                String [] parameters=array[1].split("#");
                if (parameters.length==2){                   
                    consumer.performEsplicita(Double.parseDouble(parameters[0]),
                            Double.parseDouble(parameters[1]));
                }else{
                    consumer.performEsplicita(Double.parseDouble(parameters[0]));
                }
            }
            case "Ortogonale"->{
                String [] parameters=array[1].split("#");
                consumer.performOrtogonale(Double.parseDouble(parameters[0]),
                            Double.parseDouble(parameters[1]),
                            Double.parseDouble(parameters[2]));
            }
            case "Parallela"->{
                String [] parameters=array[1].split("#");
                consumer.performParallela(Double.parseDouble(parameters[0]),
                            Double.parseDouble(parameters[1]),
                            Double.parseDouble(parameters[2]));
            }
            case "Errore"->{
                consumer.performErrore(array[1]);
            }
            case "Fine"->{
                consumer.performFine();
            }
        }
    }
    
}
