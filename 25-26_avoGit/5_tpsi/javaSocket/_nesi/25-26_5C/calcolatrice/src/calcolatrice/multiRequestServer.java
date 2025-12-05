/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package calcolatrice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author adamr
 */
//bozza non completa del server multithread. 
public class multiRequestServer extends Thread {

    private Socket s;
    float result = 0;
    boolean active = false;
    HashMap<String, multiRequestServer> listaServer;
    String id_client;

    public multiRequestServer(Socket s, HashMap<String, multiRequestServer> lista, String id_client){
        super();
        this.s = s;
        this.listaServer = lista;
        this.id_client = id_client;
    }

    public void run() {
        accept_connection();
    }

    private void accept_connection() {
        try {
            while (active) {
                PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String input_string;
                while ((input_string = in.readLine()) != null) {
                    switch (input_string.charAt(0)) {
                        case '+':
                            result += check_and_sum(input_string);
                            break;
                        case '*':
                            result *= check_and_molt(input_string);
                            break;
                        case '/':
                            result /= check_and_molt(input_string);
                            break;
                        case '-':
                            result -= check_and_sum(input_string);
                            break;
                        default:
                            if (input_string.equals("EXIT")) {
                                listaServer.remove(id_client, this);
                                out.println("EXIT");
                                break;
                            } else if (input_string.equals("=")) {
                                out.println(String.valueOf(result));
                            } else if (input_string.equals("C")) {
                                out.println("SUCCESS");
                                result = 0;
                            } else {
                                out.println("ERRORE;02");
                            }
                    }
                    changeActivation();
                }
            }
            s.close();
        } catch (IOException ex) {
            Logger.getLogger(CalcServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private float check_and_sum(String input) {
        String number_op = input.substring(1);
        PrintWriter out = null;
        BufferedReader in = null;
        float final_number = 0;
        try {
            out = new PrintWriter(s.getOutputStream(), true); 
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            final_number = Float.parseFloat(number_op);
            out.println("SUCCESS");
        } catch (IOException | NumberFormatException ex) {
            System.err.println("ERRORE di formato numerico o errore di I/O");
            out.println("ERRORE;00");
        }
        return final_number;
    }
    
    private float check_and_molt(String input) {
        String number_op = input.substring(1);
        PrintWriter out = null;
        BufferedReader in = null;
        float final_number = 1;
        try {
            out = new PrintWriter(s.getOutputStream(), true); 
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            float final_temp = Float.parseFloat(number_op);
            if(final_temp != 0){
                final_number = final_temp;
                out.println("SUCCESS");
                
            }
            else{
                out.println("ERRORE;01");
                System.err.println("ERRORE divisione per 0");
            }
        } catch (IOException | NumberFormatException ex) {
            System.err.println("ERRORE di formato numerico o errore di I/O");
            out.println("ERRORE;00");
        }
        return final_number;
    }
    
    public void changeActivation(){
        active = !active;
    }
}
