 #include <iostream>
using namespace std;

class TriangularMatrix {
    int n;
    int lower[100];
    int upper[100];

public:
    void input() {
        cout << "Enter order of matrix: ";
        cin >> n;

        int k = 0;
        cout << "\nEnter lower triangular matrix:\n";
        for (int i = 0; i < n; i++)
            for (int j = 0; j <= i; j++)
                cin >> lower[k++];

        k = 0;
        cout << "\nEnter upper triangular matrix:\n";
        for (int i = 0; i < n; i++)
            for (int j = i; j < n; j++)
                cin >> upper[k++];
    }

    int getLower(int i, int j) {
        if (i < j) return 0;
        return lower[(i * (i + 1)) / 2 + j];
    }

    int getUpper(int i, int j) {
        if (i > j) return 0;
        return upper[(i * n) - (i * (i - 1)) / 2 + (j - i)];
    }

    void product() {
        int result[10][10] = {0};

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {

                int sum = 0;
                for (int k = 0; k <= min(i, j); k++) {
                    sum += getLower(i, k) * getUpper(k, j);
                }

                result[i][j] = sum;
            }
        }

        cout << "\nProduct Matrix:\n";
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                cout << result[i][j] << " ";
            cout << endl;
        }
    }
};

int main() {
    TriangularMatrix m;
    m.input();
    m.product();
    return 0;
}