package es4Espr;
public class ThreadVerdeScuro extends ThreadColorato {
    @Override
    public void body() throws InterruptedException {
        this.setRisultato(this.getA() + this.getB() + this.getC());
    }
}
