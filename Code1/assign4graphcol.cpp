#include <iostream>
#include <vector>
using namespace std;

const int MAXV = 100; // Maximum number of vertices
vector<int> graph[MAXV];
int color[MAXV];

// Check if it's safe to color currVertex with color c
bool isSafe(int currVertex, int c) {
    for (int neighbor : graph[currVertex]) {
        if (color[neighbor] == c)
            return false;
    }
    return true;
}

// Recursive backtracking function to color the graph
bool graphColoring(int currVertex, int V, int m) {
    if (currVertex == V) return true; // All vertices colored

    for (int c = 1; c <= m; c++) {
        if (isSafe(currVertex, c)) {
            color[currVertex] = c; // Assign color
            if (graphColoring(currVertex + 1, V, m))
                return true;
            color[currVertex] = 0; // Backtrack
        }
    }
    return false; // No color could be assigned
}

int main() {
    int V, E, m;
    cout << "Enter number of vertices, edges, and colors: ";
    cin >> V >> E >> m;

    cout << "Enter the edges (u v):\n";
    for (int i = 0; i < E; i++) {
        int u, vtx;
        cin >> u >> vtx;
        graph[u].push_back(vtx);
        graph[vtx].push_back(u); // undirected graph
    }

    for (int i = 0; i < V; i++) color[i] = 0;

    if (graphColoring(0, V, m)) {
        cout << "\nColor assignment:\n";
        for (int i = 0; i < V; i++) {
            cout << "Vertex " << i << " ---> Color " << color[i] << "\n";
        }
    } else {
        cout << "\nNo solution exists with " << m << " colors.\n";
    }

    return 0;
}
