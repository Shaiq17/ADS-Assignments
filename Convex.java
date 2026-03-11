import java.util.*;

class Point {
    int x, y;

    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Node {
    Point data;
    Node next, prev;

    Node(Point data) {
        this.data = data;
    }
}

class DCLL {
    Node head = null;

    void create(Point[] points) {
        if (points.length == 0) return;

        head = new Node(points[0]);
        Node prevNode = head;

        for (int i = 1; i < points.length; i++) {
            Node newNode = new Node(points[i]);
            prevNode.next = newNode;
            newNode.prev = prevNode;
            prevNode = newNode;
        }

        // make circular
        prevNode.next = head;
        head.prev = prevNode;
    }

    Node getFirst() {
        return head;
    }

    void delete(Node node) {

        if (node == null) return;

        // only one node
        if (node.next == node) {
            head = null;
            return;
        }

        node.prev.next = node.next;
        node.next.prev = node.prev;

        if (node == head)
            head = node.next;
    }

    void display() {
        if (head == null) return;

        Node temp = head;
        do {
            System.out.println("(" + temp.data.x + ", " + temp.data.y + ")");
            temp = temp.next;
        } while (temp != head);
    }
}

public class Convex {

    static int cross(Point p1, Point p2, Point p3) {
        return (p2.x - p1.x) * (p3.y - p1.y) -
                (p2.y - p1.y) * (p3.x - p1.x);
    }

    static void sortPoints(Point[] points) {
        Point p0 = points[0];

        for (int i = 1; i < points.length; i++) {
            if (points[i].y < p0.y ||
                    (points[i].y == p0.y && points[i].x < p0.x))
                p0 = points[i];
        }

        Point finalP0 = p0;

        Arrays.sort(points, (a, b) -> {
            int cp = cross(finalP0, a, b);
            if (cp == 0)
                return dist(finalP0, a) - dist(finalP0, b);
            return -Integer.compare(cp, 0);
        });
    }

    static int dist(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) +
                (p1.y - p2.y) * (p1.y - p2.y);
    }

    static void getConvexHull(Point[] points) {

        sortPoints(points);

        DCLL list = new DCLL();
        list.create(points);

        boolean changed = true;

        while (changed) {

            changed = false;

            Node x = list.getFirst();

            if (x == null || x.next == x)
                break;

            do {
                Node xr = x.next;
                Node xrr = xr.next;

                if (cross(x.data, xr.data, xrr.data) <= 0) {

                    list.delete(xr);
                    changed = true;
                    break;   // restart checking from beginning
                }

                x = x.next;

            } while (x.next.next != list.getFirst());
        }

        System.out.println("Vertices of Convex Hull:");
        list.display();
    }

    public static void main(String[] args) {

        Point[] points = {
                new Point(0, 0),
                new Point(5, 0),
                new Point(10, 0),
                new Point(10, 5),
                new Point(10, 10),
                new Point(5, 10),
                new Point(0, 10),
                new Point(0, 5),
                new Point(5, 5)
        };

        getConvexHull(points);
    }
}