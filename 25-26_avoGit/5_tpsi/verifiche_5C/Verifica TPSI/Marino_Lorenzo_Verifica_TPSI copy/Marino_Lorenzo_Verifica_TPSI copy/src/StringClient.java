
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/*
Lato Client
*/

public class StringClient
{
    public static void main(String[] args)
    {
        try {
            while(true)
            {
            Socket s = new Socket("localhost", 50000);

            
            Scanner scanner = new Scanner(System.in);
            System.out.print("Inserisci un comando: ");
            String comando = scanner.nextLine();
            
            // ERRORE 01: comando vuoto
            if (comando.isEmpty()) 
            {
                System.out.println("ERRORE 01: comando vuoto");
                s.close();
            }
            
            int lunghezza = comando.length();
             int i = 0;
            // Comandi speciali con switch-case
            switch(comando) 
                {
                    case "COUNT":
                        if(lunghezza < 2)
                        {
                         System.out.println("Errore 04");
                         s.close();
                        }
                        
                         System.out.println(lunghezza);
                        
                        break;
                        
                    case "CHANGE":
                       
                       while(i < lunghezza)
                       {
                           i++;
                        if(comando.equals('a'))
                        {
                         if(comando.equals('b'))
                         {
                          comando.replace('a', 'b');
                         }
                        }
                       }
                       System.out.println(comando);
                       break;
                    case "CHECK":
                        int countv = 0;
                        int countc = 0;
                    while(i < lunghezza)
                    {
                     i++;
                     if(comando.equals("a"))
                     {
                         countv++;
                     }
                      if(comando.equals('e'))
                      {
                          countv++;
                       if(comando.equals("i"))
                       {
                           countv++;
                        if(comando.equals('o'))
                        {
                            countv++;
                          if(comando.equals('u'))
                          {
                              countv++;      
                          }
                          else
                          {
                           countc ++;
                          }
                         }
                      }
                     }
                    }
                    countc = lunghezza - countv;
                    if(countv > countc)
                    {
                    System.out.println("Le vocali sono maggiori delle consonanti");
                    }
                    else
                    {
                     System.out.println("Le consonanti sono maggiori delle vocali");
                    }
                }


            s.close();
            }
        } catch (UnknownHostException e) {
            System.out.println("Errore 06");
        } catch (IOException ex) {
            System.out.println("Errore 06");
        }
    }
}