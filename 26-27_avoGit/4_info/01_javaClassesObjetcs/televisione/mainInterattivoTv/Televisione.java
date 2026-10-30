package mainInterattivoTv;

// La Televisione della soluzione di "Dal diagramma al codice: la televisione" (esercitazione 06), senza modifiche.
public class Televisione {
    private String marca;
    private int pollici;
    private boolean accesa;
    private int canale;
    private int volume;
    private boolean muto;

    public Televisione(String marca, int pollici) {
        this.marca = marca;
        this.pollici = pollici;
        this.accesa = false;
        this.canale = 1;
        this.volume = 10;
        this.muto = false;
    }

    public String getMarca() {
        return marca;
    }

    public int getPollici() {
        return pollici;
    }

    public int getCanale() {
        return canale;
    }

    public int getVolume() {
        return volume;
    }

    public void accendi() {
        if (accesa) {
            System.out.println(marca + ": sono già accesa");
        } else {
            accesa = true;
        }
    }

    public void spegni() {
        if (!accesa) {
            System.out.println(marca + ": sono già spenta");
        } else {
            accesa = false;
        }
    }

    public void cambiaCanale(int canale) {
        if (!accesa) {
            System.out.println(marca + ": sono spenta, non cambio canale");
        } else if (canale < 1 || canale > 999) {
            System.out.println(marca + ": il canale " + canale + " non esiste");
        } else {
            this.canale = canale;
        }
    }

    public void sintonizzaCome(Televisione altra) {
        cambiaCanale(altra.canale);
    }

    public void alzaVolume() {
        alzaVolume(1);
    }

    public void alzaVolume(int passi) {
        if (accesa && passi > 0) {
            muto = false;
            if (passi >= 100 - volume) {
                volume = 100;
            } else {
                volume = volume + passi;
            }
        }
    }

    public void abbassaVolume() {
        if (accesa) {
            muto = false;
            if (volume > 0) {
                volume = volume - 1;
            }
        }
    }

    public void attivaMuto() {
        if (accesa) {
            muto = true;
        }
    }

    @Override
    public String toString() {
        String stato;
        if (accesa) {
            stato = "accesa";
        } else {
            stato = "spenta";
        }
        String riga = marca + " " + pollici + " pollici [" + stato + "] canale " + canale + ", volume " + volume;
        if (muto) {
            riga = riga + ", muto";
        }
        return riga;
    }
}
