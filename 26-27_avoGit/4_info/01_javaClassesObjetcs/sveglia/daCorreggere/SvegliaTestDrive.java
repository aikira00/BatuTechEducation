class Sveglia {
    int ora;
    int minuti;
    String etichetta;
    boolean ripeti;

    void suona() {
        System.out.println("DRIIIN!");
    }

    void mostra() {
        System.out.println("Sveglia alle " + ora + ":" + minuti + " (" + etichetta + ")");
    }
}

class SvegliaTestDrive {
    public static void main(String[] args) {
        s.ora = 7;
        s.minuti = 30;
        s.etichetta = "scuola";
        s.mostra();
        s.suona();
    }
}
