#include <bits/stdc++.h>
using namespace std;

// ================= HEAP TREE DATA STRUCTURE =================
class Heap {
private:
    int arr[100];
    int size;

public:
    Heap() {
        size = 0;
    }

    // Insert (Heap Tree Property maintain)
    void insert(int val) {
        arr[size] = val;
        int i = size;
        size++;

        // Heapify Up
        while (i > 0 && arr[(i - 1) / 2] < arr[i]) {
            swap(arr[i], arr[(i - 1) / 2]);
            i = (i - 1) / 2;
        }
    }

    // Heapify Down (used in delete & heapsort)
    void heapify(int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < size && arr[left] > arr[largest])
            largest = left;

        if (right < size && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            swap(arr[i], arr[largest]);
            heapify(largest);
        }
    }

    // Delete Root (Max Element)
    void deleteRoot() {
        if (size == 0) return;

        arr[0] = arr[size - 1];
        size--;
        heapify(0);
    }

    int getRoot() {
        if (size == 0) return -1;
        return arr[0];
    }

    int getSize() {
        return size;
    }

    int getElement(int i) {
        return arr[i];
    }

    void setSize(int s) {
        size = s;
    }

    void display() {
        for (int i = 0; i < size; i++)
            cout << arr[i] << " ";
        cout << endl;
    }
};

// ================= HEAP SORT =================
class HeapSort {
public:
    static void heapify(int arr[], int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left < n && arr[left] > arr[largest])
            largest = left;

        if (right < n && arr[right] > arr[largest])
            largest = right;

        if (largest != i) {
            swap(arr[i], arr[largest]);
            heapify(arr, n, largest);
        }
    }

    static void sort(int arr[], int n) {
        // Build Heap
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // Extract Elements
        for (int i = n - 1; i > 0; i--) {
            swap(arr[0], arr[i]);
            heapify(arr, i, 0);
        }
    }
};

// ================= PRIORITY QUEUE =================
class PriorityQueue {
private:
    Heap heap;

public:
    void push(int val) {
        heap.insert(val);
    }

    void pop() {
        heap.deleteRoot();
    }

    int top() {
        return heap.getRoot();
    }

    bool empty() {
        return heap.getSize() == 0;
    }

    void display() {
        heap.display();
    }
};

// ================= MAIN =================
int main() {

    // -------- HEAP TREE --------
    cout << "Heap Tree:\n";
    Heap h;
    h.insert(10);
    h.insert(20);
    h.insert(5);
    h.insert(30);

    h.display();

    cout << "Root (Max): " << h.getRoot() << endl;

    h.deleteRoot();
    cout << "After deletion: ";
    h.display();


    // -------- HEAP SORT --------
    cout << "\nHeap Sort:\n";
    int arr[] = {4, 10, 3, 5, 1};
    int n = 5;

    HeapSort::sort(arr, n);

    for (int i = 0; i < n; i++)
        cout << arr[i] << " ";
    cout << endl;


    // -------- PRIORITY QUEUE --------
    cout << "\nPriority Queue:\n";
    PriorityQueue pq;

    pq.push(15);
    pq.push(40);
    pq.push(25);

    cout << "Top: " << pq.top() << endl;

    pq.pop();
    cout << "After pop, Top: " << pq.top() << endl;

    return 0;
}