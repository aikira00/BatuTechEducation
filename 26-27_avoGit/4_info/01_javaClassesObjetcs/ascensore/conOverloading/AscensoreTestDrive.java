package edu.avo.conOverloading;

public class AscensoreTestDrive {
    public static void main(String[] args) {
        Ascensore condominio = new Ascensore(5);        // capienza standard
        Ascensore ospedale = new Ascensore(8, 12);
        System.out.println(condominio);
        System.out.println(ospedale);

        condominio.apriPorte();
        condominio.entrano(3);
        condominio.entrano(2);                          // la quinta persona non ci sta
        condominio.sali();                              // porte ancora aperte
        condominio.chiudiPorte();
        condominio.sali();
        condominio.sali(3);
        condominio.sali(4);                             // oltre l'ultimo piano
        System.out.println(condominio);

        condominio.scendi(5);                           // sotto il piano 0
        condominio.scendi(4);
        condominio.apriPorte();
        condominio.escono(3);
        System.out.println(condominio);
    }
}
