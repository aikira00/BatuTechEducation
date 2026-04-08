public class MatrixMultiplicationConcRow {
    public static void main(String[] args) {
        int[][] A = generateMatrix(1500, 1500);
        int[][] B = generateMatrix(1500, 1500);
        int[][] C = new int[A.length][B[0].length];

        long start = System.nanoTime();
        Thread[] threads = new Thread[A.length];
        for (int i = 0; i < A.length; i++) {
            threads[i] = new RowMultiplier(i, A, B, C);
            threads[i].start();
        }

        for (Thread t : threads) {
            try { t.join(); } catch (InterruptedException e) { e.printStackTrace(); }
        }
        long end = System.nanoTime();
        System.out.println("Tempo con un thread per riga: " + (end - start) / 1e6 + " ms");
    }

    public static int[][] multiply(int[][] A, int[][] B) {
        int rows = A.length, cols = B[0].length, sumLen = B.length;
        int[][] C = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                for (int k = 0; k < sumLen; k++) {
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }
        return C;
    }

    public static int[][] generateMatrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = (int) (Math.random() * 10);
            }
        }
        return matrix;
    }
}

class RowMultiplier extends Thread {
    private final int row;
    private final int[][] A, B, C;

    public RowMultiplier(int row, int[][] A, int[][] B, int[][] C) {
        this.row = row;
        this.A = A;
        this.B = B;
        this.C = C;
    }

    public void run() {
        int cols = B[0].length, sumLen = B.length;
        for (int j = 0; j < cols; j++) {
            for (int k = 0; k < sumLen; k++) {
                C[row][j] += A[row][k] * B[k][j];
            }
        }
    }
}