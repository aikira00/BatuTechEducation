package dungeon2;

import java.util.Scanner;

public class DungeonTestDrive {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Dado dado = new Dado(6);
        Arma pugnale = new Arma("il pugnale", 1);
        Arma ascia = new Ascia();
        Eroe eroe = new Eroe("Anna", pugnale);

        Mostro[] mostri = new Mostro[2];
        mostri[0] = new Goblin("Grunt", 8);
        mostri[1] = new Scheletro("Ossetto", 12);

        for (int i = 0; i < mostri.length && eroe.isVivo(); i++) {
            Mostro m = mostri[i];
            m.descrivi();

            System.out.print("1 = pugnale, 2 = ascia: ");
            if (in.next().equals("2")) {
                eroe.impugna(ascia);
            } else {
                eroe.impugna(pugnale);
            }

            boolean fuggita = false;
            while (eroe.isVivo() && m.isVivo() && !fuggita) {
                System.out.println(eroe + " contro " + m);
                System.out.print("a = attacca, c = cura, f = fuggi: ");
                String scelta = in.next();

                if (scelta.equals("a")) {
                    eroe.attacca(m, dado);
                    if (m.isVivo()) {
                        m.attacca(eroe, dado);
                    }
                } else if (scelta.equals("c")) {
                    eroe.cura();
                    m.attacca(eroe, dado);
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
