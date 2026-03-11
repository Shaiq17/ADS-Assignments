#include <bits/stdc++.h>
using namespace std;

class node
{
public:
    int data;
    node *prev;
    node *next;

    node(int d)
    {
        this->data = d;
        this->prev = NULL;
        this->next = NULL;
    }
    ~node()
    {
        int val = this->data;
        if (next != NULL)
        {
            delete next;
            next = NULL;
        }
        cout << "memory free for node with data " << val << endl;
    }
};
// traversing a linked list
void print(node *&head)
{
    node *temp = head;

    while (temp != NULL)
    {
        cout << temp->data << "->";
        temp = temp->next;
    }
    cout << "NULL" << endl;
}

void insertAtHead(node *&head, int d)
{
    if (head == NULL)
    {
        node *temp = new node(d);
        head = temp;
    }
    else
    {
        node *temp = new node(d);
        temp->next = head;
        head->prev = temp;
        head = temp;
    }
}

// gives length of linked list
int getLength(node *head)
{
    int len = 0;
    node *temp = head;

    while (temp != NULL)
    {
        len++;
        temp = temp->next;
    }
    return len;
}

void insertAtTail(node *&tail, int d)
{
    if (tail == NULL)
    {
        node *temp = new node(d);
        tail = temp;
    }
    else
    {
        node *temp = new node(d);
        tail->next = temp;
        temp->prev = tail;
        tail = temp;
    }
}

void insertAtPosition(node *&tail, node *&head, int pos, int d)
{
    // insert at start
    if (pos == 1)
    {
        insertAtHead(head, d);
        return;
    }

    node *temp = head;
    int cnt = 1;

    while (cnt < pos - 1)
    {
        temp = temp->next;
        cnt++;
    }

    // insert at last pos
    if (temp->next == NULL)
    {
        insertAtTail(tail, d);
        return;
    }

    node *newNode = new node(d);
    newNode->next = temp->next;
    temp->next->prev = newNode;
    temp->next = newNode;
    newNode->prev = temp;
}

void deleteAtPos(int pos, node *&head, node *&tail)
{
    if (head == NULL)
    {
        return;
    }
    // start node
    if (pos == 1)
    {
        node *temp = head;
        if (head->next == NULL)
        {
            head = NULL;
            tail = NULL;
        }
        else
        {
            temp->next->prev = NULL;
            head = temp->next;
        }
        temp->next = NULL;
        delete temp;
    }
    else
    {
        // middle or last node
        node *prev = NULL;
        node *curr = head;

        int cnt = 1;
        while (cnt < pos && curr != NULL)
        {
            prev = curr;
            curr = curr->next;
            cnt++;
        }

        if (curr == NULL)
        {
            return;
        }

        if (curr->next != NULL)
        {
            curr->next->prev = prev;
        }
        if (curr->next == NULL)
        {
            prev->next = NULL;
            tail = prev;
        }

        else
        {
            curr->prev = NULL;
            prev->next = curr->next;
            curr->next = NULL;
            delete curr;
        }
    }
}

int main()
{

    node *node1 = new node(10);
    node *head = node1;
    node *tail = node1;

    print(head);

    insertAtHead(head, 11);
    cout << getLength(head) << endl;

    insertAtHead(head, 13);
    insertAtTail(tail, 15);
    print(head);

    insertAtPosition(tail, head, 2, 100);
    insertAtPosition(tail, head, 1, 101);
    insertAtPosition(tail, head, 6, 102);
    insertAtPosition(tail, head, 8, 110);
    print(head);

    deleteAtPos(1, head, tail);
    cout << "tail: " << tail->data << endl;
    print(head);
}