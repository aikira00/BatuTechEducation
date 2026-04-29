/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package edu.avo.authapp;

import edu.avo.tomcatserver.TomcatApp;


/**
 *
 * @author palma
 */
public class AuthApp {

    public static void main(String[] args) {
        TomcatApp tomcatApp = new TomcatApp(8080, AuthApp.class.getPackageName(),"Auth");
    }
}
