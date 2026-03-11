package DSA_LAB;

public class Railroad {

    static boolean rearrange(int[] cars, int n) {

        CircularQueue<Integer> q = new CircularQueue<>(n);
        int expected = 1;

        for (int car : cars) {

            if (car == expected) {
                System.out.println("Car " + car + " -> Output");
                expected++;

                while (!q.isEmpty() && q.peek() == expected) {
                    System.out.println("Car " + q.dequeue() + " -> Output");
                    expected++;
                }
            }

            else {
                q.enqueue(car);
                System.out.println("Car " + car + " -> Queue");
            }
        }

        return q.isEmpty();
    }

    public static void main(String[] args) {

        int cars[] = {3,1,2,5,4};
        int n = cars.length;

        if (rearrange(cars, n))
            System.out.println("Rearrangement Possible");
        else
            System.out.println("Rearrangement Not Possible");
    }
}
