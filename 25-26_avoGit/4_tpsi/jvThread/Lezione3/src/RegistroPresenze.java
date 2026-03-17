import java.util.ArrayList;
import java.util.List;

public class RegistroPresenze {
    private List<String> presenze = new ArrayList<>();

    // Versione senza sincronizzazione
    public void aggiungiPresenza(String nome) {
        presenze.add(nome);
    }

    // Versione sincronizzata
    public synchronized void aggiungiPresenzaSync(String nome) {
        presenze.add(nome);
    }

    public int getNumeroPresenze() {
        return presenze.size();
    }

    public List<String> getPresenze() {
        return presenze;
    }
}