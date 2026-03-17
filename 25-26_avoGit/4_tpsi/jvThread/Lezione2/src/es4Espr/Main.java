package es4Espr;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        ThreadArcobaleno threadArcobaleno = new ThreadArcobaleno();
        threadArcobaleno.setInput(1, 2, 3);

        threadArcobaleno.start();
        threadArcobaleno.join();

        System.out.println(threadArcobaleno.getRisultato());
    }
}