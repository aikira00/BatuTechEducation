/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package stringex;

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
 * @author RAMLI ADAM FILA B
 */
public class SessionString extends Thread {

    private Map<String, SessionString> SessionList;
    private boolean active;
    private Socket s;
    private String username_client;

    public SessionString(Map<String, SessionString> SessionList, boolean active, Socket s) {
        this.SessionList = SessionList;
        this.active = active;
        this.s = s;
        this.username_client = username_client;
    }

    public void run() {
        startSession();
    }

    private void startSession() {
        BufferedReader in = null;
        try {
            String[] std_messages = {"COUNT", "CHANGE", "CHECK", "INVBIN", "REVERSE", "VOWEL", "ERROR", "EXIT"};
            PrintWriter out;
            in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            username_client = in.readLine();
            System.out.println("Il server ha ricevuto una richiesta da " + username_client);
            while (active) {
                try {
                    out = new PrintWriter(s.getOutputStream(), true);
                    SessionList.put(username_client, this);
                    String[] message = in.readLine().split(";");
                    switch (message[0]) {
                        case "COUNT":
                            if (message.length == 2) {
                                System.out.println("Il server ha ricevuto il comando " + std_messages[0] + " da "+ username_client);
                                out.println(std_messages[0] + ';' + conteggioLettere(message[1]));
                            } else {
                                System.out.println("Il server ha ricevuto il comando ERRATO " + std_messages[0] + " da "+ username_client);
                                out.println(std_messages[6] + ';' + "SYNTAX");
                            }
                            break;
                        case "CHANGE":
                            if (message.length == 4) {
                                System.out.println("Il server ha ricevuto il comando " + std_messages[1] + " da "+ username_client);
                                char letterToChange = message[1].toCharArray()[0];
                                char letterToUse = message[2].toCharArray()[0];
                                out.println(std_messages[1] + ';' + replacementString(message[3], letterToChange, letterToUse));
                            } else {
                                System.out.println("Il server ha ricevuto il comando ERRATO " + std_messages[1] + " da "+ username_client);
                                out.println(std_messages[6] + ';' + "SYNTAX");
                            }
                            break;
                        case "CHECK":
                            if (message.length == 2) {
                                System.out.println("Il server ha ricevuto il comando " + std_messages[2] + " da "+ username_client);
                                out.println(std_messages[2] + ";" + checkVowelsConsonants(message[1]));
                            } else {
                                System.out.println("Il server ha ricevuto il comando ERRATO " + std_messages[2] + " da "+ username_client);
                                out.println(std_messages[6] + ';' + "SYNTAX");
                            }
                            break;
                        case "INVBIN":
                            if (message.length == 2) {
                                System.out.println("Il server ha ricevuto il comando " + std_messages[3] + " da "+ username_client);
                                out.println(std_messages[3] + ";" + invertPosition(message[1]));
                            } else {
                                System.out.println("Il server ha ricevuto il comando ERRATO " + std_messages[3] + " da "+ username_client);
                                out.println(std_messages[6] + ';' + "SYNTAX");
                            }
                            break;
                        case "REVERSE":
                            if (message.length == 2) {
                                System.out.println("Il server ha ricevuto il comando " + std_messages[4] + " da "+ username_client);
                                out.println(std_messages[4] + ";" + reverseString(message[1]));
                            } else {
                                System.out.println("Il server ha ricevuto il comando ERRATO " + std_messages[4] + " da "+ username_client);
                                out.println(std_messages[6] + ';' + "SYNTAX");
                            }
                            break;
                        case "VOWEL":
                            if (message.length == 2) {
                                System.out.println("Il server ha ricevuto il comando " + std_messages[5] + " da "+ username_client);
                                out.println(std_messages[5] + ";" + countVowels(message[1]));
                            } else {
                                System.out.println("Il server ha ricevuto il comando ERRATO " + std_messages[5] + " da "+ username_client);
                                out.println(std_messages[6] + ';' + "SYNTAX");
                            }
                            break;
                        case "EXIT":
                            if (message.length == 1) {
                                System.out.println("Il server ha ricevuto il comando " + std_messages[7] + " da "+ username_client);
                                out.println(std_messages[7]);
                                SessionList.remove(username_client, this);
                                active = false;
                            } else {
                                System.out.println("Il server ha ricevuto il comando ERRATO " + std_messages[7] + " da "+ username_client);
                                out.println(std_messages[6] + ';' + "SYNTAX");
                            }
                            break;
                        default:
                            out.println(std_messages[6] + ";" + "COMMAND");
                            break;
                    }
                } catch (IOException ex) {
                    Logger.getLogger(SessionString.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SessionString.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(SessionString.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
        private static int conteggioLettere(String message_to_el){
        char[] charArray = message_to_el.toCharArray();
        int lettere = 0;
        char[] arrayLettere = {'a', 'b', 'c',
        'd', 'e', 'f',
        'g', 'h', 'i',
        'j', 'k', 'l',
        'm', 'n', 'o',
        'p', 'q', 'r',
        's', 't', 'u',
        'v', 'w', 'x',
        'y', 'z'
        };
        for(int i = 0; i < charArray.length; i++){
            for(int j = 0; j < arrayLettere.length; j++){
                if(charArray[i] == arrayLettere[j] || charArray[i] == Character.toUpperCase(arrayLettere[j]))
                    lettere++;
            }
        }
        return lettere;
    }
    
    private static String replacementString(String message_to_el, char letter1, char letter2){
        char[] charArray = message_to_el.toCharArray();
        String resultString = "";
        for(int i = 0; i < charArray.length; i++){
            if(charArray[i] == letter1)
                charArray[i] = letter2;
        }
        resultString = String.copyValueOf(charArray);
        return resultString;
    }
    
    private static String checkVowelsConsonants(String message_to_el){
        char[] charArray = message_to_el.toCharArray();
        char[] arrayVocali = {'a', 'e', 'i', 'o', 'u'};
        int consonanti;
        int vocali = consonanti = 0;
        String resultString = "";
        for(int i = 0; i < charArray.length; i++){
            for(int j = 0; j < arrayVocali.length; j++){
                if(charArray[i] == arrayVocali[j] || charArray[i] == Character.toUpperCase(arrayVocali[j]))
                    vocali++;
                else
                    consonanti++;
            }
        }
        if(vocali > consonanti)
            resultString = "VOCALI";
        else if(vocali < consonanti)
            resultString = "CONSONANTI";
        else
            resultString = "UGUALE";
        return resultString;
    }
    
    private static String invertPosition(String message_to_el){
        char[] charArray = message_to_el.toCharArray();
        String resultString = "";
        char temp;
        for(int i = 0; i < charArray.length - 1; i++){
            temp = charArray[i];
            charArray[i] = charArray[i + 1];
            charArray[i + 1] = temp;
        }
        resultString = String.copyValueOf(charArray);
        return resultString;
    }
    
    private static String reverseString(String message_to_el){
        char[] charArray = message_to_el.toCharArray();
        String resultString = "";
        char temp;
        for(int i = charArray.length - 1; i >= 0; i--){
            resultString += charArray[i];
        }
        return resultString;
    }
    
    private static int countVowels(String message_to_el){
        char[] charArray = message_to_el.toCharArray();
        char[] arrayVocali = {'a', 'e', 'i', 'o', 'u'};
        int vocali = 0;
        for(int i = 0; i < charArray.length; i++){
            for(int j = 0; j < arrayVocali.length; j++){
                if(charArray[i] == arrayVocali[j] || charArray[i] == Character.toUpperCase(arrayVocali[j]))
                    vocali++;
            }
        }
        return vocali;
    }
}
