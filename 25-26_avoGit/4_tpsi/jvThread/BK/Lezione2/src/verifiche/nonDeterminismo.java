package verifiche;


    import java.util.Random;

    public class nonDeterminismo {

        public static void main(String[] args) {
            Thread evenThread = new Thread(() -> {
                Random random = new Random();

                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.print(" Even th: ");
                        int num = random.nextInt(100);
                        if (num % 2 == 0) {
                            System.out.print(num);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });

            Thread oddThread = new Thread(() -> {
                Random random = new Random();

                for (int i = 0; i < 5; i++) {
                    try {
                        Thread.sleep(1000);
                        System.out.print(" Odd th: ");
                        int num = random.nextInt(100);
                        if (num % 2 != 0) {
                            System.out.print(num);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });

            evenThread.start();
            oddThread.start();
        }
    }


