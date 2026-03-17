package es4Espr;

public class ThreadVerdeChiaro extends ThreadColorato {
    @Override
    public void body() throws InterruptedException {
        this.setRisultato(7 - (this.getA() / this.getB()) + 22);
    }
}
