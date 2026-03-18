package JvTh06;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Enter the first integer: ");
            int a = Integer.parseInt(reader.readLine());

            System.out.print("Enter the second integer: ");
            int b = Integer.parseInt(reader.readLine());

            // Create one Runnable per operation
            char[] operators = {'+', '-', '*', '/'};
            OperationThread[] operations = new OperationThread[4];
            Thread[] threads = new Thread[4];

            for (int i = 0; i < 4; i++) {
                operations[i] = new OperationThread(a, b, operators[i]);
                threads[i] = new Thread(operations[i]);
                threads[i].start();
            }

            // Wait for all threads to finish
            for (Thread t : threads) {
                t.join();
            }

            // Print results
            System.out.println();
            System.out.println("=== RESULTS ===");
            for (OperationThread op : operations) {
                System.out.println(op);
            }

        } catch (IOException e) {
           throw new RuntimeException(e);
        } catch (NumberFormatException e) {
            System.out.println("Error: please enter a valid integer.");
        } catch (InterruptedException e) {
            System.out.println("Error: thread interrupted.");
        }
    }
}