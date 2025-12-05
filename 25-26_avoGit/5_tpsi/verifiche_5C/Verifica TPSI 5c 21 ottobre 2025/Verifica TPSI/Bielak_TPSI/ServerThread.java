import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ServerThread extends Thread
{
    private Socket clientSocket;
    
    
       
    public ServerThread(Socket socket)
    {
        this.clientSocket = socket;
    }
    
    public void run()
    {
        BufferedReader lettura =  null;
        PrintWriter invio = null;
        
        try
        {
            lettura = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
            invio = new  PrintWriter(this.clientSocket.getOutputStream(), true);
            
            invio.println("200  OK - Connessione stabilita");
            
            String line;
            
            while((line = lettura.readLine()) != null)
            {
                System.out.println("Ricevuto dal client " + clientSocket.getInetAddress() + ": " + line);
                
                
                if(line.trim().isEmpty())
                {
                    invio.println("400 Bad Request - Richiesta vuota");
                    //continue
                }
                
                String[] parti = line.split(",");
                
                if(parti.length == 0)
                {
                    invio.println("400 Bad Request - formato comando non valido");
                    //continue
                }
                String comando = parti[0].toUpperCase();
                
                
                switch(comando)
                {
                    case("CHECK"):
                        if(parti.length < 2)
                        {
                            invio.println("400 Bad Request, parametro mancante");
                        }
                        else
                        {
                            String esito = check(parti[1]);
                            invio.println(esito);
                        }
                        break;
                     
                    /*
                    case("GREATER"):
                        if(parti.length < 3)
                        {
                            invio.println("400 Bad Request, parametri insufficienti");
                        }
                        else
                        {
                            String esito = greater(parti[1], parti[2]);
                            invio.println(esito);
                        }
                        break;
                     */
                        
                    case("LEAP"):
                        if(parti.length < 2)
                        {
                            invio.println("400 Bad Request, parametro mancante");
                        }
                        else
                        {
                            String esito = leap(parti[1]);
                            invio.println(esito);
                        }
                        break;
                        
                    case("MONTH"):
                        if(parti.length < 2)
                        {
                            invio.println("400 Bad Request, parametro mancante");
                        }
                        else
                        {
                            String esito = month(parti[1]);
                            invio.println(esito);
                        }
                        break;
                        
                        case("SLASH"):
                        if(parti.length < 2)
                        {
                            invio.println("400 Bad Request, parametro mancante");
                        }
                        else
                        {
                            String esito = slash(parti[1]);
                            invio.println(esito);
                        }
                        break;
                        
                        /*
                        case("TURNDATE"):
                        if(parti.length < 2)
                        {
                            invio.println("400 Bad Request, parametro mancante");
                        }
                        else
                        {
                            String esito = turndate(parti[1]);
                            invio.println(esito);
                        }
                        break;
                        */
                        case("EXIT"):
                        invio.println("200 OK - Disconnessione");
                        return;
                        
                        default:
                        invio.println("405 Method not allowed - comandio non riconosciuto ");
                        break;    
                               
                }
            }
            invio.println("408 Request Timeout - Connessione chiusa dal client");
        }
        catch(IOException e)
        {
            System.err.println("500 Internal Server Error - Errore nella connessione client:");
            e.printStackTrace();
        }      
        
    }
    private String check(String data)
        {
            try
            {
                if(data.length() != 8)
                {
                    return "400 BAD Request - formato data non valido";
                }
                
                int giorno = Integer.parseInt(data.substring(0, 2));
                int mese = Integer.parseInt(data.substring(2, 4));
                int anno = Integer.parseInt(data.substring(4, 8));
                
                
                if(mese < 1 || mese > 12)
                {
                    return "200 OK, Mese non valido";
                }
                
                int[] giorniPerMese = {31,28,31,30,31,30,31,31,30,31,30,31};
                
                if((anno % 400 == 0) || (anno % 4 == 0) || (anno % 100 == 0))
                {
                    giorniPerMese[1] = 29;
                }
                
                if(giorno < 1 || giorno > giorniPerMese[mese - 1])
                {
                    return "200 OK, Data non Corretta,giorno non valido";
                }
                
                return "200 OK- Data corretta";
                
                    
            }
            catch(Exception e)
            {
                return("422 - Formato data non valido");               
            }
                      
        }
    
    private String leap(String data)
    {
        try
        {
            if(data.length() != 8)
            {
                return "400 BAD Request - formato data non valido";
            }
            
            int anno = Integer.parseInt(data.substring(4, 8));
            
            if((anno % 400 == 0) || (anno % 4 == 0) || (anno % 100 == 0))
            {
                return "200 OK - L'anno " + anno + " risulta bisestile";
            }
            else
            {
                return "200 OK - L'anno " + anno + " non risulta bisestile";
            }
        }
        catch(Exception e)
        {
           return("422 - Formato data non valido");          
        }
    }
    
    private String month(String data)
    {
        try
        {
            if(data.length() != 8)
            {
                return "400 BAD Request - formato data non valido";
            }
            
            int mese = Integer.parseInt(data.substring(2, 4));
            
            String[] nomiMesi = {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
            
            if(mese < 1 || mese > 12)
            {
                return "400 BAD Request, Mese non valido";
            }
            
            return "200 OK, Mese: " + nomiMesi[mese - 1];           
        }
        catch(Exception e)
        {
           return("422 - Formato data non valido");          
        }
        
    }
    
    private String slash(String data)
    {
        try
        {
            if(data.length() != 8)
            {
                return "400 BAD Request - formato data non valido";
            }
            
            int giorno = Integer.parseInt(data.substring(0, 2));
            int mese = Integer.parseInt(data.substring(2, 4));
            int anno = Integer.parseInt(data.substring(4, 8));
            
            String risultato = giorno + "/" + mese + "/" + anno;
            
            return "200 OK - Data: " + risultato;
            
        }
        catch(Exception e)
        {
           return("422 - Formato data non valido");          
        }
    }
        
}


