package es4Espr;

public abstract class ThreadColorato extends Thread {
    private double risultato;
    private double a, b, c;
    private boolean calcolato = false;

    public abstract void body() throws InterruptedException;

    @Override
    public final void run() {
        try { this.body(); }
        catch (Exception e) { throw new RuntimeException(e); }
        this.calcolato = true;
    }

    public double getRisultato() {
        if (this.calcolato)
            return this.risultato;

        throw new RuntimeException("Risultato non disponibile");
    }

    public double getA() {
        if (this.calcolato) throw new RuntimeException("Impossibile alterare valore dopo l'esecuzione");
        return this.a;
    }

    public double getB() {
        if (this.calcolato) throw new RuntimeException("Impossibile alterare valore dopo l'esecuzione");
        return this.b;
    }

    public double getC() {
        if (this.calcolato) throw new RuntimeException("Impossibile alterare valore dopo l'esecuzione");
        return this.c;
    }

    public void setRisultato(double risultato) {
        if (this.calcolato) throw new RuntimeException("Impossibile alterare valore dopo l'esecuzione");
        this.risultato = risultato;
    }

    public void setInput(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
