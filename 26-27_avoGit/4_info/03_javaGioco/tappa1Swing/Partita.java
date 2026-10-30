package tappa1Swing;

// Quello che nella console faceva il while del main: qui ogni metodo è un turno.
public class Partita {
    private Vista vista;
    private Dado dado;
    private Eroe eroe;
    private Mostro[] mostri;
    private int corrente;

    public Partita(Vista vista) {
        this.vista = vista;
        this.dado = new Dado(6);
        this.eroe = new Eroe("Anna", vista);
        this.mostri = new Mostro[2];
        this.mostri[0] = new Mostro("Grunt", "goblin", 8, vista);
        this.mostri[1] = new Mostro("Ossetto", "scheletro", 12, vista);
        this.corrente = 0;
    }

    public void inizia() {
        mostri[corrente].descrivi();
        annunciaTurno();
    }

    public void attacca() {
        if (isFinita()) {
            return;
        }
        Mostro m = mostri[corrente];
        eroe.attacca(m, dado);
        if (m.isVivo()) {
            m.attacca(eroe, dado);
        } else {
            prossimoMostro();
        }
        annunciaTurno();
    }

    public void fuggi() {
        if (isFinita()) {
            return;
        }
        vista.mostra(eroe.getNome() + " scappa");
        prossimoMostro();
        annunciaTurno();
    }

    public boolean isFinita() {
        return !eroe.isVivo() || corrente == mostri.length;
    }

    public Eroe getEroe() {
        return eroe;
    }

    // null quando i mostri sono finiti
    public Mostro getMostro() {
        if (corrente < mostri.length) {
            return mostri[corrente];
        }
        return null;
    }

    private void prossimoMostro() {
        corrente++;
        if (corrente < mostri.length) {
            mostri[corrente].descrivi();
        }
    }

    private void annunciaTurno() {
        if (!isFinita()) {
            vista.mostra(eroe + " contro " + mostri[corrente]);
        } else if (eroe.isVivo()) {
            vista.mostra(eroe + " esce dal dungeon");
        } else {
            vista.mostra(eroe.getNome() + " non ce l'ha fatta");
        }
    }
}
