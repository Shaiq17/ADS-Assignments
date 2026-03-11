package DSA_LAB;

import java.util.*;

public class CircularQueue<T> {
    private T[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;

    CircularQueue(int capacity) {
        this.capacity = capacity;
        queue = (T[]) new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    boolean isEmpty() {
        return size == 0;
    }

    boolean isFull() {
        return size == capacity;
    }

    T peek() {
        if (isEmpty())
            return null;
        return queue[front];
    }

    void enqueue(T item) {
        if (isFull()) {
            System.out.println("Queue Overflow");
            return;
        }

        rear = (rear + 1) % capacity;
        queue[rear] = item;
        size++;
    }

    T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue Underflow");
        }

        T item = queue[front];
        front = (front + 1) % capacity;
        size--;
        return item;
    }

    void display() {
        if (isEmpty()) {
            System.out.println("Queue Empty");
            return;
        }

        for (int i = 0; i < size; i++) {
            System.out.print(queue[(front + i) % capacity] + " ");
        }
        System.out.println();
    }
}
