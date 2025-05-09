#include <bits/stdc++.h>
using namespace std;

class Solution
{
public:
    // Function to find sum of weights of edges of the Minimum Spanning Tree.
    int spanningTree(int V, vector<vector<int>> adj[])
    {
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;

        vector<int> vis(V, 0);
        pq.push({0, 0}); // {weight, node}
        int sum = 0;

        while (!pq.empty()) {
            auto it = pq.top();
            pq.pop();
            int node = it.second;
            int wt = it.first;

            if (vis[node]) continue;

            vis[node] = 1;
            sum += wt;

            for (auto &neighbor : adj[node]) {
                int adjNode = neighbor[0];
                int edgeWeight = neighbor[1];
                if (!vis[adjNode]) {
                    pq.push({edgeWeight, adjNode});
                }
            }
        }

        return sum;
    }
};

int main() {
    int V, E;
    cout << "Enter number of vertices and edges: ";
    cin >> V >> E;

    vector<vector<int>> adj[V];
    cout << "Enter edges in format (u v weight):\n";

    for (int i = 0; i < E; ++i) {
        int u, v, w;
        cin >> u >> v >> w;
        adj[u].push_back({v, w});
        adj[v].push_back({u, w}); // because the graph is undirected
    }

    Solution obj;
    int sum = obj.spanningTree(V, adj);
    cout << "The sum of all the edge weights in MST: " << sum << endl;

    return 0;
}

// edges = {{0, 2, 1}, {0, 1, 2}, {1, 2, 1}, {2, 4, 2}, {2, 3, 2}, {3, 4, 1}};
