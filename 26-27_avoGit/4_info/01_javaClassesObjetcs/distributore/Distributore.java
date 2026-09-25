public class Distributore {

    // variabili d'istanza: cosa sa
    private int lattine = 2;
    private boolean acceso = true;

    // metodi: cosa fa
    public void eroga() {
        if (acceso) {
            System.out.println("clunk! ecco la tua lattina");
            lattine = lattine - 1;
        } else {
            System.out.println("distributore spento: niente lattina");
        }
    }

    public int getLattine() {
        return lattine;
    }

    public boolean isAcceso() {
        return acceso;
    }

    public void setAcceso(boolean a) {
        acceso = a;
    }
}
