package Gabriel_Manzo_Verifica_TPSI_Lab;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */


public class ServerDate extends Thread{
    Socket socket;
    char[] data1;
    int MAX_LENGTH = 25;
    
    public ServerDate(Socket socket) {
        this.socket = socket;
    }
    
    public void run(){
        
        try{
            
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(isr);
            
            while(true){
                try{
                    String line = in.readLine();
                    
                    if (line == null)
                        break;
                    
                    System.out.println("RICEVUTO" + line);
                    
                    if (line == null || line.trim().isEmpty()){
                        out.println("errore 01, input vuoto o nullo");
                        continue;
                    }
                    
                    line = line.trim();
                    
                    if(line.length() > MAX_LENGTH){
                        out.println("errore 02, superato il limite di caratteri");
                        continue;
                    }
                    
                    String[] argomenti;
                    argomenti = line.split(";");
                    
                    if(argomenti.length > 3 || argomenti.length < 2){
                        out.println("errore 03, il numero di argomenti non è corretto");
                    }
                    
                    String funzione = argomenti[0];
                    
                     
                    argomenti[1].getChars(MAX_LENGTH, MAX_LENGTH, data1, MAX_LENGTH);
                    
                    String giorno;
                    giorno = argomenti[1].substring(0, 1);
                    
                    String mese  = argomenti[1].substring(2,3);
                    
                    String anno = argomenti[1].substring(4,argomenti[1].length());
                    
                    
                    switch(funzione){
                        
                        case "CHECK":
                            int g = Integer.parseInt(argomenti[1].substring(0, 1));
                            int m = Integer.parseInt(argomenti[1].substring(2, 3));
                            int a;
                            a = Integer.parseInt(argomenti[2].substring(4, 7));
                            if(check(g,m,a))
                                out.println("la data è corretta");
                            else 
                                out.println("la data non è corretta");
                           break;
                           
                        
                        case "GREATER":
                            if(argomenti.length < 3){out.println("errore, argomenti mancanti"); continue;}
                            
                            int g1 = Integer.parseInt(argomenti[1].substring(0, 1));
                            int m1 = Integer.parseInt(argomenti[1].substring(2, 3));
                            int a1;
                            a1 = Integer.parseInt(argomenti[2].substring(4, 7));
                            
                            int giorno2 = Integer.parseInt(argomenti[2].substring(0, 1));
                            int mese2 = Integer.parseInt(argomenti[2].substring(2, 3));
                            int anno2;
                            anno2 = Integer.parseInt(argomenti[2].substring(4, 7));
                            
                            if(maggiore(g1, m1, a1, giorno2, mese2, anno2))
                                out.println("sì, la prima data inserita è maggiore della seconda");
                            else
                                out.println("no, la prima data inserita non è maggiore della seconda");
                            break;
                        
                        
                        case "LEAP":
                            
                            int anno1;
                            anno1 = Integer.parseInt(argomenti[2].substring(4, 7));
                            if(bisestile( anno1))
                                out.println("sì, la data inserita fa parte di un anno bisestile");
                            else
                                out.println("no, la data inserita non fa parte di un anno bisestile");
                            break;
                            
                            
                        case "MONTH":
                            String tmp = "";
                                                                        String ris = "";
                                                                        tmp += mese;
                                                                        tmp += mese;

                                                                        switch (tmp){
                                                                                case"01":
                                                                                    ris = "gennaio";
                                                                                    break;

                                                                                case"02":
                                                                                    ris = "febbraio";
                                                                                    break;

                                                                                case"03":
                                                                                    ris = "marzo";
                                                                                    break;

                                                                                case"04":
                                                                                    ris = "aprile";
                                                                                    break;

                                                                                case"05":
                                                                                    ris = "maggio";
                                                                                    break;

                                                                                case"06":
                                                                                    ris = "giugno";
                                                                                    break;

                                                                                case"07":
                                                                                    ris = "luglio";
                                                                                    break;

                                                                                case"08":
                                                                                    ris = "agosto";
                                                                                    break;

                                                                                case"09":
                                                                                    ris = "settembre";
                                                                                    break;

                                                                                case"10":
                                                                                    ris = "ottobre";
                                                                                    break;

                                                                                case"11":
                                                                                    ris = "novembre";
                                                                                    break;


                                                                                case"12":
                                                                                    ris = "dicembre";
                                                                                    break;
                                                                                default:


                                                                    }
                            out.println("il mese corrispettivo alla data è: " + ris);
                            break;
                            
                            
                        case "SLASH":
                            
                            String separata=giorno + "/" + mese + "/" + anno;
                            
                            
                            out.println("ecco la data separata dagli / : " + separata);
                            break;
                            
                            
                        case "TURNDATE":
                                    String temp = "";
                                    for(int i = data1.length; i >= 0; i--){
                                        temp += data1[i];
                                    }
                            out.println("ecco la data espressa al contrario: " + temp);
                            break;
                    }
                        
                } catch(Exception e){e.printStackTrace();}
            }
            
            
        
        
        
        
        
        } catch (IOException ex) {
            Logger.getLogger(ServerDate.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    

    public boolean maggiore(int g1, int g2, int m1, int m2, int a1, int a2){
        
        boolean t = false;
        
        if (a1 > a2) t = true;
        if (a1 == a2){
            if(m1 > m2) t = true;
            
            if (m1 == m2){
                if (g1 > g2) t = true;
            }
        }
        
        
        
        return t;
        
    }

    public boolean bisestile(int anno1 ){
        boolean t = false;
        
        if(anno1 % 4 == 0 || anno1 % 100 == 0 || anno1 % 400 == 0) t = true;
        
        
        return t;
    }

    public boolean check(int g, int m, int a){
        
        boolean t = true;
                            if(g > 30 && m != 1 || m != 3 || m != 5 || m != 7 || m != 8 || m != 10 || m != 12) t = false;
                            if (g > 31 || g < 1) t = false;
                            if (g > 28 && m == 2 && !bisestile(a)) t = false;
                            if (g > 29 && m == 2 ) t = false;
                            
                            return t;
    }
}
    

