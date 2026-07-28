/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.avo.eserciziopreparatoriogui;

/**
 *
 * @author palma
 */
public interface IEventObserver {

    void sendFine();
    void sendEsplicita(double a, double b, double c);
    void sendOrtogonale(double a, double b, double c,double x, double y);
    void sendParallela(double a, double b, double c, double x, double y);
}
