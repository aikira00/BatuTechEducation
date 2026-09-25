import java.util.Scanner;

public class EsercizioString {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Inserisci una frase: ");
        String s = sc.nextLine();

        // 1) Lunghezza e conteggi con un ciclo su charAt(i)
        String vocaliMin = "aeiouàèéìòù";
        int vocali = 0, consonanti = 0, cifre = 0, spazi = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // porto la lettera in minuscolo solo per il confronto con le vocali
            char m = (c >= 'A' && c <= 'Z') ? (char) (c + ('a' - 'A')) : c;

            if (vocaliMin.indexOf(m) >= 0) {
                vocali++;
            } else if (m >= 'a' && m <= 'z') {
                consonanti++;              // lettera che non è vocale
            } else if (c >= '0' && c <= '9') {
                cifre++;
            } else if (c == ' ') {
                spazi++;
            }
        }
        System.out.println("Lunghezza: " + s.length());
        System.out.println("Vocali: " + vocali);
        System.out.println("Consonanti: " + consonanti);
        System.out.println("Cifre: " + cifre);
        System.out.println("Spazi: " + spazi);

        // 2) Tutto maiuscolo, a mano: 'a'..'z' -> sottraggo 'a' - 'A'
        StringBuilder maiuscolo = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') {
                c = (char) (c - ('a' - 'A'));
            }
            maiuscolo.append(c);
        }
        System.out.println("Maiuscolo (a mano):  " + maiuscolo);
        System.out.println("Maiuscolo (metodo):  " + s.toUpperCase());

        // 3) Tutto minuscolo, a mano
        StringBuilder minuscolo = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                c = (char) (c + ('a' - 'A'));
            }
            minuscolo.append(c);
        }
        System.out.println("Minuscolo (a mano):  " + minuscolo);
        System.out.println("Minuscolo (metodo):  " + s.toLowerCase());
        // Nota: le lettere accentate non sono coperte dal confronto 'a'..'z',
        // quindi a mano restano invariate, mentre i metodi di String le gestiscono.

        // 4) Numero di parole SENZA split: conto i passaggi spazio -> carattere
        int parole = 0;
        boolean inParola = false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != ' ') {
                if (!inParola) {
                    parole++;
                    inParola = true;
                }
            } else {
                inParola = false;
            }
        }
        System.out.println("Parole (senza split): " + parole);

        // ... e CON split(" "): con spazi doppi produce stringhe vuote e sovrastima
        String t = s.trim();
        int paroleSplit = t.isEmpty() ? 0 : t.split(" ").length;
        System.out.println("Parole (split(\" \")): " + paroleSplit);
        int paroleRegex = t.isEmpty() ? 0 : t.split("\\s+").length;
        System.out.println("Parole (split(\"\\\\s+\")): " + paroleRegex + "  <- robusta con spazi multipli");

        // 5) Ogni parola con l'iniziale maiuscola (resto in minuscolo), a mano
        //    Parto dalla versione minuscola e alzo la lettera dopo ogni spazio
        StringBuilder titolo = new StringBuilder();
        boolean inizioParola = true;
        for (int i = 0; i < minuscolo.length(); i++) {
            char c = minuscolo.charAt(i);
            if (inizioParola && c >= 'a' && c <= 'z') {
                c = (char) (c - ('a' - 'A'));
            }
            titolo.append(c);
            inizioParola = (c == ' ');
        }
        System.out.println("Iniziali maiuscole (a mano): " + titolo);

        // Verifica con i metodi di String (split(" ", -1) conserva gli spazi multipli)
        String[] pezzi = s.split(" ", -1);
        StringBuilder titolo2 = new StringBuilder();
        for (int i = 0; i < pezzi.length; i++) {
            String p = pezzi[i];
            if (!p.isEmpty()) {
                titolo2.append(p.substring(0, 1).toUpperCase())
                       .append(p.substring(1).toLowerCase());
            }
            if (i < pezzi.length - 1) {
                titolo2.append(' ');
            }
        }
        System.out.println("Iniziali maiuscole (metodi): " + titolo2);
        System.out.println("Coincidono? " + titolo.toString().equals(titolo2.toString()));
    }
}
