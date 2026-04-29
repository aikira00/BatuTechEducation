/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package edu.avo.addapp;

import edu.avo.tomcatserver.TomcatApp;

/**
 *
 * @author palma
 */
public class AddApp {

    public static void main(String[] args) {
        TomcatApp tomcatApp = new TomcatApp(9090, AddApp.class.getPackageName(),"Add");
    }
}
