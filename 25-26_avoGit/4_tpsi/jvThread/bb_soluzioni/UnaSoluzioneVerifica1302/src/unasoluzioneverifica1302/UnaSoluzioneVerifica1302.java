/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package unasoluzioneverifica1302;

/**
 * Soluzione per la verifica
 *
 * @author INFODOC-1
 *
 */
public class UnaSoluzioneVerifica1302 {

    /**
     * Viene creato l'oggetto per la stampa. Notare la dichiarazione del tipo
     * dell'interfaccia e, ovviamente, l'allocazione con il tipo della classe
     * Viene creato il progetto e mandato in esecuzione. In questo caso non era
     * necessario il join.
     *
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        IObserver observer = new Observer();
        Progetto progetto = new Progetto("Progetto", observer);
        progetto.start();
        progetto.join();

    }

}
