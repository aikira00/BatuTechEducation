/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.avo.gui;

/**
 * L'interfaccia da implementare per gestire gli eventi generati dallae azioni 
 * dell'utente. Corrispondono ai tre messaggi che, da protocollo, il client
 * invia verso il server
 * 
 * @author palma
 */
public interface IEventObserver {
    /**
     * Il metodo per inviare un messaggio
     * 
     * @param message Il messaggio da inviare
     */
    void sendMessage(String message);

    /**
     * Metodo per inviare un messaggio privato
     * 
     * @param destination Lo user cui è destinato il messaggio
     * 
     * @param message Il messaggio privato
     */
    void sendPrivateMessage(String destination, String message);

    /**
     * Il metodo per quando si chiude la connessione chiudendo la finestra
     */
    void sendQuit();
    
    }
