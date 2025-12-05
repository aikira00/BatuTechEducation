package edu.avo;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(60000);
        Socket clientSocket = serverSocket.accept();
        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true); // true per flush buffer
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        //scrivo benvenuto
        out.println("Ciao, benvenuto. Inviami due stringhe e vedrai la magia!");
        String s1 = in.readLine();
        float f1 = Float.parseFloat(s1);
        float res = f1;
        //leggo operazione
        String op = in.readLine();
        //oppure while(!"".equals(operation = in.readLine()) così leggo e controllo qui
        while(!op.equals("=")){
            String s2 = in.readLine();
            float f2 = Float.parseFloat(s1);
            switch(op) {
                case "+":
                    res += f2;
                    break;
                case "-":
                    res -= f2;
                case "*":
                    res *= f2;
                    break;
                case  "/":
                    res /= f2;
                    break;

                default:
                    res = -1;
            }
            op = in.readLine();
        }

        out.println("Ecco la magia " + res);

        //chiudiamo tutto
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();

    }
}