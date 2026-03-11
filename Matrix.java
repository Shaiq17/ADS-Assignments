import java.util.*;

class Matrices {
    double[][] matrix;
    int m;
    int n;

    Matrices(int m, int n) {
        matrix = new double[m][n];
        this.m = m;
        this.n = n;
    }

    void readElements(Scanner sc) {
        System.out.println("---Enter Elements---");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = sc.nextDouble();
            }
        }
        System.out.println("Elements added!");
    }

    void printElements() {
        System.out.println("---Elements in the matrix---");
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print((int) matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    void addMatrices(double[][] b) {
        if (!(b.length == m && b[0].length == n)) {
            System.out.println("Matrices should be of same M x N!");
        } else {
            double[][] res = new double[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    res[i][j] = matrix[i][j] + b[i][j];
                }
            }
            System.out.println("Addition Successfull!");
            System.out.println("Resulted Matrix: ");
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    System.out.print((int) res[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    void multiplyMatrices(double[][] b) {
        if (n != b[0].length) {
            System.out.println("Matrices not compatible for multiplication!");
        } else {
            double[][] res = new double[m][b.length];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < res.length; j++) {
                    for (int k = 0; k < n; k++) {
                        res[i][j] += matrix[i][k] * b[k][j];
                    }
                }
            }
            System.out.println("Multiplication Successfull!");
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < res[0].length; j++) {
                    System.out.print((int) res[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    double[][] getMinor(double[][] a, int row, int col) {
        int n = a.length;
        double[][] minor = new double[n - 1][n - 1];
        int r = 0;

        for (int i = 0; i < n; i++) {
            if (i == row)
                continue;
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (j == col)
                    continue;
                minor[r][c++] = a[i][j];
            }
            r++;
        }
        return minor;
    }

    double determinant(double[][] a) {
        int n = a.length;
        if (n == 1)
            return a[0][0];
        if (n == 2)
            return a[0][0] * a[1][1] - a[0][1] * a[1][0];

        double det = 0;
        for (int j = 0; j < n; j++) {
            int sign = (j % 2 == 0) ? 1 : -1;
            det += sign * a[0][j] * determinant(getMinor(a, 0, j));
        }
        return det;
    }

    double[][] adjoint(double[][] a) {
        int n = a.length;
        double[][] adj = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sign = ((i + j) % 2 == 0) ? 1 : -1;
                double[][] minor = getMinor(a, i, j);
                adj[j][i] = sign * determinant(minor);
            }
        }
        return adj;
    }

    double[][] inverse() {
        if (determinant(matrix) == 0)
            return null;
        double[][] inv = new double[m][n];
        inv = adjoint(matrix);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                inv[i][j] = inv[i][j] * (1 / determinant(matrix));
            }
        }
        return inv;
    }
}

public class Matrix {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Matrices m = new Matrices(3, 3);
        m.readElements(sc);
        Matrices diagonal = new Matrices(3, 3);
        System.out.println("Diagonal elements: ");
        diagonal.readElements(sc);
        m.addMatrices(diagonal.matrix);
        m.multiplyMatrices(diagonal.matrix);
        System.out.println("Determinant: " + m.determinant(m.matrix));
        System.out.println("Inverse");
        double[][] res = m.inverse();
        if (res == null)
            System.out.println("Inverse doesnt Exist!");
        else {
            for (double[] ds : res) {
                for (double element : ds) {
                    System.out.print(element + " ");
                }
                System.out.println();
            }
        }
        sc.close();
    }
}
