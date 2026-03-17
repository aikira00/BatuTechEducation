public class Es5SquareNumbers implements Runnable {
    private int number;

    public Es5SquareNumbers(int number) {
        this.number = number;
    }

    public void run()  {
        String threadName = Thread.currentThread().getName();
        int square = number * number;
        /*try {
            Thread.sleep(2000); // 1000 ms = 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        System.out.println(threadName + " - Il quadrato di " + number + " è: " + square);
    }

    public static void main(String[] args) {
        for (int i = 10; i <= 20; i++) {
            Thread thread = new Thread(new Es5SquareNumbers(i));
            thread.start();
        }
    }
}
