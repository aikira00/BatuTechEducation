/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.tftpserver;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author palma
 */
public class Application {

    private final SenderProtocolManager sender;
    private final DbManager fileServer;
    private String current;
    private int blockLength;
    
    public Application(DatagramSocket socket) {
        this.sender = new SenderProtocolManager(socket);
        this.fileServer = new DbManager(512);
        blockLength=-1;
    }
    
    
    public void performFileReadRequest(String filename, InetAddress address, int port) throws IOException{
        if (fileServer.exists(filename)){
            if(fileServer.isReadable(filename)){
                current=filename;
                try {
                    sender.sendData(1, fileServer.readBlock(1, current), address, port);
                } catch (IOException ex) {
                     sender.sendError(0, address, port);
                     throw new IOException(ex);
                }
            }else{
                sender.sendError(2, address, port);
            }
            
        }else{
            sender.sendError(1, address, port);
        }
    }

    public void performFileWriteRequest(String filename, InetAddress address, int port) throws IOException {
        if (!fileServer.exists(filename)){
            if(fileServer.isWritable(filename)){
                current=filename;
                try {
                    sender.sendAck(0, address, port);
                } catch (IOException ex) {
                    sender.sendError(0, address, port);
                }
            }else{
                sender.sendError(2, address, port);
            }            
        }else{
            sender.sendError(6, address, port);
        }
    }

    public void performReceiveData(int blockNumber, byte[] block, InetAddress address, int port) throws IOException {
        fileServer.writeBlock(blockNumber, block, current);
        try {
            sender.sendAck(blockNumber, address, port);
            this.blockLength=block.length;
        } catch (IOException ex) {
             sender.sendError(0, address, port);
             throw new IOException(ex);
        }
    }

    public void performAck(int blockNumber, InetAddress address, int port) throws IOException {
        fileServer.readBlock(blockNumber, current);
        try {
            sender.sendData(blockNumber+1, fileServer.readBlock(blockNumber+1, current), address, port);
        } catch (IOException ex) {
             sender.sendError(0, address, port);
             throw new IOException(ex);
        }
    }

    public int getBlockLength() {
        return blockLength;
    }

    
}
