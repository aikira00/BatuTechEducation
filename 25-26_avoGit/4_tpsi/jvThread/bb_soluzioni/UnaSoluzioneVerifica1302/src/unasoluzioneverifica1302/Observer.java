/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unasoluzioneverifica1302;

/**
 * La classe per la stampa dei tempi impiegati sullo standard output
 *
 * @author INFODOC-1
 */
public class Observer implements IObserver {

    /**
     * Stampa le informazioni in forma tabellare
     *
     * @param nome Nome di chi sviolge l'attività
     * @param mansione Attività svolta
     * @param tempoImpiegato Tempo impiegato
     */
    @Override
    public void mostra(String nome, String mansione, int tempoImpiegato) {
        System.out.printf("%-10s  %-25s  %5d\n", nome, mansione, tempoImpiegato);
    }

}
