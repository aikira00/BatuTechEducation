/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.dblibrary;

import java.time.LocalDate;

/**
 *
 * @author palma
 */
public class Movimento {
    private final boolean versamento;
    private final double importo;
    private final LocalDate data;

    Movimento(boolean versamento, double importo, LocalDate data) {

        this.versamento = versamento;
        this.importo = importo;
        this.data = data;
    }


    public boolean isVersamento() {
        return versamento;
    }

    public double getImporto() {
        return importo;
    }

    public LocalDate getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Movimento{" + "versamento=" + versamento + ", importo=" + importo + ", data=" + data + '}';
    }
    
    
}
