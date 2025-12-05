package edu.avo;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class prenotazioniConcorrente {
    public static void main(String[] args) throws IOException {
        //creo e inizializzo list
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < 100; i++){
            list.add(null);
        }

        list = Collections.synchronizedList(list);

        String titolo = "Stranger Things";

        ServerSocket server = new ServerSocket(60000);
        SingleServer single;
        Thread thread;
        while (true) {
            single = new SingleServer(server.accept(), list, titolo);
            thread = new Thread(single);
            thread.start();
        }

    }
}