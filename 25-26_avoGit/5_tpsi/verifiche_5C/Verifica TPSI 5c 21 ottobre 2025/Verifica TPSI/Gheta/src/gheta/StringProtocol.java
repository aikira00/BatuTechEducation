package verificagheta;

public class StringProtocol {
    public static String elabora(String richiesta) {
        try {
            String[] parti = richiesta.split(";");
            String comando = parti[0].toUpperCase();

            switch (comando) {
                case "COUNT":
                    return "Lunghezza: " + parti[1].length();

                case "CHANGE":
                    if (parti.length < 4)
                        return "Errore: formato non valido (usa CHANGE;da;a;stringa)";
                    String da = parti[1];
                    String a = parti[2];
                    String str = parti[3];
                    return str.replace(da, a);

                case "CHECK":
                    String testo = parti[1].toLowerCase();
                    int vocali = 0;
                    int consonanti = 0;
                    for (int i = 0; i < testo.length(); i++) {
                        char c = testo.charAt(i);
                        if ("aeiou".indexOf(c) != -1)
                            vocali++;
                        else if (Character.isLetter(c))
                            consonanti++;
                    }
                    return String.valueOf(vocali > consonanti);

                case "INVIBIN":
                    char[] arr = parti[1].toCharArray();
                    for (int i = 0; i < arr.length - 1; i += 2) {
                        char tmp = arr[i];
                        arr[i] = arr[i + 1];
                        arr[i + 1] = tmp;
                    }
                    return new String(arr);

                case "REVERSE":
                    return new StringBuilder(parti[1]).reverse().toString();

                case "VOWEL":
                    int count = 0;
                    for (char c : parti[1].toLowerCase().toCharArray()) {
                        if ("aeiou".indexOf(c) != -1)
                            count++;
                    }
                    return "Numero vocali: " + count;

                default:
                    return "Errore: comando non riconosciuto";
            }
        } catch (Exception e) {
            return "Errore: formato non valido (" + e.getMessage() + ")";
        }
    }
}
