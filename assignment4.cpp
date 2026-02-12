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
};

void insertAtHead(node *&head, int d)
{
    if (head == NULL)
    {
        node *n = new node(d);
        head = n;
    }
    node *temp = new node(d);
    temp->next = head;
    head = temp;
}

void insertAtTail(node *&tail, int d)
{
    if(tail == NULL) {
        node* newNode = new node(d);
        tail = newNode;
    }

    node *temp = new node(d);
    tail->next = temp;
    tail = temp;
}

void display(node* &head) 
{
    if(head == NULL)
    {
        cout << "List is empty" << endl;
        return;
    }
    node* temp = head;
    while(temp != NULL)
    {
        cout << temp->data << "->";
        temp = temp->next;
    }cout << "NULL";
    cout << endl;
}

void insertAtPosition(node* &tail, node *&head, int pos, int d)
{
    if(pos == 1) 
    {
        insertAtHead(head, d);
        return;
    }

    node* temp = head;
    int cnt = 1;

    while(cnt < pos-1)
    {
        temp = temp->next;
        cnt++;
    }

    if(temp->next == NULL)
    {
        insertAtTail(tail, d);
        return;
    }

    node* newNode = new node(d);
    newNode->next = temp->next;
    temp->next = newNode;
}

void deleteNode(node* &head, int pos)
{
    if(pos == 1)
    {
        node* temp = head;
        head = head->next;
        temp->next = NULL;
        delete temp;
    }
    else 
    {
        node* curr = head;
        node* prev = NULL;

        int cnt = 1;
        while(cnt < pos)
        {
            prev = curr;
            curr = curr->next;
            cnt++;
        }

        prev->next = curr->next;
        curr->next = NULL;
        delete curr;
    }
}

bool search(node* &head, int d) 
{
    node* temp = head;
    while(temp != NULL)
    {
        if(temp->data == d)
        {
            return true;
        }
        else {
            temp = temp->next;
        }
    }
    return false;
}

int main()
{
    node* node1 = new node(10);
    cout << node1->data << endl;

    node* head = node1;
    node* tail = node1;

    insertAtHead(head, 20);
    insertAtTail(tail, 30);
    insertAtTail(tail, 60);

    display(head);

    insertAtPosition(tail, head, 3, 80);
    insertAtPosition(tail, head, 4, 90);

    display(head);

    deleteNode(head, 4);
    display(head);

    cout << search(head, 80) << endl;

    return 0;
}