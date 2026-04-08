/*
Matrice A:
1 2
3 4
Matrice B:
5 6
7 8
Matrice C (A * B):
19 22
43 50 */
public class MatrixMultiplicationSequenziale {

    public static void main(String[] args) {
        int[][] A = generateMatrix(1500, 1500);
        int[][] B = generateMatrix(1500, 1500);

        long start = System.nanoTime();
        int[][] C = multiply(A, B);
        long end = System.nanoTime();

        System.out.println("Tempo senza thread: " + (end - start) / 1e6 + " ms");
        /*int numThreads = Runtime.getRuntime().availableProcessors();
        System.out.println(numThreads);*/
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