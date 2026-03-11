import java.util.Scanner;

class Array {
    int[] a;
    int size;

    Array(int size) {
        this.size = size;
        a = new int[size];
    }

    void readElements(Scanner sc) {
        int evenIndex = 0;
        int oddIndex = size / 2;
        for (int i = 0; i < size; i++) {
            int element = sc.nextInt();
            if (element % 2 == 0) a[evenIndex++] = element;
            else a[oddIndex++] = element;
        }
    }

    void printElements() {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
    }
}

public class ArrayInsertion {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter no. of elements: ");
        int size = sc.nextInt();
        Array a = new Array(size);
        System.out.println("Enter elements");
        a.readElements(sc);
        System.out.println("Elements in the Array");
        a.printElements();
        sc.close();
    }
}
