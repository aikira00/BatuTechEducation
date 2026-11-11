package edu.avo.banca;

import edu.avo.dblibrary.Movimento;
import edu.avo.tcpiolibrary.MessageWriter;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

public class SenderProtocolManager {

    private static final String SEP = "°";

    private MessageWriter sender;

    public SenderProtocolManager(MessageWriter sender) {
        this.sender = sender;
    }

    // EsitoLogin°true°<token>  oppure  EsitoLogin°false
    public void sendOutcomeLogin(boolean outcome, int token) {
        if (outcome) {
            sender.sendMessage("EsitoLogin" + SEP + "true" + SEP + token);
        } else {
            sender.sendMessage("EsitoLogin" + SEP + "false");
        }
    }

    public void sendSaldo(double saldo) {
        sender.sendMessage("Saldo" + SEP + format(saldo));
    }

    public void sendEstrattoConto(List<Movimento> list) {
        sender.sendMessage("EstrattoConto" + movimenti(list));
    }

    public void sendVersamento(double importo) {
        sender.sendMessage("Versamento" + SEP + format(importo));
    }

    public void sendPrelievo(double importo, boolean outcome) {
        sender.sendMessage("Prelievo" + SEP + format(importo) + SEP + outcome);
    }

    public void sendBonifico(double importo, String conto, boolean outcome) {
        sender.sendMessage("Bonifico" + SEP + format(importo) + SEP + conto + SEP + outcome);
    }

    public void sendRicerca(LocalDate inizio, LocalDate fine, List<Movimento> list) {
        sender.sendMessage("Ricerca" + SEP + inizio + SEP + fine + movimenti(list));
    }

    public void sendUnknownCommand() {
        sendError("Comando sconosciuto");
    }

    public void sendWrongToken() {
        sendError("Token errato");
    }

    public void sendParametersError() {
        sendError("Numero di parametri errato");
    }

    public void sendNegativeAmount() {
        sendError("Importi negativi");
    }

    public void sendNegativeMovements() {
        sendError("Valore negativo per numero movimenti richiesti");
    }

    public void sendNumberFormatError() {
        sendError("Errore nei formati numerici");
    }

    public void sendDateFormatError() {
        sendError("Errore nei formati delle date");
    }

    public void sendDateSequenceError() {
        sendError("Errori nella sequenza temporale delle date");
    }

    public void sendAccountNotFound() {
        sendError("Numero di conto inesistente");
    }

    public void sendSameAccount() {
        sendError("Numero di conto uguale all'emittente");
    }

    public void sendLoginError() {
        sendError("Login già effettuato");
    }

    // MessageWriter.close() invia "Logout" e chiude lo stream (e quindi il socket)
    public void sendClose() {
        sender.close();
    }

    private void sendError(String tipo) {
        sender.sendMessage("Errore" + SEP + tipo);
    }

    // °Versamento|Prelievo°<importo>°<data> per ogni movimento
    private String movimenti(List<Movimento> list) {
        String message = "";
        for (Movimento m : list) {
            message += SEP + (m.isVersamento() ? "Versamento" : "Prelievo") + SEP + format(m.getImporto()) + SEP + m.getData();
        }
        return message;
    }

    // sempre due decimali e il punto: 1234.50
    private String format(double valore) {
        return String.format(Locale.ROOT, "%.2f", valore);
    }
}
