package loguercio_fa_data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * Autore: Loguercio Lorenzo 
 * Classe: 5C info
 * Fila: A
 * 
 */

/**
 *COMANDI UTILIZZATI
 * 
 */
public class DataServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server= new ServerSocket(9999);
        System.out.println("server avviato sulla porta 9999");
        System.out.println("in attesa di connassioni");
        
        while(true){
            Socket s=server.accept();
            String clientID = s.getInetAddress().toString();
            System.out.println("connessione accettata: " + clientID);
            
            //gestisce il client in un thread separato
            new Thread() -> gestisciClient(s,clientID).start();
        }
    }
    
    private static void gestisciClient(Socket s, String clientID){
        try{
            BufferedReader in = new BufferedReader(new inputStreamRader(s.getInputStream()));
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            
            //ogni client ha il suo risultato privato
            String risultato="";
            
            String richiesta="";
            while((richiesta= in.readLine())!= null){
                System.out.println("ricevuto da " + clientID + ":" + richiesta);
                
                //elabora la richiesta e ottiene una risposta e ottiene un nuovo risultato
                Risposta rispostaObj=elaboraRichiesta(richiesta, risultato);
                
                String risposta=rispostaObj.risposta;
                risultato =rispostaObj.nuovoRisultato;
                
                System.out.println("inviato a " + clientID + ":" + risposta);
                out.println(risposta);
                
                //se il client invia exit, chiude la connessione
                if(richiesta.trim().equalsIgnoreCase("EXIT")){
                    break;
                }
            }
            
            s.close();
            System.out.println("connessione chiusa con: " + clientID + "\n");
            
        }catch(IOException e){
            System.out.println("errore nella gestione del client: " + e.getMessage());
        }
    }
    
    private static Risposta elaboraRichiesta(String richiesta, String risultatoCorrente){
        String nuovoRisultato = risultatoCorrente;
        try{
            //toglie gli spazi
            richiesta.trim();
            String parti[]=richiesta.split(";");
            String comando=parti[0].trim();
            String numero=parti[1].trim();
            
            String giornoStrig = numero.substring(0, 1);
            String meseStrig = numero.substring(2, 3);
            String annoStrig = numero.substring(4, 7);
           
            giornoStrig= giornoStrig.trim();
            meseStrig= meseStrig.trim();
            annoStrig= annoStrig.trim();
            
            double giorno= Double.parseDouble(giornoStrig);
            double mese= Double.parseDouble(meseStrig);
            double anno= Double.parseDouble(annoStrig);
            
            int annoCorrente=2025;
                
            //esegue il comando
            switch(comando){
                case "CHECK":
                    //Ritona se data è corretta
                    boolean condizione=true;
                    //controllo giorno
                    if(giorno>0 && giorno<=31){
                        if(mese==2 && giorno>=28){
                            condizione=true;
                        }else{
                            condizione=false;
                        }
                        if(mese==4 && giorno>=30){
                            condizione=true;
                        }else{
                            condizione=false;
                        }
                        if(mese==6 && giorno>=30){
                            condizione=true;
                        }else{
                            condizione=false;
                        }
                        if(mese==9 && giorno>=30){
                            condizione=true;
                        }else{
                            condizione=false;
                        }
                        if(mese==11 && giorno>=30){
                            condizione=true;
                        }else{
                            condizione=false;
                        }
                    }else{
                        condizione=false;
                    }
                    //controllo mese
                    if(mese>0 && mese<12){
                        condizione=true;
                    }else{
                        condizione=false;
                    }
                    //controllo anno
                    if(anno<=annoCorrente){
                        condizione=true;
                    }else{
                        condizione=false;
                    }
                    
                    
                    if(condizione==true){
                        nuovoRisultato="true";
                        System.out.println("data valida");
                    }else{
                        nuovoRisultato="false";
                        System.out.println("data non valida");
                    }
                    break;
                    
                case "GREATER":
                    nuovoRisultato="non fatto";
                    
                case "LEAP":
                    if(mese%4==0){
                        nuovoRisultato="true";
                        System.out.println("data anno bisesistile");
                    }else{
                        nuovoRisultato="false";
                        System.out.println("data non anno bisesistilea");
                    }
                    
                case "MONTH":
                    switch(mese){
                        case 1.0:
                            nuovoRisultato="gennaio";
                            System.out.println("il mese è gennaio");
                        case 2.0:
                            nuovoRisultato="febbraio";
                            System.out.println("il mese è febbraio");
                        case 3.0:
                            nuovoRisultato="marzo";
                            System.out.println("il mese è marzo");
                        case 4.0:
                            nuovoRisultato="aprile";
                            System.out.println("il mese è aprile");
                        case 5.0:
                            nuovoRisultato="maggio";
                            System.out.println("il mese è maggio");
                        case 6.0:
                            nuovoRisultato="giugno";
                            System.out.println("il mese è giugno");
                        case 7.0:
                            nuovoRisultato="luglio";
                            System.out.println("il mese è luglio");
                        case 8.0:
                            nuovoRisultato="agosto";
                            System.out.println("il mese è agosto");
                        case 9.0:
                            nuovoRisultato="settembre";
                            System.out.println("il mese è settembre");
                        case 10.0:
                            nuovoRisultato="ottobre";
                            System.out.println("il mese è ottobre");
                        case 11.0:
                            nuovoRisultato="novembre";
                            System.out.println("il mese è novembre");
                        case 12.0:
                            nuovoRisultato="dicembre";
                            System.out.println("il mese è dicembre");
                    }   
                case "SLASH":
                    nuovoRisultato="non fatto";                    
                case "TURNDATE":
                    nuovoRisultato="non fatto";                    
            }
            
            return new Risposta("OK;operazione eseguita \nRIS;", nuovoRisultato);
            
        } catch(NumberFormatException e){
            System.out.println("ERR;02"+ risultatoCorrente);
            return new Risposta("ERR;02", nuovoRisultato);
        } catch(Exception e){
            System.out.println("ERR;99"+ risultatoCorrente);
            return new Risposta("ERR;99", nuovoRisultato);
        }
    }
    
    private static class Risposta{
        String risposta;
        String nuovoRisultato;
        
        Risposta(String risposta, String nuovoRisultato){
            this.risposta = risposta;
            this.nuovoRisultato = nuovoRisultato;
        }
    }
}

