import java.util.*;

class LinkedList {
    private static class Node {
        int value;
        Node next;

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    Node head;

    void create(int n, Scanner sc) {
        for (int i = 1; i <= n; i++) {
            System.out.print("Enter value for Node " + i + ": ");
            int value = sc.nextInt();
            if (i == 1) {
                head = new Node(value, null);
                System.out.println("Head Node element added!");
            } else {
                add(value,i-1);
            }
        }
    }

    boolean isEmpty() {
        if (head == null) return true;
        return false;
    }

    int size() {
        int size = 0;
        Node current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    void print() {
        Node current = head;
        while (current != null) {
            System.out.print(current.value + (current.next == null ? "" : " -> "));
            current = current.next;
        }
    }

    void add(int value, int index) {
        if (index < 0 || size() < index) System.out.println("Invalid index!");
        else if (index == 0) {
            Node newNode = new Node(value, head);
            head = newNode;
        }
        else {
            Node newNode = new Node(value, null);
            int i = 0;
            Node current = head;
            while (i != index-1) {
                current = current.next;
                i++;
            }
            newNode.next = current.next;
            current.next = newNode;
            System.out.println("Element added!");
        }
    }

    void delete(int index) {
        if (index < 0 || size() < index) System.out.println("Invalid Index!");
        else if (index == 0) {
            head = head.next;
        }
        else {
            int i = 0;
            Node current = head;
            while (i != index - 1) {
                current = current.next;
                i++;
            }
            current.next = current.next.next;
            System.out.println("Deletion successfull!");
        }
    }
}

public class LinkedListMain {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        LinkedList ls = new LinkedList();
        System.out.print("Enter node size: ");
        int n = sc.nextInt();
        ls.create(n, sc);
        System.out.println("LinkedList created!");
        int choice;
        do {
            System.out.println();
            System.out.println("--MENU--");
            System.out.println("1. Size of LL");
            System.out.println("2. Print LL");
            System.out.println("3. Add Node");
            System.out.println("4. Delete node");
            System.out.println("0. Exit");
            System.out.print("Enter Choice: ");
            choice = sc.nextInt();
            switch (choice) {
                case 1 -> System.out.println("Size: " + ls.size());
                case 2 -> ls.print();
                case 3 -> {
                    System.out.print("Enter value: ");
                    int value = sc.nextInt();
                    System.out.print("Enter index: ");
                    int index = sc.nextInt();
                    ls.add(value, index);
                }
                case 4 -> {
                    System.out.print("Enter Index: ");
                    int index = sc.nextInt();
                    ls.delete(index);
                }
            }
        } while (choice != 0);
        sc.close();
    }
}
