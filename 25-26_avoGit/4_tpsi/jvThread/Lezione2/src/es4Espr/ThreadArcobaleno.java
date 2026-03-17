package es4Espr;

public class ThreadArcobaleno extends ThreadColorato {
    @Override
    public void body() throws InterruptedException {
        ThreadGiallo threadGiallo = new ThreadGiallo();
        ThreadVerde threadVerde = new ThreadVerde();

        threadGiallo.setInput(this.getA(), this.getB(), this.getC());
        threadVerde.setInput(this.getA(), this.getB(), this.getC());

        threadGiallo.start();
        threadVerde.start();
        threadGiallo.join();
        threadVerde.join();

        double g = threadGiallo.getRisultato();
        double v = threadVerde.getRisultato();

        this.setRisultato(g + v);
    }
}
