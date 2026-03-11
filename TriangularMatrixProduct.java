import java.util.*;

class LowerTriangularMatrix {

    int n;
    int[][] mat;

    LowerTriangularMatrix(int n) {
        this.n = n;
        mat = new int[n][n];
    }

    void set(int i, int j, int value) {
        if (i >= j)
            mat[i][j] = value;
    }

    int get(int i, int j) {
        if (i >= j)
            return mat[i][j];
        else
            return 0;
    }

    void display() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(get(i, j) + " ");
            System.out.println();
        }
    }
}


class UpperTriangularMatrix {

    int n;
    int[] mat;

    UpperTriangularMatrix(int n) {
        this.n = n;
        mat = new int[n * (n + 1) / 2];
    }

    int index(int i, int j) {
        return (i * (2 * n - i + 1)) / 2 + (j - i);
    }

    void set(int i, int j, int value) {
        if (i <= j)
            mat[index(i, j)] = value;
    }

    int get(int i, int j) {
        if (i <= j)
            return mat[index(i, j)];
        else
            return 0;
    }

    void display() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(get(i, j) + " ");
            System.out.println();
        }
    }
}


public class TriangularMatrixProduct {

    static int[][] multiply(LowerTriangularMatrix L, UpperTriangularMatrix U) {

        int n = L.n;
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                int sum = 0;

                for (int k = 0; k < n; k++) {
                    sum += L.get(i, k) * U.get(k, j);
                }

                if (sum != 0)
                    result[i][j] = sum;
            }
        }

        return result;
    }

    static void display(int[][] mat, int n) {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(mat[i][j] + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int n = 3;

        LowerTriangularMatrix L = new LowerTriangularMatrix(n);
        UpperTriangularMatrix U = new UpperTriangularMatrix(n);

        // Lower Triangular Matrix
        L.set(0,0,1);
        L.set(1,0,2);
        L.set(1,1,3);
        L.set(2,0,4);
        L.set(2,1,5);
        L.set(2,2,6);

        // Upper Triangular Matrix
        U.set(0,0,1);
        U.set(0,1,2);
        U.set(0,2,3);
        U.set(1,1,4);
        U.set(1,2,5);
        U.set(2,2,6);

        System.out.println("Lower Triangular Matrix:");
        L.display();

        System.out.println("\nUpper Triangular Matrix:");
        U.display();

        int[][] result = multiply(L, U);

        System.out.println("\nProduct Matrix:");
        display(result, n);
    }
}