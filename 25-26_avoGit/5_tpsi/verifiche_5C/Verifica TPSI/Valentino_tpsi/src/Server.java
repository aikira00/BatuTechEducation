//FILA A VALENTINO
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
public class Server {
    public static void main(String[] args) throws IOException
    {
        
        ServerSocket ss = new ServerSocket(1000);
        
        while(true){
            
        Socket s = ss.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        
        String riga;
        while((riga=in.readLine()) != null){
             System.out.println("il server riceve: " + riga);
             riga = riga.trim();
            String[] st = riga.split(";");
            String inzio = st[0];
            
            
        
          if(inzio.equalsIgnoreCase("CHECK"))
          {  
              int n1 = Integer.parseInt(  st[1].substring(2, 4));   
              if(n1 < 0){
                out.println("ERRORE 204; numero negativo");
                continue;
            }else if(n1 == 2){
                 int n = Integer.parseInt(  st[1].substring(0, 2));  
                 if(n > 28){
                       out.println("ERRORE 205; giorno non valido per quel mese");
                       continue;
                 } else if(n < 0){
                        out.println("ERRORE 204; numero negativo");
                       continue;
                 }   
              }
            else if(n1 == 4 || n1 == 11 || n1 == 6 || n1 == 9){
                 int n = Integer.parseInt(  st[1].substring(0, 2));  
                 if(n > 30){
                       out.println("ERRORE 205; giorno non valido per quel mese");
                       continue;
                 } else if(n < 0){
                        out.println("ERRORE 204; numero negativo");
                       continue;
                 }   
              } else if(n1 == 1 || n1 == 3 || n1 == 5 || n1 == 7 || n1 == 8 || n1 == 10 || n1 == 12){
                 int n = Integer.parseInt(  st[1].substring(0, 2));  
                 if(n > 30){
                       out.println("ERRORE 205; giorno non valido per quel mese");
                       continue;
                 } else if(n < 0){
                        out.println("ERRORE 204; numero negativo");
                       continue;
                 }   
              }
                         
           
               int n2 = Integer.parseInt(  st[1].substring(4, 8)); 
              if(n2 < 0){
                out.println("ERRORE 204; numero negativo");
                continue;
            }
  
              out.println("data corretta");
              continue;
          }
          
          
            if(inzio.equalsIgnoreCase("GREATER")){
                int numero = Integer.parseInt(  st[1].substring(0, 2)); 
                 int numero1 = Integer.parseInt(  st[2].substring(0, 2));
                  int numero2 = Integer.parseInt(  st[1].substring(2, 4)); 
                 int numero3 = Integer.parseInt(  st[2].substring(2, 4)); 
                  int numero4 = Integer.parseInt(  st[1].substring(4, 8)); 
                 int numero5 = Integer.parseInt(  st[2].substring(4, 8)); 
                if(numero4 > numero5)
                {
                      if(numero2 < numero3){                      
                    out.println("data più grande: " + st[1]);     
                    continue;
                    }  
                }
                else if(numero4 == numero5)
                {
                      if(numero2 < numero3){                      
                    out.println("data più grande: " + st[1]);
                    continue;
                    }
                      
                 }
                 else if(numero4 == numero5)
                {
                      if(numero2 == numero3){ 
                          if(numero1 < numero2);
                      }                     
                    out.println("data più grande: " + st[1]);
                    continue;
                    }
                 else{
                       out.println("data più grande: " + st[2]);
                       continue;
                 }
               
        }
            
             if(inzio.equalsIgnoreCase("MONTH")){
                 
                  int mese = Integer.parseInt(  st[1].substring(2, 4)); 
                  if(mese == 1)
                  {
                      out.println("mese della data: " + "gennaio");
                  }
                  else if(mese == 2)
                  {
                      out.println("mese della data: " + "febbraio");
                  }
                   else if(mese == 3)
                  {
                      out.println("mese della data: " + "marzo");
                  }
                   else if(mese == 4)
                  {
                      out.println("mese della data: " + "aprile");
                  }
                   else if(mese == 5)
                  {
                      out.println("mese della data: " + "maggio");
                  }
                   else if(mese == 6)
                  {
                      out.println("mese della data: " + "giugno");
                  }
                   else if(mese == 7)
                  {
                      out.println("mese della data: " + "luglio");
                  }
                   else if(mese == 8)
                  {
                      out.println("mese della data: " + "agosto");
                  }
                   else if(mese == 9)
                  {
                      out.println("mese della data: " + "settembre");
                  }
                   else if(mese == 10)
                  {
                      out.println("mese della data: " + "ottobre");
                  }
                   else if(mese == 11)
                  {
                      out.println("mese della data: " + "novembre");
                  }
                   else if(mese == 12)
                  {
                      out.println("mese della data: " + "dicembre");
                  }  
                  continue;
             }
             
              if(inzio.equalsIgnoreCase("SLASH")){
                   int gior = Integer.parseInt(  st[1].substring(0, 2));
                    int mes = Integer.parseInt(  st[1].substring(2, 4));
                     int ano = Integer.parseInt(  st[1].substring(4, 8));
                     out.println("data: " + gior + "/" + mes + "/" + ano); 
                     continue;
              }
             
              
              if(inzio.equalsIgnoreCase("TURNDATE")){
                  int giorn = Integer.parseInt(  st[1].substring(0, 2));
                    int mess = Integer.parseInt(  st[1].substring(2, 4));
                     int anoo = Integer.parseInt(  st[1].substring(4, 8));
                     out.println("data: " + anoo + mess + giorn);   
                  continue;
              }
              
              
        
        }
        
       s.close();
    }
     
}
}
