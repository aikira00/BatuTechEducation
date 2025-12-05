package Gabriel_Manzo_Verifica_TPSI_Lab;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class ServerThread {
    public static void main(String args[]) throws IOException{
        new ServerThread().runServer();}
    public void runServer() throws IOException{
        
        ServerSocket serverSocket = new ServerSocket(12345);
        String tmp = "";
        
        while(true){
            Socket socket = serverSocket.accept();
            new ServerDate(socket).start();
        }
             
        
    }
}
