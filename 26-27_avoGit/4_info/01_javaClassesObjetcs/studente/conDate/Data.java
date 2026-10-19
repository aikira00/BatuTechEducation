package conDate;

// La Data della consegna "classe Data": giorno, mese, anno, getter e setter, due costruttori,
// toString con le barre ("26/08/18"), toStringAlfabetico ("10 Ottobre 2026"), controlloData con l'array dei giorni.
// Il costruttore non controlla nulla: per questo esiste controlloData, e per questo Studente lo chiama.
public class Data {
    private int giorno;
    private int mese;
    private int anno;

    public Data() {
        this(1, 1, 2000);
    }

    public Data(int giorno, int mese, int anno) {
        this.giorno = giorno;
        this.mese = mese;
        this.anno = anno;
    }

    public int getGiorno() {
        return giorno;
    }

    public int getMese() {
        return mese;
    }

    public int getAnno() {
        return anno;
    }

    public void setGiorno(int giorno) {
        this.giorno = giorno;
    }

    public void setMese(int mese) {
        this.mese = mese;
    }

    public void setAnno(int anno) {
        this.anno = anno;
    }

    public boolean controlloData() {
        int[] giorniDelMese = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (anno < 0) {
            return false;
        }
        if (mese < 1 || mese > 12) {
            return false;
        }
        int massimo = giorniDelMese[mese - 1];
        boolean bisestile = (anno % 4 == 0 && anno % 100 != 0) || anno % 400 == 0;
        if (mese == 2 && bisestile) {
            massimo = 29;
        }
        return giorno >= 1 && giorno <= massimo;
    }

    // "26/08/18": giorno e mese su due cifre, anno sulle ultime due
    @Override
    public String toString() {
        String s = "";
        if (giorno < 10) {
            s = s + "0";
        }
        s = s + giorno + "/";
        if (mese < 10) {
            s = s + "0";
        }
        s = s + mese + "/";
        int dueCifre = anno % 100;
        if (dueCifre < 10) {
            s = s + "0";
        }
        s = s + dueCifre;
        return s;
    }

    // "10 Ottobre 2026": con il mese fuori da 1-12 scrive il numero, così non si rompe mai
    public String toStringAlfabetico() {
        String[] nomiDeiMesi = {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", "Giugno",
                "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
        String nomeMese;
        if (mese >= 1 && mese <= 12) {
            nomeMese = nomiDeiMesi[mese - 1];
        } else {
            nomeMese = "mese " + mese;
        }
        return giorno + " " + nomeMese + " " + anno;
    }
}
