public class Es3Main {

    public static void main(String [] args){
        Thread t1 = new Es3DadoThread("Gino");
        Thread t2 = new Es3DadoThread("Pina");
        t1.start();
        t2.start();
    }
}
