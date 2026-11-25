package stampante;

public class LaboratorioTestDrive {
    public static void main(String[] args) {
        // 1. si compra la cartuccia, si installa la stampante
        Cartuccia c1 = new Cartuccia("C1", 16);
        Stampante lab = new Stampante("Lab 2", c1);
        lab.caricaCarta(10);
        System.out.println(lab);

        // 2. due computer collegati alla stessa stampante
        Computer pc1 = new Computer("PC-01");
        Computer pc2 = new Computer("PC-02");
        pc1.aggiungiStampante(lab);
        pc2.aggiungiStampante(lab);

        // 3. si stampa
        pc1.stampa("Relazione", 6, 0);
        pc2.stampa("Tesina", 6, 0);
        System.out.println(lab);

        // 4. si aggiunge carta e si riprova
        lab.caricaCarta(10);
        pc2.stampa("Tesina", 6, 0);
        pc1.stampa("Orario", 5, 0);
        System.out.println(lab);
        System.out.println(c1);

        // 5. si cambia la cartuccia
        Cartuccia c2 = new Cartuccia("C2", 20);
        lab.cambiaCartuccia(c2);
        pc1.stampa("Orario", 5, 0);
        System.out.println(lab);
        System.out.println(c1);

        // 6. una seconda stampante, solo per PC-02
        Stampante aula = new Stampante("Aula 3", c1);
        aula.caricaCarta(8);
        pc2.aggiungiStampante(aula);
        pc2.stampa("Avviso", 2, 1);
        pc1.stampa("Avviso", 2, 1);
        System.out.println(aula);
        System.out.println(lab);
    }
}