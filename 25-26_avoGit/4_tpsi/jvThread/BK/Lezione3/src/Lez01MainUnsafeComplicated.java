import java.util.Locale;
public class Lez01MainUnsafeComplicated {
    public static void main(String[] args) throws InterruptedException {
        long startTime = System.currentTimeMillis();

        Lez01Counter counter = new Lez01Counter();

        int incrementValue1 = 200_000_000;
        int incrementValue2 = 100_000_000;

        Lez01IncrementThread incrementer1 =
                new Lez01IncrementThread(counter, incrementValue1);

        Lez01IncrementThread incrementer2 =
                new Lez01IncrementThread(counter, incrementValue2);

        incrementer1.start();
        incrementer2.start();

        incrementer1.join();
        incrementer2.join();

        int counterValue = counter.getValue();

        long timeElapsed = System.currentTimeMillis() - startTime;

        System.out.format(Locale.ITALIAN, "SUM VALUE: %,d - SHOULD BE: %,d\n",
                counterValue,
                (incrementValue1 + incrementValue2));

        int difference = incrementValue1 + incrementValue2 - counterValue;

        double percent = ((0.0 + difference) /
                (incrementValue1 + incrementValue2)) * 100;

        System.out.format(Locale.ITALIAN,
                "DEFFERENCE: %,d - DIFF: %f %%\n", difference, percent);

        System.out.format(Locale.ITALIAN,
                "FINISHED Counter UNSAFE, elapsed time: %,d ms\n",
                timeElapsed);
    }
}
