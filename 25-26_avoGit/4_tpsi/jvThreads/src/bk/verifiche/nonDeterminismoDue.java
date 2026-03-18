package verifiche;

public class nonDeterminismoDue {

         static class ThreadOne extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread 1: " + i);
                }
            }
        }

    static class ThreadTwo extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Thread 2: " + i);
                }
            }
        }

        public static void main(String[] args) {
            ThreadOne thread1 = new ThreadOne();
            ThreadTwo thread2 = new ThreadTwo();

            thread1.start();
            thread2.start();
        }
    }
