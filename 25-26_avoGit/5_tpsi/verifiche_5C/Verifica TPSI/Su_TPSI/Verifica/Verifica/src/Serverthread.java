/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Serverthread extends Thread {
    Socket s;

    public Serverthread(Socket s) {
        this.s = s;
    }
    @Override
    public void run(){
        PrintWriter out;
        int var,var1,mese,giorno,anno;
        boolean isBisestile = false;
        String[] mesi = {"GENNAIO","FEBRAIO","MARZO","APRILE","MAGGIO","GIUNIO","LUGLIO","AGOSTO","SETTEMBRE","OTTOBRE","NOVEMBRE","DICEMBRE"};
        int[] giornim = {31,28,31,30,31,30,31,31,30,31,30,31};
        while(true){
            try {
                InputStreamReader mb = new InputStreamReader(s.getInputStream());
                BufferedReader b = new BufferedReader(mb);
                String[] msg = b.readLine().split(";");
                var = Integer.parseInt(msg[1]);
                out = new PrintWriter(s.getOutputStream(),true);
                //Controllo n
                System.out.println("il n è " + var);
                //dati della data
                mese = var/10000 % 100;
                giorno = var/1000000;
                anno = var % 10000;
                System.out.println("il anno è " + anno);
                //controllo anno bisestile
                if(anno % 400 == 0 || (anno % 4 == 0 && anno % 100 != 0))
                {
                    isBisestile = true;
                    giornim[1] = 29;
                }
                   
                //controllo dati inseriti
                if(msg[1].length() == 8 && (mese > 0 && mese <=12) && giorno < giornim[mese-1])
                    switch(msg[0]){
                        case "CHECK" -> {
                            out.println(var);
                        }
                        case "GREATER" -> {
                            var1 = Integer.parseInt(msg[2]);
                            mese = var1/10000 % 100;
                            giorno = var1/1000000;
                            if(msg[2].length() != 8 || (mese <= 0 || mese > 12) && giorno < giornim[mese-1]){
                                out.println("Errore formato seconda data");
                                break;
                            }
                            if(var > var1)
                                out.println(var);
                            else
                                out.println(var1);
                        }
                        case "LEAP" -> {
                            if(isBisestile)
                                out.println("anno bisestile");
                            else
                                out.println("anno non bisestile");
                        }
                        case "MONTH" -> {
                                out.println(mesi[mese-1]);    
                        }
                        case "SLASH" -> {
                                out.println(giorno + "/" + mese + "/" + anno);
                            
                        }
                        case "TURNDATE" -> {
                                out.println(anno +"" + "" + mese + "" + giorno);
                            
                        }
                    }
                else if(msg[0].equals("EXIT"))
                    s.close();
                else
                    out.println("Errore formato data");

                } catch (NumberFormatException ex) {
                    try {
                        out = new PrintWriter(s.getOutputStream(),true); 
                        //dato insetito non è un numero
                        out.println("ERR 1");
                    } catch (IOException ex1) {
                        Logger.getLogger(Serverthread.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                } catch (ArrayIndexOutOfBoundsException ex) {
                    try {
                        out = new PrintWriter(s.getOutputStream(),true); 
                        //dato insetito mancante
                        out.println("ERR 2");
                    } catch (IOException ex1) {
                        Logger.getLogger(Serverthread.class.getName()).log(Level.SEVERE, null, ex1);
                    }
                } catch (IOException ex) {
                Logger.getLogger(Serverthread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}