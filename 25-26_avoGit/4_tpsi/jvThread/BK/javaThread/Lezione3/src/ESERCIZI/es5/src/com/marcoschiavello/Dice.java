package com.marcoschiavello;

import com.marcoschiavello.exceptions.InvalidDiceProbException;

/**
 * Classe che identifica un dado a due faccie che prevede una probabilita che esca una faccia rispetto a un altra presa nel costruttore
 *
 * @author Marco Schiavello
 */
public class Dice {
    /**
     * Probabilita che esca una faccia rispetto a un altra
     */
    private double prob;

    /**
     * Costruttore completo che prende la probabilita per dado e lo crea
     *
     * @param prob probabilita che esca una faccia rispetto a un altra
     * @throws InvalidDiceProbException
     */
    public Dice(double prob) {
        if(prob < 0 || prob > 1)
            throw new InvalidDiceProbException();

        this.prob = prob;
    }

    /**
     * Metodo che dice che dice e uscita la facia con la probabilita indicata nel costruttore, qursto sara indicato con il
     * ritorno true altriemnto se esce false sara uscita la faccia con la probailita rimanente
     *
     * @return risultato del lancio del dado
     */
    public boolean roll() { return Math.random() < this.prob; }
}
