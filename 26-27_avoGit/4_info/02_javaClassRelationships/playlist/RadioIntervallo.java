package playlist;

// Il main crea canzoni e playlist: la stessa Campanella finisce in due playlist.
public class RadioIntervallo {
    public static void main(String[] args) {
        // 1. le canzoni della tabella
        Canzone campanella = new Canzone("Campanella", "I Pendolari", 3, 12);
        Canzone ultimaFila = new Canzone("Ultima fila", "I Pendolari", 4, 5);
        Canzone interrogato = new Canzone("Interrogato", "MC Verifica", 2, 58);
        Canzone ricreazione = new Canzone("Ricreazione", "Lady Merenda", 0, 0);
        Canzone nebbia = new Canzone("Nebbia in tangenziale", "I Lampioni", 4, 41);
        Canzone caffe = new Canzone("Caffè delle sette", "Trio Moka", 5, 20);
        Canzone intro = new Canzone("Intro", "Gli Assenti", 1, 15);

        // 2. la playlist della 4B
        Playlist quartaB = new Playlist("4B carica");
        quartaB.aggiungi(campanella);
        quartaB.aggiungi(nebbia);
        quartaB.aggiungi(caffe);
        quartaB.aggiungi(ultimaFila);
        quartaB.aggiungi(ricreazione);
        quartaB.aggiungi(intro);
        System.out.println(quartaB);

        // 3. la playlist della 5A
        Playlist quintaA = new Playlist("5A maturità");
        quintaA.aggiungi(interrogato);
        quintaA.aggiungi(ricreazione);
        quintaA.aggiungi(campanella);
        quintaA.aggiungi(intro);
        quintaA.aggiungi(ultimaFila);
        System.out.println(quintaA);

        // 4. lunedì suona la 4B, martedì la 5A
        quartaB.suona();
        quintaA.suona();

        // 5. gli ascolti
        System.out.println(campanella);
        System.out.println(ultimaFila);
        System.out.println(interrogato);
        System.out.println(ricreazione);
        System.out.println(nebbia);
        System.out.println(caffe);
        System.out.println(intro);
    }
}
