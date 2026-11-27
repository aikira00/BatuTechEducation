package stampante;

public class Cartuccia {
    private String codice;
    private int pagine;

    public Cartuccia(String codice, int pagine) {
        this.codice = codice;
        this.pagine = pagine;
    }

    public int getPagine() {
        return pagine;
    }

    public void consuma(int n) {
        if (n <= pagine) {
            pagine = pagine - n;
        }
    }

    @Override
    public String toString() {
        return "cartuccia " + codice + " (" + pagine + " pagine)";
    }
}