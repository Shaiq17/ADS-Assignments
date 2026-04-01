#include<bits/stdc++.h>
using namespace std;

struct AVLNode {
    int data;
    AVLNode *left, *right;
    int height;

    AVLNode(int val) {
        data = val;
        left = right = NULL;
        height = 1;
    }
};

int height(AVLNode* node) {
    if(node == NULL) return 0;
    return node->height;
}

int getBalance(AVLNode* node) {
    if(node == NULL) return 0;
    return height(node->left) - height(node->right);
}

// Right Rotation
AVLNode* rightRotate(AVLNode* y) {
    AVLNode* x = y->left;
    AVLNode* T2 = x->right;

    x->right = y;
    y->left = T2;

    y->height = max(height(y->left), height(y->right)) + 1;
    x->height = max(height(x->left), height(x->right)) + 1;

    return x;
}

// Left Rotation
AVLNode* leftRotate(AVLNode* x) {
    AVLNode* y = x->right;
    AVLNode* T2 = y->left;

    y->left = x;
    x->right = T2;

    x->height = max(height(x->left), height(x->right)) + 1;
    y->height = max(height(y->left), height(y->right)) + 1;

    return y;
}

AVLNode* insertAVL(AVLNode* node, int key) {
    if(node == NULL) return new AVLNode(key);

    if(key < node->data)
        node->left = insertAVL(node->left, key);
    else
        node->right = insertAVL(node->right, key);

    node->height = 1 + max(height(node->left), height(node->right));

    int balance = getBalance(node);

    // LL
    if(balance > 1 && key < node->left->data)
        return rightRotate(node);

    // RR
    if(balance < -1 && key > node->right->data)
        return leftRotate(node);

    // LR
    if(balance > 1 && key > node->left->data) {
        node->left = leftRotate(node->left);
        return rightRotate(node);
    }

    // RL
    if(balance < -1 && key < node->right->data) {
        node->right = rightRotate(node->right);
        return leftRotate(node);
    }

    return node;
}

int main() {
    AVLNode* root = NULL;

    vector<int> values = {10, 20, 30, 40, 50, 25};

    for(int val : values) {
        root = insertAVL(root, val);
    }

    cout << "AVL Inorder: ";
    function<void(AVLNode*)> inorderAVL = [&](AVLNode* root) {
        if(!root) return;
        inorderAVL(root->left);
        cout << root->data << " ";
        inorderAVL(root->right);
    };

    inorderAVL(root);
    cout << endl;

    return 0;
}