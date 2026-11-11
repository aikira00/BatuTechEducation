/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.avo.dblibrary;

import java.util.List;

/**
 *
 * @author palma
 */
public interface Persistenza {
    List<Movimento> estrattoConto(String codiceConto, int numeroMovimenti);
    double saldo(String codiceConto);
    boolean prelievo(String codiceConto, double importo);
    void versamento(String codiceConto, double importo);
    String login(String username,String password);
    List<Movimento> ricercaMovimenti(String codiceConto, String dataPrecedente, String dataSuccessiva);
    boolean bonifico(String codiceConto, double importo, String codiceContoCorrente);
    public boolean esisteConto(String codiceConto);
    void close();
}
