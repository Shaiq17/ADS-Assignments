#include<bits/stdc++.h>
using namespace std;

class matrix {
    int m, n;
    float a[10][10];

public: 
    matrix(int r, int c) {
        this->m = r;
        this->n = c;
    }

    void input() {
        cout << "enter elements: ";
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                cin >> a[i][j];
            }
        }
    }

    void display() {
        cout << "Matrix: " << endl;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                cout << a[i][j] << " ";
            }cout << endl;
        }
    }

    matrix add(matrix b) {
        matrix c(m,n);
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                c.a[i][j] = a[i][j] + b.a [i][j];
            }
        }
        return c;
    }

    matrix multiply(matrix b) {
        matrix c(m, b.n);
        for(int i=0; i<m; i++) {
            for(int j=0; j<b.n; j++) {
                c.a[i][j] = 0;
                for(int k=0; k<n; k++) {
                    c.a[i][j] += a[i][j] * b.a[k][j];
                }
            }
        }
        return c;
    }

    float determinant() {
        return (a[0][0] * a[1][1]) - (a[0][1] * a[1][0]);
    }

    void inverse() {
        float det = determinant();
        if(det == 0) {
            cout << "inverse not possible!" << endl;
            return;
        }

        
        cout << a[1][1]/det << " " << -a[0][1]/det << endl;
        cout << -a[1][0]/det << " " << a[0][0]/det << endl;
    }

    void setDiagonal(float x, float y) {
        a[0][0] = x;
        a[0][1] = 0;
        a[1][0] = 0;
        a[1][1] = y;
    }

};

int main()
{
    matrix A(2,2), B(2,2);

    A.input();
    A.display();

    A.inverse();
    B.input();

    A.add(B).display();

    A.multiply(B).display();


    return 0;
}