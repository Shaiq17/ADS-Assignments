import java.util.*;

public class StackAlgorithms {

    // ---------------- TOWER OF HANOI (STACK) ----------------
    static void towerOfHanoi(int n) {

        Stack<Object> S = new Stack<>();

        S.push(n);
        S.push('A');
        S.push('B');
        S.push('C');

        while (!S.isEmpty()) {

            char d = (char) S.pop();
            char i = (char) S.pop();
            char s = (char) S.pop();
            n = (int) S.pop();

            if (n > 1) {

                S.push(n - 1);
                S.push(i);
                S.push(s);
                S.push(d);

                S.push(1);
                S.push(s);
                S.push(i);
                S.push(d);

                S.push(n - 1);
                S.push(s);
                S.push(d);
                S.push(i);

            } else {
                System.out.println("Move " + s + " -> " + d);
            }
        }
    }

    // ---------------- RAT IN MAZE (STACK) ----------------
    static class Point {
        int row, col;

        Point(int r, int c) {
            row = r;
            col = c;
        }
    }

    static boolean ratMaze(int[][] maze, int m, int n) {

        Stack<Point> path = new Stack<>();
        int row = 0, col = 0;

        maze[row][col] = 1;

        while (row != m - 1 || col != n - 1) {

            if (col + 1 < n && maze[row][col + 1] == 0) {
                path.push(new Point(row, col));
                col++;
                maze[row][col] = 1;
            }

            else if (row + 1 < m && maze[row + 1][col] == 0) {
                path.push(new Point(row, col));
                row++;
                maze[row][col] = 1;
            }

            else if (col - 1 >= 0 && maze[row][col - 1] == 0) {
                path.push(new Point(row, col));
                col--;
                maze[row][col] = 1;
            }

            else if (row - 1 >= 0 && maze[row - 1][col] == 0) {
                path.push(new Point(row, col));
                row--;
                maze[row][col] = 1;
            }

            else if (path.isEmpty()) {
                return false;
            }

            else {
                Point p = path.pop();
                row = p.row;
                col = p.col;
            }
        }

        return true;
    }

    // ---------------- FACTORIAL (STACK) ----------------
    static int factorial(int n) {

        Stack<Integer> s = new Stack<>();

        while (n > 1) {
            s.push(n);
            n--;
        }

        int fact = 1;

        while (!s.isEmpty()) {
            fact *= s.pop();
        }

        return fact;
    }

    // ---------------- GCD (STACK) ----------------
    static int gcd(int m, int n) {

        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();

        s1.push(m);
        s2.push(n);

        while (!s1.isEmpty()) {

            m = s1.pop();
            n = s2.pop();

            if (m % n != 0) {
                s1.push(n);
                s2.push(m % n);
            } else {
                return n;
            }
        }
        return n;
    }

    // ---------------- FIBONACCI (STACK) ----------------
    static int fibonacci(int n) {

        Stack<Integer> s = new Stack<>();
        s.push(n);

        int fib = 0;

        while (!s.isEmpty()) {

            n = s.pop();

            if (n > 2) {
                s.push(n - 1);
                s.push(n - 2);
            }

            else if (n == 1 || n == 2) {
                fib++;
            }
        }

        return fib;
    }

    // ---------------- DECIMAL TO BINARY (STACK) ----------------
    static int binary(int n) {

        Stack<Integer> S = new Stack<>();
        Stack<Integer> power = new Stack<>();

        int p = 1;
        int b = 0;

        while (n > 0) {
            S.push(n % 2);
            power.push(p);
            p *= 10;
            n /= 2;
        }

        while (!S.isEmpty()) {
            b += S.pop() * power.pop();
        }

        return b;
    }

    // ---------------- MAIN MENU ----------------
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n----- STACK ALGORITHMS MENU -----");
            System.out.println("1. Tower of Hanoi");
            System.out.println("2. Rat in Maze");
            System.out.println("3. Factorial");
            System.out.println("4. GCD");
            System.out.println("5. Fibonacci");
            System.out.println("6. Decimal to Binary");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter number of disks: ");
                    int n = sc.nextInt();
                    towerOfHanoi(n);
                    break;

                case 2:
                    int[][] maze = {
                            { 0, 0, 1, 0 },
                            { 1, 0, 1, 0 },
                            { 1, 0, 0, 0 },
                            { 1, 1, 1, 0 }
                    };
                    System.out.println(ratMaze(maze, 4, 4) ? "Path Found" : "No Path");
                    break;

                case 3:
                    System.out.print("Enter number: ");
                    n = sc.nextInt();
                    System.out.println("Factorial = " + factorial(n));
                    break;

                case 4:
                    System.out.print("Enter m and n: ");
                    int m = sc.nextInt();
                    int k = sc.nextInt();
                    System.out.println("GCD = " + gcd(m, k));
                    break;

                case 5:
                    System.out.print("Enter n: ");
                    n = sc.nextInt();
                    System.out.println("Fibonacci = " + fibonacci(n));
                    break;

                case 6:
                    System.out.print("Enter decimal number: ");
                    n = sc.nextInt();
                    System.out.println("Binary = " + binary(n));
                    break;

                case 7:
                    System.exit(0);

                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}