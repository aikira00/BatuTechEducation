package JvTh03;

class MainPartTwo {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();
        t1.start();
        t2.start();
        try {
            t1.join(); // Aspetta la terminazione di t1
            t2.join(); // Aspetta la terminazione di t2
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Main terminato dopo la fine dei thread.");
    }
}