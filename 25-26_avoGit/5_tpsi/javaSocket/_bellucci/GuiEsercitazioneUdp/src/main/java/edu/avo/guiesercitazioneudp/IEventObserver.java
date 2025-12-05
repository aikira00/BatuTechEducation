/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.avo.guiesercitazioneudp;

/**
 *
 * @author palma
 */
public interface IEventObserver {
    void sendConnect(byte station);
    void sendRequest(byte sensor);
    void sendSetting(byte actuator, byte value);
    void sendSetting(byte actuator);
    void sendClose();
    void sendDisconnect();
    void sendUnknowCommand();
}
