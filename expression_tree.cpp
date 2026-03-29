#include<bits/stdc++.h>
using namespace std;

class ExpNode {
public:
    char data;
    ExpNode* left;
    ExpNode* right;

    ExpNode(char val) {
        data = val;
        left = right = NULL;
    }
};

// Function to check operator
bool isOperator(char c) {
    return (c == '+' || c == '-' || c == '*' || c == '/');
}

// Build Expression Tree from postfix
ExpNode* buildTree(string postfix) {
    stack<ExpNode*> st;

    for(char ch : postfix) {
        ExpNode* node = new ExpNode(ch);

        if(!isOperator(ch)) {
            st.push(node);
        } else {
            node->right = st.top(); st.pop();
            node->left = st.top(); st.pop();
            st.push(node);
        }
    }

    return st.top();
}

// Inorder traversal (to print expression)
void inorder(ExpNode* root) {
    if(root == NULL) return;

    if(isOperator(root->data)) cout << "(";
    
    inorder(root->left);
    cout << root->data;
    inorder(root->right);

    if(isOperator(root->data)) cout << ")";
}

int main() {
    string postfix = "ab+cd-*";

    ExpNode* root = buildTree(postfix);

    cout << "Infix Expression: ";
    inorder(root);
}