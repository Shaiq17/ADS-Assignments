#include <bits/stdc++.h>
using namespace std;

class ArrayDS
{
public:
    int arr[100];
    int n;

    void insert()
    {
        cout << "enter the no. of elements: ";
        cin >> n;

        int evenIndex = 0;
        int oddIndex = n - 1;
        int num;

        cout << "Enter elements: ";
        for (int i = 0; i < n; i++)
        {
            cin >> num;

            if (num % 2 == 0)
            {
                arr[evenIndex] = num;
                evenIndex++;
            }
            else
            {
                arr[oddIndex] = num;
                oddIndex--;
            }
        }
    }

    void display()
    {
        for (int i = 0; i < n; i++)
        {
            cout << arr[i] << " ";
        }
    }
};

int main()
{

    ArrayDS obj;
    obj.insert();
    obj.display();

    return 0;
}