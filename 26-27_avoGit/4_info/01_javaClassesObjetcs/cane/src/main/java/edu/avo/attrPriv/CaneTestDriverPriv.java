package edu.avo.attrPriv;

public class CaneTestDriverPriv {
    public static void main(String[] args) {
        Cane cane = new Cane();

        // Togliete il commento alla riga sotto e compilate:
        //   error: nome has private access in Cane
        // cane.nome = "Fufi";
        //
        // La riga resta commentata apposta: con un errore di compilazione
        // l'IDE non riesce a eseguire nemmeno gli altri main del progetto.

        cane.presentati();   // null, null, taglia null, 0 anni
        cane.abbaia();       // null: Bau bau!
        // Lo stato è protetto, ma non c'è modo di impostarlo: serve una porta controllata.
    }
}
