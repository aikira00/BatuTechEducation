/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziopreparatorio.server;

/**
 *
 * @author palma
 */
public interface ICommandConsumer {

    void performEsplicita(double a, double b, double c);

    void performOrtogonale(double a, double b, double c, double x, double y);

    void performParallela(double a, double b, double c, double x, double y);

    void performFine();

    void performError(int code);
}
