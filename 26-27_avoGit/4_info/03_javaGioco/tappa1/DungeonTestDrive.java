package tappa1;

import java.util.Scanner;

public class DungeonTestDrive {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Dado dado = new Dado(6);
        Eroe eroe = new Eroe("Anna");

        Mostro[] mostri = new Mostro[2];
        mostri[0] = new Mostro("Grunt", "goblin", 8);
        mostri[1] = new Mostro("Ossetto", "scheletro", 12);

        for (int i = 0; i < mostri.length && eroe.isVivo(); i++) {
            Mostro m = mostri[i];
            m.descrivi();
            boolean fuggita = false;

            while (eroe.isVivo() && m.isVivo() && !fuggita) {
                System.out.println(eroe + " contro " + m);
                System.out.print("a = attacca, f = fuggi: ");
                String scelta = in.next();

                if (scelta.equals("a")) {
                    eroe.attacca(m, dado);
                    if (m.isVivo()) {
                        m.attacca(eroe, dado);
                    }
                } else {
                    System.out.println(eroe.getNome() + " scappa");
                    fuggita = true;
                }
            }
        }

        if (eroe.isVivo()) {
            System.out.println(eroe + " esce dal dungeon");
        } else {
            System.out.println(eroe.getNome() + " non ce l'ha fatta");
        }
    }
}
