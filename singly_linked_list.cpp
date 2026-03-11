#include <bits/stdc++.h>
using namespace std;

class node
{
public:
    int data;
    node *next;

    // constructor
    node(int val)
    {
        this->data = val;
        this->next = NULL;
    }

    ~node()
    {
        int value = this->data;
        // memory free
        if (this->next != NULL)
        {
            delete next;
            this->next = NULL;
        }

        cout << "memory is free for node with data " << value << endl;
    }
};

void insertAtHead(node *&head, int d)
{
    // new node create
    node *temp = new node(d);
    temp->next = head;
    head = temp;
}

void insertAtTail(node *&tail, int d)
{
    node *temp = new node(d);
    tail->next = temp;
    tail = tail->next;
}

void print(node *&head)
{
    node *temp = head;
    while (temp != NULL)
    {
        cout << temp->data << "->";
        temp = temp->next;
    }
    cout << "NULL" << endl;
    // cout << endl;
}

void inesrtAtPosition(node *&tail, node *&head, int position, int d)
{
    // insert at start
    if (position == 1)
    {
        insertAtHead(head, d);
        return;
    }
    node *temp = head;
    int cnt = 1;

    while (cnt < position - 1)
    {
        temp = temp->next;
        cnt++;
    }

    // insert at last position
    if (temp->next == NULL)
    {
        insertAtTail(tail, d);
        return;
    }

    node *newNode = new node(d);

    newNode->next = temp->next;
    temp->next = newNode;
}

void deleteNode(int pos, node *&head)
{
    // deleting at start
    if (pos == 1)
    {
        node *temp = head;
        head = head->next;
        temp->next = NULL;
        delete temp;
    }
    else
    {
        // deleting any middle node or last node
        node *curr = head;
        node *prev = NULL;

        int cnt = 1;
        while (cnt < pos)
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

bool isCircular(node *head)
{
    if (head == NULL)
    {
        return true;
    }

    node *temp = head->next;

    while (temp != NULL && temp != head)
    {
        temp = temp->next;
    }
    if (temp == head)
    {
        return true;
    }
    else
    {
        return false;
    }
}

bool detectLoop(node *head)
{
    if (head == NULL)
    {
        return false;
    }

    map<node *, bool> visited;
    node *temp = head;

    while (temp != NULL)
    {
        // cycle is present
        if (visited[temp] == true)
        {
            cout << "present on element " << temp->data << endl;
            return true;
        }

        visited[temp] = true;
        temp = temp->next;
    }

    return false;
}

node *floydDetectLoop(node *head)
{
    if (head == NULL)
    {
        return NULL;
    }

    node *slow = head;
    node *fast = head;

    while (slow != NULL && fast != NULL)
    {
        fast = fast->next;
        if (fast != NULL)
        {
            fast = fast->next;
        }
        slow = slow->next;

        if (slow == fast)
        {
            cout << "present at " << slow->data << endl;
            return slow;
        }
    }
    return NULL;
}

node *getStartingNode(node *head)
{
    if (head == NULL)
    {
        return NULL;
    }

    node *intersection = floydDetectLoop(head);

    if(intersection == NULL) {
        return NULL;
    }

    node *slow = head;

    while (slow != intersection)
    {
        slow = slow->next;
        intersection = intersection->next;
    }

    return slow;
}

void removeLoop(node *head)
{
    if (head == NULL)
    {
        return;
    }

    node *startOfLoop = getStartingNode(head);
    if(startOfLoop == NULL) {
        cout << head->data << endl;
    }
    node *temp = startOfLoop;

    while (temp->next != startOfLoop)
    {
        temp = temp->next;
    }
    temp->next = NULL;
}

int main()
{
    node *node1 = new node(15);

    // cout << node1 -> data << endl;
    // cout << node1 -> next << endl;

    // head pointed to node1
    node *head = node1;
    node *tail = node1;
    insertAtHead(head, 12);
    insertAtHead(head, 10);

    insertAtTail(tail, 22);

    // inesrtAtPosition(tail, head, 6, 19);

    print(head);
    tail->next = head->next;

    if (floydDetectLoop(head) != NULL)
    {
        cout << "cycle is present" << endl;
    }
    else
    {
        cout << "No cycle is present" << endl;
    }

    cout << "loop starts at " << getStartingNode(head)->data << endl;

    removeLoop(head);
    print(head);

    /* if(isCircular(head)) {
        cout << "Linked list is circular" << endl;
    }
    else {
        cout << "Linked list is not circular" << endl;
    }
    */

    // deleteNode(6, head);
    // print(head);

    return 0;
}