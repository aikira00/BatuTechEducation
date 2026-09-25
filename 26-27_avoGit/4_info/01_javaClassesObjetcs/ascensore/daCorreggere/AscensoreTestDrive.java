class Ascensore {
    int piano;
    int capienza;

    void sali() {
        piano = piano + 1;
        System.out.println("Salgo al piano " + piano);
    }

    void scendi() {
        piano = piano - 1;
        System.out.println("Scendo al piano " + piano);
    }
}

class AscensoreTestDrive {
    public static void main(String[] args) {
        Ascensore a = new Ascensore();
        a.piano = 3;
        a.apriPorte();
        a.sali();
    }
}
