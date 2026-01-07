/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avo.battisti.essuudpbattisti;

import java.io.IOException;
import java.net.DatagramPacket;

/**
 *
 * @author Enrico
 */
public class Application {
    SenderProtocolManager sPM;
    CreatoreDiFile c = new CreatoreDiFile();

    public Application(SenderProtocolManager sPM) {
        this.sPM = sPM;
    }
    public void performDati(String nomeFile,String num,String testo,DatagramPacket packet) throws IOException{
        c.creaFrammento(nomeFile, num, testo);
        sPM.sendConfirm(num,packet);
    }
    public void performRicostruzioneFile(DatagramPacket packet) throws IOException{
        c.ricostruzioneFile();
        sPM.sendFileRicostruito(packet);
    }
    public void performUnknownCommand(DatagramPacket packet) throws IOException{
        sPM.sendUnknownCommand(packet);
    }
    public void performParametersError(DatagramPacket packet) throws IOException{
        sPM.sendParametersError(packet);
    }
}
