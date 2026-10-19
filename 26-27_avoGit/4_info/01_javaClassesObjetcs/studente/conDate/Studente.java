package conDate;

// "Dal diagramma al codice: lo studente" (esercitazione 12): una classe con due attributi di tipo Data.
// Le date le crea lo studente stesso, nel costruttore e in iscrivi: composizione. Il main non ha mai
// un riferimento alle date interne, tranne quello che fa uscire il getter (domanda 2).
// Nessun setter: nome, cognome e nascita si fissano alla nascita dell'oggetto; l'iscrizione cambia solo con iscrivi.
public class Studente {
    private String nome;
    private String cognome;
    private Data dataDiNascita;
    private Data dataIscrizione;

    public Studente(String nome, String cognome, int giorno, int mese, int anno) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = new Data(giorno, mese, anno);
        if (!dataDiNascita.controlloData()) {
            System.out.println(nome + " " + cognome + ": data di nascita " + dataDiNascita + " non valida, metto 01/01/2000");
            this.dataDiNascita = new Data(1, 1, 2000);
        }
        this.dataIscrizione = new Data(1, 9, 2026);   // iscritto a quest'anno, finché non si dice altro
    }

    // Non è un setter: ha le regole, e se la data non va bene lascia quella che c'era
    public void iscrivi(int giorno, int mese, int anno) {
        Data nuova = new Data(giorno, mese, anno);
        if (!nuova.controlloData()) {
            System.out.println(nome + " " + cognome + ": data di iscrizione " + nuova + " non valida, resto al " + dataIscrizione);
        } else if (etaAl(nuova) < 0) {
            System.out.println(nome + " " + cognome + ": iscrizione " + nuova + " prima della nascita, resto al " + dataIscrizione);
        } else {
            dataIscrizione = nuova;
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
