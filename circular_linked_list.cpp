#include <bits/stdc++.h>
using namespace std;

class node
{
public:
    int data;
    node *next;

    node(int d)
    {
        this->data = d;
        next = NULL;
    }

    ~node()
    {
        int value = this->data;
        if (this->next != NULL)
        {
            delete next;
            next = NULL;
        }
        cout << "memoroy is free for node with data: " << value << endl;
    }
};

void insertNode(node *&tail, int element, int d)
{
    // assuming the element is present in the list
    if (tail == NULL)
    { // empty list
        node *newNode = new node(d);
        tail = newNode;
        newNode->next = newNode;
    }

    else
    {
        node *curr = tail;

        while (curr->data != element)
        {
            curr = curr->next;
        }
        // element found -> curr is representing element vala node
        node *temp = new node(d);
        temp->next = curr->next;
        curr->next = temp;
    }
}

void print(node *tail)
{
    node *temp = tail;
    // empty list
    if (tail == NULL)
    {
        cout << "list is empty" << endl;
        return;
    }

    do
    {
        cout << tail->data << " ";
        tail = tail->next;
    } while (tail != temp);
    cout << endl;
}

void deleteNode(node *&tail, int val)
{
    // empty list
    if (tail == NULL)
    {
        cout << "list is empty" << endl;
        return;
    }
    else
    {
        // non empty

        // assuming that 'val' is present in the linked list
        node *prev = tail;
        node *curr = prev->next;

        while (curr->data != val)
        {
            prev = curr;
            curr = curr->next;
        }
        prev->next = curr->next;

        // single node LL
        if (curr == prev)
        {
            tail = NULL;
        }
        //>=2 nodes LL
        else if (tail == curr)
        {
            tail = prev;
        }
        curr->next = NULL;
        delete curr;
    }
}

bool isCircular(node* head) {
    if(head == NULL) {
        return true;
    }

    node* temp = head->next;

    while(temp != NULL && temp != head) {
        temp = temp->next;
    }
    if(temp == head) {
        return true;
    }
    else {
        return false;
    }
}

bool detectLoop(node* head) {
    if(head == NULL) {
        return false;
    }

    map<node*, bool> visited;
    node* temp = head;

    while(temp != NULL) {
        //cycle is present
        if(visited[temp] == true) {
            return true;
        }

        visited[temp] = true;
        temp = temp->next;
    }

    return false;
}

int main()
{
    node *tail = NULL;

    insertNode(tail, 5, 3);
    print(tail);

    insertNode(tail, 3, 5);
    print(tail);

    insertNode(tail, 5, 7);
    print(tail);

    insertNode(tail, 5, 9);
    print(tail);

    if(isCircular(tail)) {
        cout << "Linked list is circular" << endl;
    }
    else {
        cout << "Linked list is not circular" << endl;
    }

   /* deleteNode(tail, 3);
    print(tail);

    deleteNode(tail, 5);
    print(tail);

    deleteNode(tail, 9);
    print(tail);

    deleteNode(tail, 7);
    print(tail); */

    return 0;
}