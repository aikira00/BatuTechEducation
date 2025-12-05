/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.avo.guiesercitazioneudp;

/**
 *
 * @author palma
 */
public interface IAppObserver {
    void updateConnect(boolean ok);
    void updateSetting(int what, int value);
    void updateRequest(int what, int value);
    void setObserver(IEventObserver observer);
    void updateDisconnect();
    void updateError(byte errorType);
}
