/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.clientverificaa;

/**
 *
 * @author palma
 */
public class ReceiverProtocolManager {
    private Application application;

    public ReceiverProtocolManager(Application application) {
        this.application = application;
    }
    
    public void consumeMessage(String message){
        String [] parts=message.split("#");
        switch(parts[0]){
            case "LIMITS"->{
                application.performLimits(Integer.parseInt(parts[1]), Integer.parseInt(parts[2]));
            }
            case "RESULT"->{
                application.performResult(parts[1]);
            }
            case "WON"->{
                application.performWon();
            }
            case "PARAMETERS ERROR"->{
                application.performParametersError();
            }
            case "UNKNOWN COMMAND"->{
                application.performUnknownCommand();
            }
        }
    }
    
}
