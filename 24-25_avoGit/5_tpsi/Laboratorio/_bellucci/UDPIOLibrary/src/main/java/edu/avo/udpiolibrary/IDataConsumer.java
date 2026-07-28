/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.avo.udpiolibrary;

import java.net.InetAddress;

/**
 *
 * @author MULTI01
 */
public interface IDataConsumer {
       public void consumeData(byte[] data, int dataLenght,
               InetAddress address,int portNumber);
}
