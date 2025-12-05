/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.avo.clientverificaa;

/**
 *
 * @author palma
 */
public interface IApplicationObserver {
    void viewLimits(int min, int max);
    void viewResult(String result);
    void viewWon();
    void viewParametersError();
    void viewUnknownCommand();
}
