/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package datedanny;

/*
Studente: Stortiero Danny
Classe: 5C Informatica
Data: 21/10/2025
*/

import java.io.*;
import java.net.Socket;

public class Date extends Thread {

    private Socket socket;
    
    public Date(Socket socket){
        this.socket = socket;
    }
   
    @Override
    public void run(){
        
        try(BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(),true);)
        {
            
            String s1;
            
            while((s1 = in.readLine()) != null){
                
                
                if(s1.equals("exit")){
                    out.println("Connessione chiusa");
                    System.out.println("Connessione chiusa dal Client");
                    break;
                }
                
                if(s1.isEmpty()){
                    out.println("ERROR 1!");
                    continue;
                }
                
                                
                if(s1.length() > 25){
                    out.println("ERROR 2!");
                    continue;
                }
                
                
                boolean errore = true;
                
                for(int i = 0; i < s1.length(); i++){
                    
                     if(s1.charAt(i) == ';')
                         errore = false;
                }
                
                if(errore == true){
                    out.println("ERROR 3!");
                    continue;
                }
                    

                String[]  comando = s1.split(";");
                
                if(comando[1].length() != 8){
                    out.println("ERROR 4!");
                    continue;
                }
                
                
                boolean errore2 = false;
                
                String numeri = s1.substring(s1.length()-8);
                
                char c;
                
                for(int i = 0; i < numeri.length(); i++){
                    
                     c = numeri.charAt(i);
                    
                     if(!Character.isDigit(c)){
                         errore2 = true;
                     }
                }
                
                if(errore2 == true){
                    out.println("ERROR 5!");
                    continue;
                }
                
                int numeriN = Integer.parseInt(numeri);
                
                int giorno = numeriN / 1000000;
                
                int m = numeriN / 10000;
                
                int mese = m % 100;
                
                int anno = numeriN % 10000;
                
                
                out.println(giorno);
                out.println(mese);
                out.println(anno);
                
                
                
                //ho fatto tutti i controlli ma per il tempo non sono riuscito a finire
                
                
                
                
            }//graffa del while
                 
    }catch(IOException e){
            System.out.println("Errore nel thread client: " + e.getMessage());
            }finally{
            try{
            
            socket.close();
            }catch(IOException e){
            e.printStackTrace();
            }
        }//graffa del finally
        
    }//graffa del Run()
    
}//ultima graffa
