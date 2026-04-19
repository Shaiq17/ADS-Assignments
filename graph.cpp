#include <bits/stdc++.h>
using namespace std;

#define V 4  // number of vertices

void warshall(int graph[V][V]) {
    int reach[V][V];

    // Initialize reach matrix
    for (int i = 0; i < V; i++)
        for (int j = 0; j < V; j++)
            reach[i][j] = graph[i][j];

    // Warshall's Algorithm
    for (int k = 0; k < V; k++) {
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                reach[i][j] = reach[i][j] || (reach[i][k] && reach[k][j]);
            }
        }
    }

    // Print transitive closure
    cout << "Transitive Closure Matrix:\n";
    for (int i = 0; i < V; i++) {
        for (int j = 0; j < V; j++)
            cout << reach[i][j] << " ";
        cout << endl;
    }
}

#include <queue>

void topologicalSort(int graph[V][V]) {
    int indegree[V] = {0};

    // Calculate indegree
    for (int i = 0; i < V; i++)
        for (int j = 0; j < V; j++)
            if (graph[i][j])
                indegree[j]++;

    queue<int> q;

    // Push nodes with indegree 0
    for (int i = 0; i < V; i++)
        if (indegree[i] == 0)
            q.push(i);

    int count = 0;

    cout << "Topological Order: ";

    while (!q.empty()) {
        int u = q.front();
        q.pop();
        cout << u << " ";
        count++;

        for (int v = 0; v < V; v++) {
            if (graph[u][v]) {
                indegree[v]--;
                if (indegree[v] == 0)
                    q.push(v);
            }
        }
    }

    // Check cycle
    if (count != V)
        cout << "\nGraph has a cycle. No Topological Sort possible.";
}

int main() {
    int graph[V][V] = {
        {0, 1, 0, 0},
        {0, 0, 1, 0},
        {0, 0, 0, 1},
        {0, 0, 0, 0}
    };

    warshall(graph);
    cout << endl;
    topologicalSort(graph);

    return 0;
}