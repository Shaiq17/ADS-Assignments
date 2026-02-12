#include<bits/stdc++.h>
using namespace std;

bool isEmpty(int arr[], int n) 
{
    if(n == 0) {
        return true;
    }
    else {
        return false;
    }
}

void display(int arr[][10], int m, int n) {
    cout << "Matrix: " << endl;
    for(int i=0; i<m; i++) {
        for(int j=0; j<n; j++) {
            cout << arr[i][j] << " ";
        }cout << endl;
    }
}

void input(int arr[][10], int row, int col) {
    cout << "Enter the elements of the matrix: " << endl;

    for(int i=0; i<row; i++) {
        for(int j=0; j<col; j++) {
            cin >> arr[i][j];
        }
    }
}

bool isDiagonal(int arr[][10], int row, int col) {
    if(row != col) {
        cout << "Matrix should be a square matrix" << endl;
        return false;
    }

    for(int i=0; i<row; i++) {
        for(int j=0; j<col; j++) {
            if(i != j && arr[i][j] != 0) {
                return false;
            }
        }
    }
    return true;
}

bool isTriDiagonal(int arr[][10], int row, int col) {
    if(row != col) {
        cout << "Matrix should be a square matrix" << endl;
        return false;
    }

    for(int i=0; i<row; i++) {
        for(int j=0; j<col; j++) {
            if(abs(i-j) > 1 && arr[i][j] != 0) {
                return false;
            }
        }
    }
    return true;
}

int main()
{
    int arr[10][10];
    int row, col;
    cout << "enter the no. of rows: ";
    cin >> row;
    cout << "Enter the no. of cols: " << endl;
    cin >> col;

    input(arr, row, col);
    display(arr, row, col);

    if(isDiagonal(arr, row , col)) {
        cout << "This is a diagonal Matrix!" << endl;
    }
    else {
        cout << "This is not a diagonal matrix!" << endl;
    }

    if(isTriDiagonal(arr, row , col)) {
        cout << "This is a tridiagonal Matrix!" << endl;
    }
    else {
        cout << "This is not a tridiagonal matrix!" << endl;
    }


    return 0;
}