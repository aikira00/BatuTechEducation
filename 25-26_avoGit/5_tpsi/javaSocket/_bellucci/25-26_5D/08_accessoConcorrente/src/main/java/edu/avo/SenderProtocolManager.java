package edu.avo;

import java.io.PrintWriter;
import java.util.List;

public class SenderProtocolManager {

    private PrintWriter out;

    public SenderProtocolManager(PrintWriter out){
        this.out = out;
    }

    //posti_liberi§posto#posto#
    //da app mi arriva lista di posti liberi
    public void sendPostiLiberi(List<Integer> posti){
        StringBuilder s = new StringBuilder();
        s.append("POSTI_LIBERI§");
        for(Integer posto : posti){
            s.append(posto+ '#');
        }
        //delete last #
        s.deleteCharAt(s.length()-1);
        out.println(s.toString());
    }

    //posti richiesti occupati
    public void sendPostiRichiestiOccupati(){
        out.println("POSTI_RICHIESTI_OCCUPATI");
    }

    //posti richiesti prenotati
    //POSTI PRENOTATI§nome#posto…...°posto
    //da app mi arriva già lista posti prenotati
    public void sendPostiPrenotati(String nome, List<Integer> posti){
        StringBuilder s = new StringBuilder();
        s.append("POSTI_PRENOTATI§" + nome + '#');
        for(Integer posto : posti){
                s.append(posto + '°');
        }
        //delete last #
        s.deleteCharAt(s.length()-1);
        out.println(s.toString());
    }

    //esito ok o ko se prenotazione nn esiste
    //ANNULLA§nome#esito
    //da app mi arriva lista di posti prenotati
    public void sendAnnullaPrenotazione(String nome, boolean esito){
        if(esito){
            out.println("ANNULLA§"+nome+"#OK#PRENOTAZIONE_ANNULLATA");
        }
        else{//utente non esiste mando errore
            out.println("ANNULLA§"+nome+"#KO#NESSUNA_PRENOTAZIONE_TROVATA");
        }
    }

    public void sendPrenotazionePresente(String nome){
        out.println("PRENOTAZIONE_PRESENTE§"+nome);
    }

    public void sendTitoloSpettacolo(String titolo){
        out.println("TITOLO§"+titolo);
    }

    public void sendParametersError() {
        out.println("PARAMETERS ERROR");
    }

    public void sendUnknowCommand() {

        out.println("UNKNOW COMMAND");
    }

}
