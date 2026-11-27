package elfRacePart1;

import java.util.Scanner;

public class ChristmasRace {
    public static void main(String[] args) {
        // 1. gli ordini di Babbo Natale
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Babbo Natale, quanti regali per squadra? ");
        int gifts = keyboard.nextInt();
        System.out.print("Babbo Natale, quanti turni dura la gara? ");
        int turns = keyboard.nextInt();

        // 2. gli elfi della tabella
        Elf pigna = new Elf("Pigna", "postino");
        Elf trucciolo = new Elf("Trucciolo", "falegname");
        Elf nastrina = new Elf("Nastrina", "impacchettatore");
        Elf vischio = new Elf("Vischio", "postino");
        Elf tizzone = new Elf("Tizzone", "falegname");
        Elf brina = new Elf("Brina", "impacchettatore");
        Elf cannella = new Elf("Cannella", "falegname");
        Elf zenzero = new Elf("Zenzero", "impacchettatore");
        Elf bisco = new Elf("Bisco", "tuttofare");          // passo 4
        Elf scintilla = new Elf("Scintilla", "tuttofare");  // passo 4

        // 3. le squadre
        Team snowflakes = new Team("I Fiocchi", gifts);
        snowflakes.add(pigna);
        snowflakes.add(trucciolo);
        snowflakes.add(nastrina);
        Team baubles = new Team("Le Palline", gifts);
        baubles.add(brina);
        baubles.add(tizzone);
        baubles.add(vischio);
        Team stockings = new Team("Le Calze", gifts);
        stockings.add(cannella);
        stockings.add(zenzero);
        Team candyCanes = new Team("I Bastoncini", gifts);   // passo 4
        candyCanes.add(bisco);
        candyCanes.add(scintilla);

        // le letterine: a ogni squadra le sue, una per regalo; i desideri a giro
        String[] wishes = {"un trenino", "una bambola", "un pallone"};
        for (int i = 0; i < gifts; i++) {
            String wish = wishes[i % wishes.length];
            snowflakes.receive(new Letter(wish));
            baubles.receive(new Letter(wish));
            stockings.receive(new Letter(wish));
            candyCanes.receive(new Letter(wish));   // passo 4
        }

        // 4. la fabbrica e la gara
        Workshop workshop = new Workshop();
        workshop.add(snowflakes);
        workshop.add(baubles);
        workshop.add(stockings);
        workshop.add(candyCanes);   // passo 4
        workshop.race(turns);

        // 5. Babbo Natale ritira i regali pronti: ogni squadra gli dà i suoi
        System.out.println("--- Babbo Natale ritira i regali ---");
        Gift[] snowflakesSack = snowflakes.deliver();
        System.out.println(snowflakes.getName() + ": " + snowflakesSack.length + " regali");
        Gift[] baublesSack = baubles.deliver();
        System.out.println(baubles.getName() + ": " + baublesSack.length + " regali");
        Gift[] stockingsSack = stockings.deliver();
        System.out.println(stockings.getName() + ": " + stockingsSack.length + " regali");
        Gift[] candyCanesSack = candyCanes.deliver();   // passo 4
        System.out.println(candyCanes.getName() + ": " + candyCanesSack.length + " regali");

        keyboard.close();
    }
}
