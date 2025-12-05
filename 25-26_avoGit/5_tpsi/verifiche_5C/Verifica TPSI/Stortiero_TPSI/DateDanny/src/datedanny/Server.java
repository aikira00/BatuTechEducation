/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datedanny;

/*
Studente: Stortiero Danny
Classe: 5C Informatica
Data: 21/10/2025
*/

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    public static final int PORT = 5555;
    
    public static void main(String[] args){
        new Server().runServer();
    }
    
    public void runServer(){
        
        try(ServerSocket serverSocket = new ServerSocket(PORT)){
            
            System.out.println("Server pronto per la connessione sulla porta" + PORT);
            
            while(true){
                Socket socket = serverSocket.accept();
                System.out.println("Nuovo Client connesso: " + socket.getInetAddress());
                new Date(socket).start();
            }
            
        }catch(IOException e){
            System.err.println("Errore nel server" + e.getMessage());
            e.printStackTrace();
        }
    }
}
