
package stampante;

public class Vassoio {
    private int fogli;

    public Vassoio() {
        this.fogli = 0;
    }

    public int getFogli() {
        return fogli;
    }

    public void aggiungi(int n) {
        fogli = fogli + n;
    }

    public void togli(int n) {
        if (n <= fogli) {
            fogli = fogli - n;
        }
    }
}
