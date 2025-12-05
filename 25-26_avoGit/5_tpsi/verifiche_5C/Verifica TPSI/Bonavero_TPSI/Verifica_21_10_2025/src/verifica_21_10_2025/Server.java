/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package verifica_21_10_2025;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class Server 
{
    
    public static void main(String[] args)
    {
        
        System.out.println("=SERVER AVVIATO=");
        System.out.println("In attesa di connessione sulla porta 12345 ..." );
        
        try 
        {
            ServerSocket ss = new ServerSocket(12345);
            
            //accetto la richiesta del client
            Socket s = ss.accept();
            
            
            //importo le funzioni di lettura delle stringhe del client
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            InputStreamReader isr = new InputStreamReader(s.getInputStream());
            BufferedReader in = new BufferedReader(isr);
            
            String comando;
            while(true)
            {
                if ((comando = in.readLine()) != null)
                {
                    //divido l'input in due parti
                    String[] parti = comando.split(";");
                    
                    if (parti[0].equals("EXIT") || parti[0].equals("exit"))
                    {
                        out.println("Chiusura connessione... ");
                        System.out.println("Client disconnesso su richiesta.");
                        break;
                    }
                    

                    //controllo se il formato è giusto (e. CHECK;21102025)
                    if (parti.length == 2)
                    {
                        //creo il comando e la data
                        String op = parti[0];
                        String date = parti[1];
                        //divido la data in pezzi
                        int gg = Integer.parseInt(date.substring(0, 2));
                        int mm = Integer.parseInt(date.substring(2, 4));
                        int aa = Integer.parseInt(date.substring(4, 8));
                    
                    
                        if(parti[0].equals("CHECK")
                           || parti[0].equals("LEAP")
                           || parti[0].equals("MONTH")
                           || parti[0].equals("SLASH")
                           || parti[0].equals("TURNDATE"))
                        {
                            try
                            {
                                switch (op)
                                {
                                    case "CHECK":
                                        //controllo se il GIORNO è giusto (semplifico i casi a 31)
                                        if(gg < 0 || gg > 31)
                                            {
                                               out.println("La data e' sbagliata: GIORNO errato.");
                                                break;
                                            }
                                        
                                        //controllo se il MESE è giusto (semplifico i casi a 31)
                                        else if(mm < 0 || mm > 12)
                                            {
                                               out.println("La data e' sbagliata: MESE errato.");
                                                break;
                                            }
                                        
                                        //controllo se l'ANNO è giusto (semplifico i casi a 31)
                                        else if(aa < 0)
                                            {
                                               out.println("La data e' sbagliata: ANNO errato.");
                                                break;
                                            }
                                        
                                        out.println("La data e' valida :).");
                                        break;
                                        
    
                                    case "LEAP":
                                        //controllo se l'ANNO è BISESTILE (semplifico il caso controllando se l'anno è divisibile per 4)
                                        if(aa % 4 == 0)
                                            {
                                               out.println("L'Anno e' bisestile. ");
                                                break;
                                            }
                                        else
                                            {
                                               out.println("L'Anno NON e' bisestile. ");
                                                break;
                                            }
                                        
                                        
                                    case "MONTH":
                                        //creo una stringa month e gli asseggno il nome del mese
                                        String month;
                                        
                                        switch (mm)
                                        {
                                            case 1:
                                                month = "Gennaio";
                                                out.println("Il MESE e' : " + month);
                                                break;
                                                
                                            case 2:
                                                month = "Febbraio";
                                                out.println("Il MESE e' : " + month);
                                                break;
                                                
                                            case 3:
                                                month = "Marzo";
                                                out.println("Il MESE e' : " + month);
                                                break;
                                                
                                            case 4:
                                                month = "Aprile";
                                                out.println("Il MESE e' : " + month);
                                                break;
                                                
                                            case 5:
                                                month = "Maggio";
                                                out.println("Il MESE e' : " + month);
                                                break;
                                                
                                            case 6:
                                                month = "Giugno";
                                                out.println("Il MESE e' : " + month);
                                                break;
                                                
                                            case 7:
                                                month = "Luglio";
                                                out.println("Il MESE e' : " + month);
                                                break;
                                                
                                            case 8:
                                                month = "Agosto";
                                                out.println("Il MESE e' : " + month);
                                                break;
                                                
                                            case 9:
                                                month = "Settembre";
                                                out.println("Il MESE e' : " + month);
                                                break;
                                                
                                            case 10:
                                                month = "Ottobre";
                                                out.println("Il MESE e' : " + month);
                                                break;
                                                
                                            case 11:
                                            month = "Novembre";
                                            out.println("Il MESE e' : " + month);
                                            break;
                                            
                                            case 12:
                                                    month = "Dicembre";
                                                    out.println("Il MESE e' : " + month);
                                                    break;
                                        }
                                        
                                    case "SLASH":
                                        //creo la stringa per la data con lo Slash (es. 21/10/2025)
                                        String SlashDate = "" + gg + "/" + mm + "/" + aa;
                                        
                                        out.println("La data convertita con lo SLASH e' : " + SlashDate);
                                        break;
                                        
                                    case "TURNDATE":
                                        //creo la stringa per la data CONVERTITA (es. 20251021)
                                        String TurnDate = "" + aa + mm + gg;
                                        
                                        out.println("La data convertita AL CONTRARIO e' : " + TurnDate);
                                        break;
                                }
                                
                            }catch (NumberFormatException e) {
                                out.println("Errore: numero non valido");
                            }
                        } 
                     
                        
                    }
                    
                    //controllo se il formato è volendo usare greater (e. GREATER;21102025;23102025)
                    else if (parti.length == 3)
                    {
                        //creo le date
                        String date1 = parti[1];
                        String date2 = parti[2];
                        
                        //divido la data1 in pezzi
                        int gg1 = Integer.parseInt(date1.substring(0, 2));
                        int mm1 = Integer.parseInt(date1.substring(2, 4));
                        int aa1 = Integer.parseInt(date1.substring(4, 8));
                        
                        //divido la data2 in pezzi
                        int gg2 = Integer.parseInt(date2.substring(0, 2));
                        int mm2 = Integer.parseInt(date2.substring(2, 4));
                        int aa2 = Integer.parseInt(date2.substring(4, 8));
                        
                        if(parti[0].equals("GREATER"))
                        {
                            try
                            {
                                if (aa1 > aa2)
                                {
                                    out.println("La prima data e' MAGGIORE della seconda.");
                                    break;
                                }
                                
                                else if (aa1 < aa2)
                                {
                                    out.println("La prima data e' MINORE della seconda.");
                                    break;
                                }
                                
                                else if (aa1 == aa2)
                                {
                                    if (mm1 > mm2)
                                    {
                                        out.println("La prima data e' MAGGIORE della seconda.");
                                        break;
                                    }
                                    
                                    else if (mm1 < mm2)
                                    {
                                        out.println("La prima data e' MINORE della seconda.");
                                        break;
                                    }
                                    
                                    else if (mm1 == mm2)
                                    {
                                        if (gg1 > gg2)
                                        {
                                            out.println("La prima data e' MAGGIORE della seconda.");
                                            break;
                                        }

                                        else if (gg1 < gg2)
                                        {
                                            out.println("La prima data e' MINORE della seconda.");
                                            break;
                                        }

                                        else if (gg1 == gg2)
                                        {
                                            out.println("Le due date sono UGUALI.");
                                            break;
                                        }
                                    }  
                                } 
                            }catch (NumberFormatException e) {
                                out.println("Errore: numero non valido");
                                }   
                        }
                    
                        else
                        {
                            out.println("ERROR 401 Bad Request: formato non valido");
                            continue;
                        }
                    }else
                            out.println("ERROR 401 Bad Request: formato non valido");
                }
            }
        }catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
}
    
