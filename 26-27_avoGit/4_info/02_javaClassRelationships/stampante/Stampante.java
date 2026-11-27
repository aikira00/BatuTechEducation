package stampante;

public class Stampante {
    private String nome;
    private Vassoio vassoio;
    private Cartuccia cartuccia;

    public Stampante(String nome, Cartuccia cartuccia) {
        this.nome = nome;
        this.vassoio = new Vassoio();    // composizione: lo crea la stampante
        this.cartuccia = cartuccia;      // aggregazione: arriva da fuori
    }

    public void caricaCarta(int n) {
        vassoio.aggiungi(n);
    }

    public void cambiaCartuccia(Cartuccia nuova) {
        this.cartuccia = nuova;
    }

    public void stampa(String documento, int pagine) {
        if (vassoio.getFogli() < pagine) {
            System.out.println(nome + ": carta insufficiente");
        } else if (cartuccia.getPagine() < pagine) {
            System.out.println(nome + ": inchiostro insufficiente");
        } else {
            vassoio.togli(pagine);
            cartuccia.consuma(pagine);
            System.out.println(nome + ": stampato " + documento);
        }
    }

    @Override
    public String toString() {
        return nome + ": " + vassoio.getFogli() + " fogli, " + cartuccia;
    }
}