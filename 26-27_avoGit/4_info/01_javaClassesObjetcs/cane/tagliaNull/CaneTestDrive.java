package tagliaNull;

// Il main della slide 26: fido non riceve mai una taglia, e il suo null arriva a setTaglia.
public class CaneTestDrive {
    public static void main(String[] args) {
        Cane fido = new Cane();             // taglia di fido: null
        Cane rex = new Cane();
        rex.setTaglia(fido.getTaglia());    // passa null a setTaglia
    }
}
