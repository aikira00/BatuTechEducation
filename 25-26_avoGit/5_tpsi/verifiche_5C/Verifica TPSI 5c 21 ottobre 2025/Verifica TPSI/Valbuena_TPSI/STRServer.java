/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
import java.net.ServerSocket;
import java.net.Socket;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class STRServer {
    public static void main(String[] args){
        try{
            int i=0;
            int j=0;
            String finale1 = "";
            int finale2 = 0;
            ServerSocket ses = new ServerSocket(9999);
            Socket so;
            while(true){
                so = ses.accept();
                PrintWriter ouptut = new PrintWriter(so.getOutputStream(), true);
                BufferedReader ipnut = new BufferedReader(new InputStreamReader(so.getInputStream()));
                String legginput = ipnut.readLine();
                String[] ip=legginput.split(";");
                switch(ip[0]){
                    case "COUNT":
                        while(ip[1].charAt(i)!='\n'){
                            j++;
                            i++;
                        }
                        finale2=j;
                        break;
                    case "CHANGE":
                        finale1=ip[3];
                        while(ip[3].charAt(i)!='\n'){
                            if(ip[2].charAt(0)==ip[3].charAt(i)){
                                String[] ipp = ip[3].split(ip[2]);
                                String a= ipp[0].concat(ip[1]).concat(ipp[1]);
                                finale1 = a;
                            }
                        }
                        break;
                    case "CHECK":
                        int k=0;
                        while(ip[1].charAt(i)!='\n'){
                            if(ip[1].charAt(i)=='a' || ip[1].charAt(i)=='e' || ip[1].charAt(i)=='i' || ip[1].charAt(i)=='o' || ip[1].charAt(i)=='u'){
                                j++;
                            }
                            else{
                                k++;
                            }
                            if(k<j){
                                finale1="le vocali";
                            }
                            else{
                                finale1="le consonanti";
                            }
                        }
                        break;
                    case "INVBIN":
                        while(ip[1].charAt(i)!='\n'){
                            
                        }
                        break;
                    case "REVERSE":
                        while(ip[1].charAt(i)!='\n'){
                            
                        }
                        break;
                    case "VOWEL":
                        while(ip[1].charAt(i)!='\n'){
                            
                        }
                        break;
                    default:
                        ouptut.println(finale1);
                        ouptut.println(String.valueOf(finale2));
                        System.out.println(finale1);
                }
                i=0;
                j=0;
                so.close();
            }
        }
        catch(IOException e){
            Logger.getLogger(STRServer.class.getName()).log(Level.SEVERE, null, e);
        }
    }
}
