/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.avo.gui;

/**
 *
 * @author palma
 */
public interface IAppObserver {

    /**
     * Per aggiungere un messaggio alla lista
     *
     * @param user L'autore del messaggio
     * @param message Il messaggio
     */
    void addMessage(String user, String message);

    /**
     * Per aggiungere un messaggio privato alla lista dei messaggi
     *
     * @param from L'autopre del messaggio privato
     *
     * @param message Il messaggio privato
     */
    void addPrivateMessage(String from, String message);

    /**
     * Per aggiungere un utente arrivato nella lista
     *
     * @param user L'utente da aggiungere
     */
    void addUser(String user);

    /**
     * Per riempire la lsita utewnti quando ci si connette
     *
     * @param user Il singolo utente che man mano sarà aggiunto alla lista
     */
    void addUserToList(String user);

    void error(int errorCode);

    /**
     * Per rimuovere un utente che si sgancia
     *
     * @param user L'utente da rimuovere
     */
    void removeUser(String user);
    
    void setObserver(IEventObserver observer);
}
