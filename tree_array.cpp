#include<bits/stdc++.h>
using namespace std;

class ArrayTree {
public:
    int tree[100];  

    ArrayTree() {
        for(int i = 0; i < 100; i++)
            tree[i] = -1;   
    }

    void setRoot(int value) {
        tree[0] = value;
    }

    void setLeft(int rootIndex, int value) {
        int index = 2 * rootIndex + 1;
        if(tree[rootIndex] == -1) {
            cout << "Parent does not exist\n";
        } else {
            tree[index] = value;
        }
    }

    void setRight(int rootIndex, int value) {
        int index = 2 * rootIndex + 2;
        if(tree[rootIndex] == -1) {
            cout << "Parent does not exist\n";
        } else {
            tree[index] = value;
        }
    }

    void display() {
        for(int i = 0; i < 15; i++) {
            if(tree[i] != -1)
                cout << "Index " << i << ": " << tree[i] << endl;
        }
    }

    void inorder(int index) {
        if(index >= 100 || tree[index] == -1)
            return;

        inorder(2 * index + 1);   // left
        cout << tree[index] << " "; // root
        inorder(2 * index + 2);   // right
    }
};

int main() {
    ArrayTree t;

    t.setRoot(1);
    t.setLeft(0, 2);
    t.setRight(0, 3);
    t.setLeft(2, 4);
    t.setRight(1, 5);

    t.display();
    cout << "inorder: " ;
    t.inorder(0);

    return 0;
}
