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
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

/**
 *
 * @author adamr
 */
public class CalcServerBat {

    public static void main(String[] args) {
        //ArrayList<multiRequestServer> server_sessions = new ArrayList<>();
        Map<String, MultiRequestServerBat> server_sessions = new ConcurrentHashMap<>();
        try {

            ServerSocket ss = new ServerSocket(10000);
            Socket s;
            while (true) {
                s = ss.accept();
                Thread t = new MultiRequestServerBat(s,server_sessions);
                t.start();
                /*PrintWriter out = new PrintWriter(s.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String request_name = in.readLine();
                if(request_name != null){
                    if(server_sessions.get(request_name) == null){
                        server_sessions.put(request_name, new multiRequestServer(s, server_sessions, request_name));
                        server_sessions.get(request_name).start();
                        server_sessions.get(request_name).changeActivation();
                    }
                    else{
                        server_sessions.get(request_name).changeActivation();
                    }
                }*/
            }
        } catch (IOException ex) {
            System.err.println("ERRORE di input/output");
        }
    }

    private static float check_and_sum(String input) {
        String number_op = input.substring(1);
        float final_number = Float.parseFloat(number_op);
        return final_number;
    }
}
