public class Televisione {
    private String marca;
    private boolean accesa;
    private int canale;
    private int volume;

    public Televisione(String marca) {
        this.marca = marca;     // this.marca è l'attributo, marca è il parametro
        accesa = false;
        canale = 1;
        volume = 10;
    }

    public void accendi() {
        accesa = true;
    }

    public void spegni() {
        // PASSO 3: scrivi qui
    }

    public void cambiaCanale(int canale) {
        // PASSO 5: scrivi qui
    }

    public void alzaVolume() {
        if (accesa && volume < 100) {
            volume = volume + 1;
        }
    }

    public void abbassaVolume() {
        // PASSO 4: scrivi qui
    }

    @Override
    public String toString() {
        String stato;
        if (accesa) {
            stato = "accesa";
        } else {
            stato = "spenta";
        }
        return marca + " [" + stato + "] canale " + canale + ", volume " + volume;
    }
}
