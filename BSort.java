import java.util.*;

public class BSort {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node insert(Node head, int value) {
        Node newNode = new Node(value);

        if (head == null)
            return newNode;

        Node temp = head;
        while (temp.next != null)
            temp = temp.next;

        temp.next = newNode;
        return head;
    }

    static Node delete(Node head, int[] arr, int indexRef) {
        if (head == null)
            return null;

        arr[indexRef] = head.data;

        return head.next;
    }

    static void bucketSort(int[] arr) {

        int n = arr.length;
        if (n == 0)
            return;

        int max = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > max)
                max = arr[i];

        Node[] buckets = new Node[max + 1];

        for (int i = 0; i < n; i++)
            buckets[arr[i]] = insert(buckets[arr[i]], arr[i]);

        int indexRef = 0;

        for (int i = 0; i <= max; i++) {
            while (buckets[i] != null) {
                buckets[i] = delete(buckets[i], arr, indexRef++);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of elements: ");
        int size = sc.nextInt();
        int[] arr = new int[size];
        System.out.printf("Enter %d elements: ",size);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("Sorted List: ");
        bucketSort(arr);

        for (int num : arr)
            System.out.print(num + " ");
    }
}
