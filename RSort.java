import java.util.*;

public class RSort {

    public static int getMax(LinkedList<Integer> list) {
        int max = list.getFirst();
        for (int num : list) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }

    public static void radixSort(LinkedList<Integer> list) {

        int max = getMax(list);

        List<LinkedList<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            buckets.add(new LinkedList<>());
        }

        for (int exp = 1; max / exp > 0; exp *= 10) {

            while (!list.isEmpty()) {
                int num = list.removeFirst();
                int digit = (num / exp) % 10;
                buckets.get(digit).add(num);
            }

            for (int i = 0; i < 10; i++) {
                while (!buckets.get(i).isEmpty()) {
                    list.add(buckets.get(i).removeFirst());
                }
            }
        }
    }

    public static void main(String[] args) {

        LinkedList<Integer> list = new LinkedList<>();
        list.add(170);
        list.add(45);
        list.add(75);
        list.add(90);
        list.add(802);
        list.add(24);
        list.add(2);
        list.add(66);

        System.out.println("Before Sorting:");
        System.out.println(list);

        radixSort(list);

        System.out.println("After Sorting:");
        System.out.println(list);
    }
}
