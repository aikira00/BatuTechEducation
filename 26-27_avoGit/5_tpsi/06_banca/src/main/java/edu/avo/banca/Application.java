package edu.avo.banca;

import edu.avo.dblibrary.Persistenza;
import java.time.LocalDate;
import java.util.Map;
import java.util.Random;

public class Application implements CommandConsumer {

    private static Random random = new Random();

    private SenderProtocolManager sender;
    private Persistenza db;
    private Map<String, Integer> tokens;   // username -> token dei client collegati
    private String username;               // null finché non c'è un login riuscito
    private String conto;
    private boolean closed;

    public Application(SenderProtocolManager sender, Persistenza db, Map<String, Integer> tokens) {
        this.sender = sender;
        this.db = db;
        this.tokens = tokens;
        username = null;
        conto = null;
        closed = false;
    }

    @Override
    public void performLogin(String username, String password) {
        if (this.username == null) {
            String conto = db.login(username, password);
            if (conto.isEmpty()) {
                sender.sendOutcomeLogin(false, 0);
            } else if (registra(tokens, username)) {
                this.username = username;
                this.conto = conto;
                sender.sendOutcomeLogin(true, tokens.get(username));
            } else {
                sender.sendLoginError();   // utente già collegato da un altro client
            }
        } else {
            sender.sendLoginError();
        }
    }

    @Override
    public void performSaldo(int token) {
        if (tokenOk(token)) {
            sender.sendSaldo(db.saldo(conto));
        } else {
            sender.sendWrongToken();
        }
    }

    @Override
    public void performEstrattoConto(int token, int n) {
        if (!tokenOk(token)) {
            sender.sendWrongToken();
        } else if (n <= 0) {
            sender.sendNegativeMovements();
        } else {
            sender.sendEstrattoConto(db.estrattoConto(conto, n));
        }
    }

    @Override
    public void performVersamento(int token, double importo) {
        if (!tokenOk(token)) {
            sender.sendWrongToken();
        } else if (importo <= 0) {
            sender.sendNegativeAmount();
        } else {
            db.versamento(conto, importo);
            sender.sendVersamento(importo);
        }
    }

    @Override
    public void performPrelievo(int token, double importo) {
        if (!tokenOk(token)) {
            sender.sendWrongToken();
        } else if (importo <= 0) {
            sender.sendNegativeAmount();
        } else {
            sender.sendPrelievo(importo, db.prelievo(conto, importo));
        }
    }

    @Override
    public void performBonifico(int token, double importo, String conto) {
        if (!tokenOk(token)) {
            sender.sendWrongToken();
        } else if (importo <= 0) {
            sender.sendNegativeAmount();
        } else if (conto.equals(this.conto)) {
            sender.sendSameAccount();
        } else if (!db.esisteConto(conto)) {
            sender.sendAccountNotFound();
        } else {
            sender.sendBonifico(importo, conto, db.bonifico(this.conto, importo, conto));
        }
    }

    @Override
    public void performRicerca(int token, LocalDate inizio, LocalDate fine) {
        if (!tokenOk(token)) {
            sender.sendWrongToken();
        } else if (inizio.isAfter(fine) || fine.isAfter(LocalDate.now())) {
            sender.sendDateSequenceError();
        } else {
            sender.sendRicerca(inizio, fine, db.ricercaMovimenti(conto, inizio.toString(), fine.toString()));
        }
    }

    // chiamata una volta sola anche se il Logout arriva dopo un errore del db
    @Override
    public void performClose() {
        if (!closed) {
            closed = true;
            if (username != null) {
                tokens.remove(username);
                username = null;
            }
            sender.sendClose();
            db.close();
        }
    }

    @Override
    public void performUnknownCommand() {
        sender.sendUnknownCommand();
    }

    @Override
    public void performParametersError() {
        sender.sendParametersError();
    }

    @Override
    public void performNumberFormatError() {
        sender.sendNumberFormatError();
    }

    @Override
    public void performDateFormatError() {
        sender.sendDateFormatError();
    }

    // senza login il token è sempre errato
    private boolean tokenOk(int token) {
        return username != null && tokens.get(username) == token;
    }

    // Controlla e inserisce insieme: due client con lo stesso utente nello stesso istante.
    // Ogni client ha la sua Application: il metodo è static, così si sincronizza
    // sulla classe, una sola per tutti, e i client passano uno alla volta.
    // Per saldo e movimenti non serve: il db aggiorna il saldo con un solo comando.
    private static synchronized boolean registra(Map<String, Integer> tokens, String username) {
        boolean ok = false;
        if (!tokens.containsKey(username)) {
            int token;
            while (tokens.containsValue(token = random.nextInt(100000, 1000000)));
            tokens.put(username, token);
            ok = true;
        }
        return ok;
    }
}
