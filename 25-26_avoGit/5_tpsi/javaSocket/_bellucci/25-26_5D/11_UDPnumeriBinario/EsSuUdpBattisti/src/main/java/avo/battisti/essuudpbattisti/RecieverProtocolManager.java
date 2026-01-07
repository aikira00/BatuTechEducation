/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package avo.battisti.essuudpbattisti;

import java.io.IOException;
import java.net.DatagramPacket;
import java.util.Arrays;

/**
 *
 * @author Enrico
 */
public class RecieverProtocolManager {

    private String[] parts = null;
    private Application a;
    private int stringheArrivate = 0;
    private SingleServer server;
    
    public RecieverProtocolManager(Application a,SingleServer server) {
        this.a = a;
        this.server=server;
    }

    public void consumeMessage(DatagramPacket packet) throws IOException {
        String[] command = new String(Arrays.copyOfRange(packet.getData(),0,packet.getLength())).split("!");
        if (command.length == 2) {
            switch (command[0]) {
                case "INFO TRASFERIMENTO" -> {
                    parts = command[1].split("@");
                    if (parts.length != 2) {
                        a.performParametersError(packet);
                        parts = null;
                    }
                }
                case "DATI" -> {
                    String[] datiFile = command[1].split("@");
                    if (datiFile.length == 2 && parts != null) {
                        if (stringheArrivate != Integer.parseInt(parts[1])) {
                            if (Integer.parseInt(datiFile[0]) <= Integer.parseInt(parts[1]) && Integer.parseInt(datiFile[0]) >= 1) {
                                a.performDati(parts[0], datiFile[0], datiFile[1],packet);
                                stringheArrivate++;
                            } else {
                                a.performParametersError(packet);
                            }
                        } else {
                            server.stop=true;
                            a.performRicostruzioneFile(packet);                           
                        }
                    } else {
                        a.performParametersError(packet);
                    }
                }
            }
        } else {
            a.performUnknownCommand(packet);
        }
    }
}
