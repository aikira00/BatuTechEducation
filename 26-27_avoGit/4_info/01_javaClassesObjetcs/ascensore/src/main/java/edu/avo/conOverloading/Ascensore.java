package edu.avo.conOverloading;

// PASSO 2 — l'ascensore conosce i suoi limiti e decide da solo.
// Overloading su costruttori (con this(...)) e su metodi: sali() e sali(int piani).
public class Ascensore {
    private int piano;
    private int ultimoPiano;
    private int capienza;
    private int persone;
    private boolean porteAperte;

    public Ascensore(int ultimoPiano, int capienza) {
        this.ultimoPiano = ultimoPiano;
        this.capienza = capienza;
        this.piano = 0;
        this.persone = 0;
        this.porteAperte = false;
    }

    public Ascensore(int ultimoPiano) {
        this(ultimoPiano, 4);         // capienza standard: 4 persone
    }

    public int getPiano() {
        return piano;
    }

    public int getPersone() {
        return persone;
    }

    public void apriPorte() {
        porteAperte = true;
        System.out.println("Porte aperte al piano " + piano);
    }

    public void chiudiPorte() {
        porteAperte = false;
    }

    public void entrano(int n) {
        if (!porteAperte) {
            System.out.println("Porte chiuse, non entra nessuno");
        } else if (persone + n > capienza) {
            System.out.println("Troppe persone: siamo in " + persone + ", capienza " + capienza);
        } else {
            persone = persone + n;
        }
    }

    public void escono(int n) {
        if (porteAperte && n <= persone) {
            persone = persone - n;
        }
    }

    public void sali() {
        sali(1);
    }

    public void sali(int piani) {
        if (porteAperte) {
            System.out.println("Chiudete le porte");
        } else if (piano + piani > ultimoPiano) {
            System.out.println("Il piano " + (piano + piani) + " non esiste, l'ultimo è il " + ultimoPiano);
        } else {
            piano = piano + piani;
            System.out.println("Salgo al piano " + piano);
        }
    }

    public void scendi() {
        scendi(1);
    }

    public void scendi(int piani) {
        if (porteAperte) {
            System.out.println("Chiudete le porte");
        } else if (piano - piani < 0) {
            System.out.println("Sotto il piano 0 non si va");
        } else {
            piano = piano - piani;
            System.out.println("Scendo al piano " + piano);
        }
    }

    public String toString() {
        String porte;
        if (porteAperte) {
            porte = "porte aperte";
        } else {
            porte = "porte chiuse";
        }
        return "Ascensore al piano " + piano + "/" + ultimoPiano + ", " + persone + "/" + capienza
                + " persone, " + porte;
    }
}
