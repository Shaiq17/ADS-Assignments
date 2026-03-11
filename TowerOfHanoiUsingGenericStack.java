package DSA_LAB;

import java.util.*;
import DSA_LAB.GenericStack;

public class TowerOfHanoiUsingGenericStack {

    static void moveDisk(GStack<Integer> from,
            GStack<Integer> to,
            char fromRod,
            char toRod) {

        if (from.isEmpty()) {
            int disk = to.pop();
            from.push(disk);
            System.out.println("Move disk " + disk +
                    " from " + toRod + " to " + fromRod);
        }

        else if (to.isEmpty()) {
            int disk = from.pop();
            to.push(disk);
            System.out.println("Move disk " + disk +
                    " from " + fromRod + " to " + toRod);
        }

        else {
            int fromTop = from.peek();
            int toTop = to.peek();

            if (fromTop > toTop) {
                int disk = to.pop();
                from.push(disk);
                System.out.println("Move disk " + disk +
                        " from " + toRod + " to " + fromRod);
            } else {
                int disk = from.pop();
                to.push(disk);
                System.out.println("Move disk " + disk +
                        " from " + fromRod + " to " + toRod);
            }
        }
    }

    static void solve(int n) {

        GStack<Integer> src = new GStack<>();
        GStack<Integer> aux = new GStack<>();
        GStack<Integer> dest = new GStack<>();

        src.setCapacity(n);
        aux.setCapacity(n);
        dest.setCapacity(n);

        char s = 'A', a = 'B', d = 'C';

        for (int i = n; i >= 1; i--) {
            src.push(i);
        }

        if (n % 2 == 0) {
            char temp = d;
            d = a;
            a = temp;

            GStack<Integer> tempStack = dest;
            dest = aux;
            aux = tempStack;
        }

        int totalMoves = (int) Math.pow(2, n) - 1;

        for (int i = 1; i <= totalMoves; i++) {

            if (i % 3 == 1)
                moveDisk(src, dest, s, d);

            else if (i % 3 == 2)
                moveDisk(src, aux, s, a);

            else
                moveDisk(aux, dest, a, d);
        }
    }

    public static void main(String[] args) {
        solve(3);
    }
}