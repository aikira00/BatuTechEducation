package unasoluzioneverifica1302;

/**
 * Classe che rappresenta lo svolgimento delle attività
 *
 * @author INFODOC-1
 */
public class Attivita extends Thread {

    private final String mansione;
    private final IObserver observer;
    private int tempoImpiegato;

    /**
     *
     * @param nome Nome di chi svolge l'attività
     * @param mansione Il tipo di attività svolta
     * @param observer Oggetto per la stampa dei dati richiesti
     */
    public Attivita(String nome, String mansione, IObserver observer) {
        super(nome);
        this.mansione = mansione;
        this.observer = observer;
    }

    /**
     * Viene generato un tempo casuale a meno che non si tratti dell'unico caso
     * in cui il tempo e fisso. Si attende il tempo previsto e quindi si
     * mostrano i dati richiesti
     */
    @Override
    public void run() {
        tempoImpiegato = 2000;
        if (!getName().equals("G")) {
            tempoImpiegato = (int) (Math.random() * 2001 + 4000);
        }
        try {
            Thread.sleep(tempoImpiegato);
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        observer.mostra(getName(), mansione, tempoImpiegato);
    }

    /**
     * Restituisce il tempo impiegato a svolgere la mansione
     *
     * @return Il tempo impiegato
     */
    public int getTempoImpiegato() {
        return tempoImpiegato;
    }

}
