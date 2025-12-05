package edu.avo;

import java.util.ArrayList;
import java.util.List;

public class ReaderProtocolManager {

    private Application app;

    public ReaderProtocolManager(Application app) {
        this.app = app;
    }

    //messaggio in arrivo da client
    /*Client → Server
        PRENOTA§nome#posto[°posto…...°posto] Nome e lista di posti richiesti
        ANNULLA§nome Annullamento della richiesta
        TITOLO Titolo dello spettacolo
        POSTI LIBERI Richiede l’invio dei posti liberi
        QUIT\*/
    public void consumeMessage(String message){
        String[] parts = message.split("§");
        if(parts.length > 0){
            switch(parts[0]) {
                case "PRENOTA"-> {
                    String[] elements = parts[1].split("#");
                    if(elements.length == 2){
                        String nome =  elements[1];
                        String[] posti =  elements[2].split("°");
                        List<Integer> postiRichiesti = new ArrayList<>();
                        for(String i : posti){
                            postiRichiesti.add(Integer.parseInt(i));
                        }
                        app.performPrenotaPosti(nome, postiRichiesti);

                    }
                    else{
                        app.performParametersError();
                    }
                }

                case "ANNULLA" ->{
                    if(parts.length == 2){
                        String nome =  parts[1];
                        app.performAnnullaPrenotazione(nome);

                    }
                    else{
                        app.performParametersError();
                    }
                }

                case "TITOLO" -> {
                    if(parts.length == 1) {
                        app.performTitolo();
                    }
                    else{
                        app.performParametersError();
                    }
                }

                case "POSTI_LIBERI" -> {
                    if(parts.length == 1) {
                        app.performPostiLiberi();
                    }
                    else{
                        app.performParametersError();
                    }
                }

                default -> {
                    app.performUnknowCommand();
                }
            }
        }
        else {
            app.performUnknowCommand();
        }
    }
}
