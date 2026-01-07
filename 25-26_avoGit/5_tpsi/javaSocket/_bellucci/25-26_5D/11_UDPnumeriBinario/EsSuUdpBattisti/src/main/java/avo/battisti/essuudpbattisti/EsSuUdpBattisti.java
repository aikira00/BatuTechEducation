/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package avo.battisti.essuudpbattisti;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

/**
 *
 * @author Enrico
 */
public class EsSuUdpBattisti {

    public static void main(String[] args) throws SocketException {
        DatagramSocket socket = new DatagramSocket(60000);
        SingleServer s;
        Thread t;
        s = new SingleServer(socket);
        t = new Thread(s);
        t.start();
    }
}
