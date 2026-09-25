import java.util.Random;
import java.util.Scanner;

public class GamerTag {
    public static void main(String[] args) {
        String[] aggettivi = {
            "Crazy", "Dark", "Shadow", "Epic", "Silent",
            "Rapid", "Frozen", "Wild", "Electric", "Golden",
            "Mystic", "Brave", "Savage", "Cyber", "Ghost",
            "Infernal", "Atomic", "Steel", "Night", "Turbo"
        };

        String[] creature = {
            "Wolf", "Dragon", "Tiger", "Falcon", "Phoenix",
            "Shark", "Panther", "Eagle", "Cobra", "Raven",
            "Leopard", "Bear", "Fox", "Scorpion", "Viper",
            "Lion", "Kraken", "Hawk", "Jaguar", "Griffin"
        };

        String[] numeri = {
            "7", "13", "21", "42", "99",
            "404", "777", "888", "999", "2000",
            "3000", "5000", "9000", "123", "321",
            "666", "808", "1010", "2025", "2026"
        };

        Random rnd = new Random();
        Scanner sc = new Scanner(System.in);
        String risposta;

        do {
            // nextInt(n) restituisce un intero tra 0 e n-1: indici sempre validi
            int i = rnd.nextInt(aggettivi.length);
            int j = rnd.nextInt(creature.length);
            int k = rnd.nextInt(numeri.length);

            String gamerTag = aggettivi[i] + creature[j] + numeri[k];
            System.out.println("\nNickname generato: " + gamerTag);

            // ripete la domanda finché la risposta non è 's' o 'n'
            do {
                System.out.println("\nVuoi generarne un altro? (s/n)");
                risposta = sc.nextLine().trim().toLowerCase();
            } while (!risposta.equals("s") && !risposta.equals("n"));

        } while (risposta.equals("s"));

        System.out.println("\nArrivederci!");
    }
}
