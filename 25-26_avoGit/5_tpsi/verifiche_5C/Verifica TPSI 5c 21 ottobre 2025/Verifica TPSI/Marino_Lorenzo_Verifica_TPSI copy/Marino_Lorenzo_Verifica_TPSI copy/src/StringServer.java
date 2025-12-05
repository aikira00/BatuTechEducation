import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/*
  Server
*/

public class StringServer 
{
    public static void main(String[] args) 
    {
        try {
            ServerSocket ss = new ServerSocket(50000);
            while (true) 
            {
                Socket s = ss.accept(); 
                
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String parola = in.readLine(); //parola dopo il comando, dovevo usare gli array
                String comando = "";
                System.out.println("Il server riceve " + parola + comando);
                int lunghezza = parola.length();
                int i = 0;
                
                if(parola.equals(" "))
                {
                 System.out.println("Errore 01");
                }
                else
                {
                switch(comando) 
                {
                    case "COUNT":
                        if(lunghezza < 2)
                        {
                         System.out.println("Errore 04");
                        }
                        else
                        {
                         System.out.println(lunghezza);
                        }
                        break;
                        
                    case "CHANGE":
                        
                       while(i < lunghezza)
                       {
                           i++;
                        if(parola.equals('a'))
                        {
                         if(parola.equals('b'))
                         {
                          parola.replace('a', 'b');
                         }
                        }
                       }
                       System.out.println(parola);
                       break;
                    case "CHECK":
                        int countv = 0;
                        int countc = 0;
                    while(i < lunghezza)
                    {
                     i++;
                     if(parola.equals("a"))
                     {
                         countv++;
                     }
                      if(parola.equals('e'))
                      {
                          countv++;
                       if(parola.equals("i"))
                       {
                           countv++;
                        if(parola.equals('o'))
                        {
                            countv++;
                          if(parola.equals('u'))
                          {
                              countv++;      
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
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
