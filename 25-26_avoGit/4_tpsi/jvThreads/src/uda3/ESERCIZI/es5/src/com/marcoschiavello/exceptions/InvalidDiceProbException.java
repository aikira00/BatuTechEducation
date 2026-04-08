package com.marcoschiavello.exceptions;

/**
 * Eccazione che viene lanciata quando viene data una probabilita invalida al costruttore di {@link com.marcoschiavello.Dice}
 *
 * @author Marco Schiavello
 */
public class InvalidDiceProbException extends RuntimeException {
    /**
     * Costruttore che chiama il costruttore di {@link RuntimeException} passandogli il messaggio di errore
     */
    public InvalidDiceProbException() {
        super("Invalid probabolity, it must be between 0 and 1");
    }
}
