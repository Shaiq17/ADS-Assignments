package DSA_LAB;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.EmptyStackException;

class GStack<T> {
    private int size;
    private int capacity;
    private ArrayList<T> s;
    private int top;

    GStack() {
        s = new ArrayList<>();
        size = 0;
        top = -1;
    }

    void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    boolean isFull() {
        if (size == capacity)
            return true;
        return false;
    }

    boolean isEmpty() {
        if (size == 0)
            return true;
        return false;
    }

    int size() {
        return size;
    }

    void push(T element) {
        if (isFull())
            throw new StackOverflowError();
        else {
            top++;
            s.add(element);
            size++;
        }
    }

    T pop() {
        if (isEmpty())
            throw new EmptyStackException();
        size--;
        T element = s.get(top);
        s.remove(top);
        top--;
        return element;
    }

    T peek() {
        if (isEmpty())
            throw new EmptyStackException();
        return s.get(top);
    }
}

public class GenericStack {

    static int precedence(char op) {
        return switch (op) {
            case '*' , '/' -> 2;
            case '+', '-' -> 1;
            default -> 0;
        };
    }

    static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    static String infixToPostfix(String infix) {
        GStack<Character> stack = new GStack<>();
        stack.setCapacity(infix.length()/2 + 1);
        StringBuilder postfix = new StringBuilder();

        for (int i = 0; i < infix.length(); i++) {
            char c = infix.charAt(i);

            if (Character.isDigit(c)) {
                postfix.append(c);
            } else if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    postfix.append(stack.pop());
                }
                stack.pop();
            } else if (isOperator(c)) {
                while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(c)) {
                    postfix.append(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            postfix.append(stack.pop());
        }

        return postfix.toString();
    }

    static int evaluatePostfix(String postfix) {
        GStack<Integer> stack = new GStack<Integer>();
        stack.setCapacity(postfix.length()/2 + 1);

        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);

            if (Character.isDigit(c)) {
                stack.push(c - '0');
            } else if (isOperator(c)) {
                int b = stack.pop();
                int a = stack.pop();
                switch (c) {
                    case '+' -> stack.push(a + b);
                    case '-' -> stack.push(a - b);
                    case '*' -> stack.push(a * b);
                    case '/' -> {
                        if (b == 0) throw new ArithmeticException();
                        stack.push(a / b);
                    }
                }
            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter stack capacity: ");
        int capacity = sc.nextInt();
        GStack<Integer> stack = new GStack<>();
        stack.setCapacity(capacity);
        int choice;
        do {
            System.out.println("---MENU---");
            System.out.println("1. Push");
            System.out.println("2. Pop");
            System.out.println("3. Peek");
            System.out.println("4. Size");
            System.out.println("5. Infix to Postfix");
            System.out.println("0. Exit");
            System.out.print("Enter your Choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            switch (choice) {
                case 1 -> {
                    System.out.print("Enter element: ");
                    stack.push(sc.nextInt());
                }
                case 2 -> System.out.println(stack.pop());
                case 3 -> System.out.println(stack.peek());
                case 4 -> System.out.println(stack.size());
                case 5 -> {
                    System.out.print("Enter infix expression: ");
                    String infix = sc.nextLine();
                    String postfix = infixToPostfix(infix);
                    System.out.println("Postfix exp: " + postfix);
                    System.out.println("Evaluated expression: " + evaluatePostfix(postfix));
                }
                case 0 -> System.out.println("Program ended!");
            }
            System.out.println();
        } while (choice != 0);
        sc.close();
    }
}
