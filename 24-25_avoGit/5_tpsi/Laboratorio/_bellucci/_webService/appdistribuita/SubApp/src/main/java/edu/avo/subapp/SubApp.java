package edu.avo.subapp;

import edu.avo.tomcatserver.TomcatApp;

public class SubApp {
    public static void main(String[] args) {
        TomcatApp tomcatApp = new TomcatApp(9092, SubApp.class.getPackageName(),"Sub");
    }
}
