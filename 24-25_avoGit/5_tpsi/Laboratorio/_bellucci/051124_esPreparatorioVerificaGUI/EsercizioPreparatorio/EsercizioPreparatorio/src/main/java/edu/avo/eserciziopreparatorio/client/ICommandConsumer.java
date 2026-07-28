/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziopreparatorio.client;

/**
 *
 * @author palma
 */
public interface ICommandConsumer {
    void performEsplicita(double m, double q);
    void performEsplicita(double x);
    void performParallela(double a, double b, double c);
    void performOrtogonale(double a, double b, double c);
    void performErrore(String type);
    void performFine();
}
