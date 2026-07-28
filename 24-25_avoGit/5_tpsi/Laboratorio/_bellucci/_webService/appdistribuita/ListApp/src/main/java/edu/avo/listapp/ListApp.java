/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package edu.avo.listapp;

import edu.avo.tomcatserver.TomcatApp;

/**
 *
 * @author palma
 */
public class ListApp {

    public static void main(String[] args) {
        TomcatApp tomcatApp = new TomcatApp(7070, ListApp.class.getPackageName(),"List");
    }
}
