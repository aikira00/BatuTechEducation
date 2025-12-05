package edu.avo;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;

public class SingleServer implements Runnable {

    Socket socket;
    HashMap<String, String> credentials;
    int token = -1;
    String user = null;
    int countText = 0; //server salva fino a 5 messaggi e richiede token
    private static final int TOKEN_MAX = 99999999;

    public SingleServer(Socket socket, HashMap<String, String> credentials) {
        this.socket = socket;
        this.credentials = credentials;
    }

    public boolean checkLogin(String username, String password) {
        if(credentials.containsKey(username)) {
            if(credentials.get(username).equals(password)) {
                return true;
            }
        }
        return false;
    }

    public String processMessage(String message) throws IOException {
        if (message == null || message.isEmpty()) {
            return "ERROR";
        }

        String[] tokens = message.split("#");
        if (tokens.length == 0) {
            return "ERROR#Formato messaggio non valido";
        }

        String command = tokens[0];
        int userToken = 0;
        switch (command) {
            case "LOGIN":
                if (tokens.length < 3) {
                    return "ERROR#LOGIN richiede username e password";
                }

                if(!checkLogin(tokens[1], tokens[2])) {
                    return "ERROR#username o password non valide";
                }
                // crea token random
                Random generator = new Random();
                token = 1 + generator.nextInt(TOKEN_MAX );
                user = tokens[1];
                //creo file se già non esiste
                Path userFilePath = Paths.get("resources/" + user + ".txt");
                if (!Files.exists(userFilePath)) {
                    Files.createFile(userFilePath);
                }
                return "LOGIN#OK#" + token;
            case "MSG":
                if (tokens.length < 3) {
                    return "ERROR#MSG richiede token e testo";
                }
                //non controllo number format exception
                userToken = Integer.parseInt(tokens[1]);
                if (token == -1 || token != userToken) {
                    return "ERROR#Non autenticato o token non valido";
                }

                //salva testo
                FileWriter userFile = new FileWriter("resources/"+ user+".txt", true);
                userFile.append(tokens[2]).append("\n");//se qui append lancia eccezione non chiudo file
                userFile.close();
                return "TEXT#SAVED#" + tokens[2];

            case "LIST":
                if (token == -1 || token != userToken) {
                    return "ERROR#Non autenticato o token non valido";
                }
                //ritorna testo
                BufferedReader reader = new BufferedReader(new FileReader("resources/"+ user+".txt"));
                //qui sarebbe meglio usare StrinBuilder per il loop
                //StringBuilder response = new StringBuilder("LIST#");
                String s = "LIST#";
                String line;
                while((line = reader.readLine()) != null) {
                    s += line + "@";
                    // response.append(line).append("@");
                }
                reader.close();
                return s;
            case "QUIT":
                return "GOODBYE";

            default:
                return "ERROR#CComando non valido";

        }
    }

    public void run(){

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            PrintWriter out = new PrintWriter(this.socket.getOutputStream(), true);

            String line;
            while((line = in.readLine()) != null && !"QUIT".equals(line)){
                out.println(processMessage(line));
            }
            // Invia messaggio di chiusura
            out.println("GOODBYE");
            System.out.println("Client disconnesso: " + user);
            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
