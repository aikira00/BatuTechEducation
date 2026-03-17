package es4Espr;
public class ThreadGialloChiaro extends ThreadColorato {
    @Override
    public void body() throws InterruptedException {
        this.setRisultato((3 * this.getA() + 5) / (this.getB() - this.getC()));
    }
}
