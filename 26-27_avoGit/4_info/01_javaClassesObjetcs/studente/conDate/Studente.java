package conDate;

// "Dal diagramma al codice: lo studente" (esercitazione 12): una classe che ha due attributi di tipo Data.
// Le date si copiano nel costruttore: lo studente non cambia se qualcuno modifica la Data usata per crearlo.
// Nessun setter: nome, cognome e date si fissano alla nascita dell'oggetto.
public class Studente {
    private String nome;
    private String cognome;
    private Data dataDiNascita;
    private Data dataIscrizione;

    public Studente(String nome, String cognome, Data dataDiNascita, Data dataIscrizione) {
        this.nome = nome;
        this.cognome = cognome;

        if (dataDiNascita.controlloData()) {
            this.dataDiNascita = new Data(dataDiNascita.getGiorno(), dataDiNascita.getMese(), dataDiNascita.getAnno());
        } else {
            System.out.println(nome + " " + cognome + ": data di nascita " + dataDiNascita + " non valida, metto 01/01/2000");
            this.dataDiNascita = new Data(1, 1, 2000);
        }

        if (!dataIscrizione.controlloData()) {
            System.out.println(nome + " " + cognome + ": data di iscrizione " + dataIscrizione + " non valida, metto 01/09/2026");
            this.dataIscrizione = new Data(1, 9, 2026);
        } else if (etaAl(dataIscrizione) < 0) {
            System.out.println(nome + " " + cognome + ": iscrizione " + dataIscrizione + " prima della nascita, metto 01/09/2026");
            this.dataIscrizione = new Data(1, 9, 2026);
        } else {
            this.dataIscrizione = new Data(dataIscrizione.getGiorno(), dataIscrizione.getMese(), dataIscrizione.getAnno());
        }
    }

    public String getNome() {
        return nome;
    }

    public String getCognome() {
        return cognome;
    }

    public Data getDataDiNascita() {
        return dataDiNascita;
    }

    public Data getDataIscrizione() {
        return dataIscrizione;
    }

    // Anni compiuti nel giorno indicato: la differenza fra gli anni, meno uno se il compleanno non è ancora arrivato.
    // Si confrontano i getter delle due date, mai le date fra loro.
    public int etaAl(Data giorno) {
        int anni = giorno.getAnno() - dataDiNascita.getAnno();
        boolean compleannoPassato = giorno.getMese() > dataDiNascita.getMese()
                || (giorno.getMese() == dataDiNascita.getMese() && giorno.getGiorno() >= dataDiNascita.getGiorno());
        if (!compleannoPassato) {
            anni = anni - 1;
        }
        return anni;
    }

    public int etaAllIscrizione() {
        return etaAl(dataIscrizione);
    }

    public boolean isMaggiorenneAl(Data giorno) {
        return etaAl(giorno) >= 18;
    }

    @Override
    public String toString() {
        return cognome + " " + nome + ", nascita " + dataDiNascita.toStringAlfabetico()
                + ", iscrizione " + dataIscrizione.toStringAlfabetico();
    }
}
