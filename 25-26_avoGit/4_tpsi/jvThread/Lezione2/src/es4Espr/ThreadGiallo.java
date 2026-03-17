
package es4Espr;
public class ThreadGiallo extends ThreadColorato {
    @Override
    public void body() throws InterruptedException {
        ThreadGialloChiaro threadGialloChiaro = new ThreadGialloChiaro();
        ThreadGialloScuro threadGialloScuro = new ThreadGialloScuro();

        threadGialloChiaro.setInput(this.getA(), this.getB(), this.getC());
        threadGialloScuro.setInput(this.getA(), this.getB(), this.getC());

        threadGialloChiaro.start();
        threadGialloScuro.start();
        threadGialloChiaro.join();
        threadGialloScuro.join();

        double g1 = threadGialloChiaro.getRisultato();
        double g2 = threadGialloScuro.getRisultato();

        this.setRisultato(g1 * g2);
    }
}
