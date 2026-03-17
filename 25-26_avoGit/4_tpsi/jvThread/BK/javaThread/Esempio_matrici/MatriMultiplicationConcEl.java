public class MatriMultiplicationConcEl {
    public static void main(String[] args) {
        int[][] A = generateMatrix(1500, 1500);
        int[][] B = generateMatrix(1500, 1500);
        int[][] C = new int[A.length][B[0].length];

        long start = System.nanoTime();
        int rows = A.length, cols = B[0].length;
        Thread[][] threads = new Thread[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                threads[i][j] = new ElementMultiplier(i, j, A, B, C);
                threads[i][j].start();
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                try { threads[i][j].join(); } catch (InterruptedException e) { e.printStackTrace(); }
            }
        }
        long end = System.nanoTime();

        System.out.println("Tempo con un thread per ogni elemento: " + (end - start) / 1e6 + " ms");
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

class ElementMultiplier extends Thread {
    private final int i, j;
    private final int[][] A, B, C;

    public ElementMultiplier(int i, int j, int[][] A, int[][] B, int[][] C) {
        this.i = i;
        this.j = j;
        this.A = A;
        this.B = B;
        this.C = C;
    }

    public void run() {
        int sumLen = B.length;
        for (int k = 0; k < sumLen; k++) {
            C[i][j] += A[i][k] * B[k][j];
        }
    }
}
