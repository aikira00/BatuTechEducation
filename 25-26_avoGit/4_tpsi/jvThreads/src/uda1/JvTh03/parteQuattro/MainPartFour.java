package JvTh03.parteQuattro;

public class MainPartFour {
        public static void main(String[] args) {
            MyThread t1 = new MyThread();
            t1.start();
            try {
                t1.join(); // Aspetta la fine del thread
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Il valore calcolato dal thread è: " + t1.getResult());
        }
}

