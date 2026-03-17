/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unasoluzioneverifica1302;

/**
 * Classe che rappresenta l'intero progetto Manda in esecuzioni i vari thread
 *
 * @author INFODOC-1
 */
public class Progetto extends Thread {

    private final IObserver observer;

    /**
     * Costruttore
     *
     * @param nome Nome del progetto
     * @param observer Per stampare le informazioni richieste
     */
    public Progetto(String nome, IObserver observer) {
        super(nome);
        this.observer = observer;
    }

    /**
     * Qui vengono attivate le singole attività secondo lo scenario di
     * successione o parallelismo richiesto Il calcolo del tempo viene
     * effettuato scegliendo fra le quattro attività svolte in parallelo la più
     * lunga
     *
     */
    @Override
    public void run() {

        Attivita analisiUMLAsseblaggio = new Attivita("A", "Raccolta requisiti", observer);
        analisiUMLAsseblaggio.start();
        try {
            analisiUMLAsseblaggio.join();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        int tempoImpiegato = analisiUMLAsseblaggio.getTempoImpiegato();
        analisiUMLAsseblaggio = new Attivita("B", "UML", observer);
        analisiUMLAsseblaggio.start();
        try {
            analisiUMLAsseblaggio.join();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        tempoImpiegato += analisiUMLAsseblaggio.getTempoImpiegato();
        Attivita[] realizzazioni = new Attivita[4];
        realizzazioni[0] = new Attivita("C", "Scrittura codice", observer);
        realizzazioni[1] = new Attivita("D", "Scrittura codice", observer);
        realizzazioni[2] = new Attivita("E", "Creazione tiles e sprite", observer);
        realizzazioni[3] = new Attivita("F", "Registrazione suoni", observer);
        for (Attivita realizzazione : realizzazioni) {
            realizzazione.start();
        }
        for (Attivita realizzazione : realizzazioni) {
            try {
                realizzazione.join();
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        tempoImpiegato += Math.max(Math.max(realizzazioni[0].getTempoImpiegato(),
                realizzazioni[1].getTempoImpiegato()),
                Math.max(realizzazioni[2].getTempoImpiegato(),
                        realizzazioni[3].getTempoImpiegato()));
        analisiUMLAsseblaggio = new Attivita("G", "Assemblaggio", observer);
        analisiUMLAsseblaggio.start();
        try {
            analisiUMLAsseblaggio.join();
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
        tempoImpiegato += 2000;
        observer.mostra(getName(), "Progetto", tempoImpiegato);
    }
}
