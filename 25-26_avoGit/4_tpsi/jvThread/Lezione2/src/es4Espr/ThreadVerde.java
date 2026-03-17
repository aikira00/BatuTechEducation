package es4Espr;

public class ThreadVerde extends ThreadColorato {
    @Override
    public void body() throws InterruptedException {
        ThreadVerdeChiaro threadVerdeChiaro = new ThreadVerdeChiaro();
        ThreadVerdeScuro threadVerdeScuro = new ThreadVerdeScuro();

        threadVerdeChiaro.setInput(this.getA(), this.getB(), this.getC());
        threadVerdeScuro.setInput(this.getA(), this.getB(), this.getC());

        threadVerdeChiaro.start();
        threadVerdeScuro.start();
        threadVerdeChiaro.join();
        threadVerdeScuro.join();

        double v1 = threadVerdeChiaro.getRisultato();
        double v2 = threadVerdeScuro.getRisultato();

        this.setRisultato(v1 / v2);
    }
}
