/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package edu.avo.calcolonondistribuito;

/**
 *
 * @author palma
 */
public class CalcoloNonDistribuito {

    public static void main(String[] args) throws InterruptedException {
        Crivello c=new Crivello(2,20000000);
        System.out.println("Inizio calcolo");
        long t1=System.currentTimeMillis();
        c.start();
        c.join();
        System.out.println((System.currentTimeMillis()-t1)/1000);
    }
}
