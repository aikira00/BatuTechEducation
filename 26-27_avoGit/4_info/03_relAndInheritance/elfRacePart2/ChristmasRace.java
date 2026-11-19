package elfRacePart2;

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
        Elf pigna = new Postman("Pigna");
        Elf trucciolo = new Carpenter("Trucciolo");
        Elf nastrina = new Wrapper("Nastrina");
        Elf vischio = new Postman("Vischio");
        Elf tizzone = new Carpenter("Tizzone");
        Elf brina = new Wrapper("Brina");
        Elf cannella = new Carpenter("Cannella");
        Elf zenzero = new Wrapper("Zenzero");
        Elf bisco = new Handyman("Bisco");
        Elf scintilla = new Handyman("Scintilla");

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

        // 4. la fabbrica e la gara
        Workshop workshop = new Workshop();
        workshop.add(snowflakes);
        workshop.add(baubles);
        workshop.add(stockings);
        workshop.add(candyCanes);   // passo 4
        workshop.race(turns);
    }
}
