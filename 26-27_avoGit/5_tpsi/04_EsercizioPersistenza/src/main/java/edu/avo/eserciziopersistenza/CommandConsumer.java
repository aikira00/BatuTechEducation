/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.avo.eserciziopersistenza;

/**
 *
 * @author MULTI01
 */
public interface CommandConsumer {

    void performLogin();
            
    void performList(int token);

    void performLogin(String username, String password);

    void performParametersError();

    void performTextSave(String text, int token);

    void performUnknownCommand();

    void performClose();
    
}
