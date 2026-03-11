import java.util.*;

class sample {
    
}

class ConvexHull {
    
    static Node head = null;
    static Node hull = null;

    static class Node {
        int x, y;
        Node next, prev;

        Node(int x, int y) {
            this.x = x;
            this.y = y;
            next = prev = this;
        }
    }

    static void insert(int x, int y) {

        Node newNode = new Node(x, y);

        if (head == null) {
            head = newNode;
            return;
        }

        Node last = head.prev;

        last.next = newNode;
        newNode.prev = last;

        newNode.next = head;
        head.prev = newNode;
    }

    static void inputPoints() {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of points: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Enter x y: ");
            int x = sc.nextInt();
            int y = sc.nextInt();
            insert(x, y);
        }
    }

    static int orientation(Node p, Node q, Node r) {

        int val = (q.x - p.x) * (r.y - p.y)
                - (q.y - p.y) * (r.x - p.x);

        if (val == 0) return 0;
        return (val > 0) ? 1 : -1;
    }

    static void addToHull(Node point) {

        Node newNode = new Node(point.x, point.y);

        if (hull == null) {
            hull = newNode;
            return;
        }

        Node last = hull.prev;

        last.next = newNode;
        newNode.prev = last;

        newNode.next = hull;
        hull.prev = newNode;
    }

    static void computeHull() {

        if (head == null || head.next == head)
            return;

        Node start = head;
        Node temp = head.next;

        while (temp != head) {
            if (temp.x < start.x)
                start = temp;
            temp = temp.next;
        }

        Node p = start;

        do {
            addToHull(p);

            Node q = p.next;

            Node r = p.next.next;
            while (r != p) {
                if (orientation(p, q, r) == 1)
                    q = r;
                r = r.next;
            }

            p = q;

        } while (p != start);
    }

    static void displayHull() {

        if (hull == null) {
            System.out.println("No hull found.");
            return;
        }

        System.out.println("\nConvex Hull Points:");

        Node temp = hull;
        do {
            System.out.println(temp.x + " " + temp.y);
            temp = temp.next;
        } while (temp != hull);
    }

    static void innerPoints() {

        System.out.println("\nInner Points:");

        Node temp = head;

        do {
            if (!isInHull(temp)) {
                System.out.println(temp.x + " " + temp.y);
            }
            temp = temp.next;
        } while (temp != head);
    }

    static boolean isInHull(Node point) {

        if (hull == null) return false;

        Node temp = hull;

        do {
            if (temp.x == point.x && temp.y == point.y)
                return true;
            temp = temp.next;
        } while (temp != hull);

        return false;
    }

    public static void main(String[] args) {

        inputPoints();
        computeHull();
        displayHull();
        innerPoints();
    }
}