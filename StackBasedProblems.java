import java.util.Scanner;
import java.util.Stack;

public class StackBasedProblems {

    static int factorial(int n) {

        GStack<Integer> stack = new GStack<>();
        stack.setCapacity(n - 1);

        while (n > 1) {
            stack.push(n);
            n--;
        }

        int result = 1;

        while (!stack.isEmpty()) {
            result *= stack.pop();
        }

        return result;
    }

    static int fib(int n) {

        if (n <= 1)
            return n;

        GStack<Integer> stack = new GStack<>();
        stack.setCapacity(n);
        stack.push(n);

        int sum = 0;

        while (!stack.isEmpty()) {
            int temp = stack.pop();

            if (temp <= 1) {
                sum += temp;
            } else {
                stack.push(temp - 1);
                stack.push(temp - 2);
            }
        }

        return sum;
    }

    static int gcd(int m, int n) {

        GStack<int[]> stack = new GStack<>();
        stack.setCapacity(n);
        stack.push(new int[] { m, n });

        int result = 0;

        while (!stack.isEmpty()) {
            int[] values = stack.pop();
            m = values[0];
            n = values[1];

            if (n == 0) {
                result = m;
            } else {
                stack.push(new int[] { n, m % n });
            }
        }

        return result;
    }

    static void convert(int n) {

        GStack<Integer> stack = new GStack<>();
        stack.setCapacity(n);

        while (n > 0) {
            stack.push(n % 2);
            n = n / 2;
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
        System.out.println();
    }

    



    public static void main(String[] args) {

        System.out.println("Factorial: " + factorial(3));
        System.out.println("GCD: " + gcd(12, 36));
        System.out.println("Fibonacci: " + fib(5));
        System.out.print("Binary conversion: ");
        convert(10);

    }
}
