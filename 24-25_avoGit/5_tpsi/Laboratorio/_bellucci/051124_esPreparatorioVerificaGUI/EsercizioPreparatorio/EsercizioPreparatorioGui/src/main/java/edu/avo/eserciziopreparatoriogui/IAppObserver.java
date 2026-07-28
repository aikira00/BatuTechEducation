/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.avo.eserciziopreparatoriogui;

/**
 *
 * @author palma
 */
public interface IAppObserver {
    void updateEsplicita(double m, double q);
    void updateEsplicita(double x);
    void updateOrtogonale(double a, double b, double c);
    void updateParallela(double a, double b, double c);
    void updateError(String type);
    void closeWindow();
    void setObserver(IEventObserver observer);
}
