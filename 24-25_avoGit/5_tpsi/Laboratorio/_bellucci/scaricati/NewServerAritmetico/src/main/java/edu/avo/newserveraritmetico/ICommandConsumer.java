/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package edu.avo.newserveraritmetico;

/**
 *
 * @author MULTI01
 */
public interface ICommandConsumer {
    void add(int n1, int n2);
    void sub(int n1, int n2);
    void mul(int n1, int n2);
    void div(int n1, int n2);
    void close();
}
