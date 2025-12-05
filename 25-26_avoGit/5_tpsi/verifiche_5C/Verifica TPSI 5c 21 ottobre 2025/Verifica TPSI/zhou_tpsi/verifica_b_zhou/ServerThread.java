/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verifica_b_zhou;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.PrintWriter;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class ServerThread extends Thread {
    
    //ServerSocket ss= new ServerSocket(8888); 
    Socket s;
    
    ServerThread(Socket s)
    {
     this.s = s;
    }
    
    
     public void run()
    {
     PrintWriter out;
     int conta=0;
     String var[];
    
     
     while(true)
     {
      try{
          InputStreamReader mb = new InputStreamReader(s.getInputStream());
          BufferedReader b = new BufferedReader(mb);
          String[] msg= b.readLine().split("");
          var =Integer.parseInt(s, conta);
          System.out.println("Le lettere sono:"+var);
          out=new PrintWriter(s.getOutputStream(),true);
          switch(msg[0])

          {
              case "COUNT" -> {
               
                  int conta_lettere=0;
                  for (int i = 0; i < 30; i++) {
                      if(var[i] != null)
                      {
                       conta_lettere++;
                      }else
                      {
                       System.out.println("Non ci sono lettere presenti nel posto:" + var[i] +".");
                      }
                 }
                  System.out.println("Le lettere sono:" + conta_lettere);
              
              
              }
          
              case "CHANGE" ->{
              
                  String vocale = "a";
                  String lettera = "b";
                  char sostituzione;

                  for (int i = 0; i < 30; i++) {
                      if (var[i] == vocale) {
                          sostituzione += lettera;    //var[0]=a sostituzione=b; 
                      } else {
                         var[i] += sostituzione;
                      }  
                  }
                  System.out.println("Non si sono cambiati le lettere");      
              
              }
              
              
              
              case "CHECK" ->{
                        
                int conta_vocali=0;
                int conta_lettere=0;
                  String vocale1 = "a";
                  String vocale2 = "e";
                  String vocale3 = "i";
                  String vocale4 = "o";
                  String vocale5 = "u";
                
               for (int i = 0; i < 30; i++) {
                      if (var[i] == vocale1) {
                          conta_vocali++;
                          System.out.println("Ci sono:" + conta_vocali + "vocali");
                      } else if (var[i] == vocale2) {
                          conta_vocali++;
                          System.out.println("Ci sono:" + conta_vocali + "vocali");
                      } else if (var[i] == vocale3) {
                          conta_vocali++;
                          System.out.println("Ci sono:" + conta_vocali + "vocali");
                      } else if (var[i] == vocale4) {
                          conta_vocali++;
                          System.out.println("Ci sono:" + conta_vocali + "vocali");
                      } else if (var[i] == vocale5) {
                          conta_vocali++;
                          System.out.println("Ci sono:" + conta_vocali + "vocali");
                      } else {
                         conta_lettere++;
                      }

                  }
              
                  if(conta_vocali > conta_lettere)
                  {
                   System.out.println("I vocali sono piu grandi delle lettere");
                  }else
                   System.out.println("I lettere sono piu grandi delle vocali");
              
              }
          
               
              
              case "INVBIN" ->
              {
               int pari[]={2,4,6,8,10,12,14,16,18,20,22,24,26,28,30};
               int dispari[]={1,3,5,7,9,11,13,15,17,19,21,23,25,27,29};
               
               
               
               for(int i=1;i<31;i++)
               { 
                for(int j=0;j<15;j++)
                {
                    if(i == dispari[j])
                {
                 var[i];
                }else
                 var[i-1];
                }
               }
               
               
               System.out.println("La stringa invertita e:" + var);
               
               
               
              
              }
              
              
              
              
              
              
              
              
              
              case "REVERSE" ->
              {
               int i;
               int j;
               
               for(i=30;i<0;i--)
               {
                for(j=0;j<30;j++)
                {
                 var[i]+=var[j]; //ciao  var[4]=o --> var[0]=o
                }
                
               }
               System.out.println("La parola inverita è:" + var);    
              }
              
              
              
              case "VOWEL" ->
              {
                  int conta_vocali=0;
                  String vocale1 = "a";
                  String vocale2 = "e";
                  String vocale3 = "i";
                  String vocale4 = "o";
                  String vocale5 = "u";

                  for (int i = 0; i < 30; i++) {
                      if (var[i] != vocale1) {
                          conta_vocali++;
                          System.out.println("Ci sono:" + conta_vocali + "vocali");
                      } else if (var[i] == vocale2) {
                          conta_vocali++;
                          System.out.println("Ci sono:" + conta_vocali + "vocali");
                      } else if (var[i] == vocale3) {
                          conta_vocali++;
                          System.out.println("Ci sono:" + conta_vocali + "vocali");
                      } else if (var[i] == vocale4) {
                          conta_vocali++;
                          System.out.println("Ci sono:" + conta_vocali + "vocali");
                      } else if (var[i] == vocale5) {
                          conta_vocali++;
                          System.out.println("Ci sono:" + conta_vocali + "vocali");
                      } else {
                          System.out.println("Non ci sono vocali");
                      }

                  }
              
                 System.out.println("I vocali sono:" + conta_vocali);
                 
              }
              
              
          
          
          }
       
    
     
     
      
     }   catch (IOException ex) {
             Logger.getLogger(ServerThread.class.getName()).log(Level.SEVERE, null, ex);
         }
     
    }
    
    }
   

    
    
    
}
