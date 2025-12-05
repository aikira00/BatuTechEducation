/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adamr
 */
//bozza non completa del server multithread. 
public class MultiRequestServerBat extends Thread {

    private Socket s;
    private float result;
    private boolean active;
    private Map<String, MultiRequestServerBat> listaServer;
    private String id_client;

    public MultiRequestServerBat(Socket s, Map<String, MultiRequestServerBat> lista){
        //super();
        this.s = s;
        listaServer = lista;
        this.id_client = "";
        result = 0;
        active = true;
    }

    public void run() {
        accept_connection();
    }

    private void accept_connection() {
        try {
            PrintWriter out = new PrintWriter(s.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            id_client = in.readLine();
            listaServer.put(id_client,this);
            System.out.println("USERNAME: " + id_client);
            String input_string;
            while (active && (input_string=in.readLine())!=null) {
                System.out.println(id_client + " WHILE ACTIVE " + active);
                System.out.println(id_client + " HA INVIATO " + input_string);
                switch (input_string.charAt(0)) {
                    case '+':
                        result += check_and_sum(input_string, in, out);
                        break;
                    case '*':
                        result *= check_and_molt(input_string, in, out);
                        break;
                    case '/':
                        result /= check_and_molt(input_string, in, out);
                        break;
                    case '-':
                        result -= check_and_sum(input_string, in, out);
                        break;
                    default:
                        if (input_string.equals("EXIT")) {
                            listaServer.remove(id_client, this);
                            out.println("EXIT");
                            System.out.println("SERVER HA INVIATO A " + id_client + " EXIT");
                            changeActivation(); //uscire da while questo fa terminare il thread
                            System.out.println(id_client + " active " + active);
                            break; //esce da switch
                        } else if (input_string.equals("=")) {
                            out.println(String.valueOf(result));
                            System.out.println("SERVER HA INVIATO A " + id_client + String.valueOf(result));
                        } else if (input_string.equals("C")) {
                            out.println("SUCCESS");
                            System.out.println("SERVER HA INVIATO A " + id_client + "SUCCESS");
                            result = 0;
                        } else {
                            out.println("ERRORE;02");
                            System.out.println("SERVER HA INVIATO A " + id_client + "ERRORE;02");
                        }
                }
            }
            System.out.println("SERVER CHIUDE SOCKET per " + id_client);
            in.close();
            out.close();
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(CalcServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private float check_and_sum(String input, BufferedReader in, PrintWriter out) throws IOException {
        String number_op = input.substring(1);
        float final_number = 0;
        try {
            final_number = Float.parseFloat(number_op);
            out.println("SUCCESS");
        } catch (NumberFormatException ex) {
            System.err.println("ERRORE di formato numerico o errore di I/O");
            out.println("ERRORE;00");
        }
        return final_number;
    }
    
    private float check_and_molt(String input, BufferedReader in, PrintWriter out) {
        String number_op = input.substring(1);
        float final_number = 1;
        try {
            float final_temp = Float.parseFloat(number_op);
            if(final_temp != 0){
                final_number = final_temp;
                out.println("SUCCESS");
            }
            else{
                out.println("ERRORE;01");
                System.err.println("ERRORE divisione per 0");
            }
        } catch (NumberFormatException ex) {
            System.err.println("ERRORE di formato numerico o errore di I/O");
            out.println("ERRORE;00");
        }
        return final_number;
    }
    
    public void changeActivation(){
        active = !active;
    }
}
