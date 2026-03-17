package com.marcoschiavello.Interfaces;

/**
 * Interfaccia che descrive i metodi che devono essere implementati dentro banca che imlpementa questa interfacia
 *
 * @author Marco Schiavello
 */
public interface Bank {
    /**
     * Metodo che prevela dal saldo della banca un ammontare preso come parametro
     *
     * @param balance ammontare da prelevare
     * @return il saldo rimanente sul conto dopo l'operazione
     */
    double preleva(double balance);

    /**
     * Metodo che deposita nella banca un ammontare preso come parametro
     *
     * @param balance ammontare da depositare
     * @return saldo del conto dopo l'operazione
     */
    double deposita(double balance);

    /**
     * Metodo per prendere il saldo del conto
     *
     * @return saldo del conto
     */
    double getBalance();
}
