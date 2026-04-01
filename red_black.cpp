#include<bits/stdc++.h>
using namespace std;

enum Color {RED, BLACK};

struct RBNode {
    int data;
    Color color;
    RBNode *left, *right, *parent;

    RBNode(int val) {
        data = val;
        color = RED;
        left = right = parent = NULL;
    }
};

RBNode* root = NULL;

// Left Rotate
void rotateLeft(RBNode*& root, RBNode*& pt) {
    RBNode* pt_right = pt->right;

    pt->right = pt_right->left;
    if(pt->right != NULL)
        pt->right->parent = pt;

    pt_right->parent = pt->parent;

    if(pt->parent == NULL)
        root = pt_right;
    else if(pt == pt->parent->left)
        pt->parent->left = pt_right;
    else
        pt->parent->right = pt_right;

    pt_right->left = pt;
    pt->parent = pt_right;
}

// Right Rotate
void rotateRight(RBNode*& root, RBNode*& pt) {
    RBNode* pt_left = pt->left;

    pt->left = pt_left->right;
    if(pt->left != NULL)
        pt->left->parent = pt;

    pt_left->parent = pt->parent;

    if(pt->parent == NULL)
        root = pt_left;
    else if(pt == pt->parent->left)
        pt->parent->left = pt_left;
    else
        pt->parent->right = pt_left;

    pt_left->right = pt;
    pt->parent = pt_left;
}

void BSTInsert(RBNode*& root, RBNode*& pt) {
    if(root == NULL) {
        root = pt;
        return;
    }

    if(pt->data < root->data) {
        if(root->left == NULL) {
            root->left = pt;
            pt->parent = root;
        } else {
            BSTInsert(root->left, pt);
        }
    } else {
        if(root->right == NULL) {
            root->right = pt;
            pt->parent = root;
        } else {
            BSTInsert(root->right, pt);
        }
    }
}

int main() {
    vector<int> values = {10, 20, 30, 40, 50, 25};

    for(int val : values) {
        RBNode* pt = new RBNode(val);
        BSTInsert(root, pt);
        // Normally you should call fixInsert(root, pt);
    }

    cout << "Red-Black Tree (Inorder): ";

    function<void(RBNode*)> inorderRB = [&](RBNode* root) {
        if(!root) return;
        inorderRB(root->left);
        cout << root->data << " ";
        inorderRB(root->right);
    };

    inorderRB(root);
    cout << endl;

    return 0;
}