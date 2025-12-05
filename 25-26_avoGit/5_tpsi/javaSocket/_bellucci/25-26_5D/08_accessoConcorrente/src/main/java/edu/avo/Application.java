package edu.avo;

import java.util.ArrayList;
import java.util.List;

public class Application {

    private SenderProtocolManager sender;
    private final List<String> posti;
    private String titoloSpettacolo;


    public Application(SenderProtocolManager sender, List<String> posti, String titoloSpettacolo){
        this.sender = sender;
        this.posti = posti;
        this.titoloSpettacolo = titoloSpettacolo;
    }

   //
    public void performPostiLiberi(){
        List<Integer> postiLiberi = new ArrayList<>();
        //anche se posti lista synchronized iterazione nn garantita in concorrenza
        synchronized (posti) {
            for (int i = 0; i < posti.size(); i++) {
                if (posti.get(i) == null) {
                    postiLiberi.add(i);
                }
            }
        }
        sender.sendPostiLiberi(postiLiberi);
    }

    public void performPrenotaPosti(String nome, List<Integer> postiDaConfermare){
        synchronized (posti) {
            if(posti.contains(nome)){
                sender.sendPrenotazionePresente(nome);
                return;
            }
        }
        boolean occupato = false;
        //posti lista sincronizzata, get dovrebbe essere a posto
        //però se altri prenotato lo stesso posto... meglio blocco sync
        synchronized (posti) {
            for (Integer posto : postiDaConfermare) {
                if (posti.get(posto) != null) {
                    sender.sendPostiRichiestiOccupati();
                    return;
                }
            }
            // Se tutti i posti sono liberi, effettua la prenotazione
            for (Integer posto : postiDaConfermare) {
                posti.set(posto, nome);
            }
        }
    }

    public void performAnnullaPrenotazione(String nome){

        synchronized (posti) {
            if(!posti.contains(nome)){
                sender.sendAnnullaPrenotazione(nome, false);
                return;
            }
            for(String posto: posti){
                if(posto!= null && posto.equals(nome)){
                    posti.set(posti.indexOf(nome), null);
                }
            }
        }
        sender.sendAnnullaPrenotazione(nome, true);
    }

    public void performTitolo(){
        sender.sendTitoloSpettacolo(this.titoloSpettacolo);
    }

    public void performUnknowCommand() {

        sender.sendUnknowCommand();
    }

    public void performParametersError() {

        sender.sendParametersError();
    }
}
