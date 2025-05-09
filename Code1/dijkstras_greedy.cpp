#include <iostream>
#include <vector>
#include <queue>
#include <climits>

using namespace std;

const int INF = INT_MAX;

int main() {
    int vertices, edges;    
    cout << "Enter the number of vertices and edges: ";
    cin >> vertices >> edges;

    vector<pair<int, int>> adj[vertices];
    cout << "Enter the edges (source, destination, weight):" << endl;
    for (int i = 0; i < edges; ++i) {
        int source, destination, weight;
        cin >> source >> destination >> weight;
        adj[source].push_back({destination, weight});
        adj[destination].push_back({source, weight}); // For undirected graph
    }

    int start_node;
    cout << "Enter the starting node: ";
    cin >> start_node;

    vector<int> distances(vertices, INF);
    distances[start_node] = 0;

    priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
    pq.push({0, start_node});

    while (!pq.empty()) {
        int current_distance = pq.top().first;
        int current_node = pq.top().second;
        pq.pop();

        if (current_distance > distances[current_node]) {
            continue;
        }

        for (auto& neighbor : adj[current_node]) {
            int next_node = neighbor.first;
            int weight = neighbor.second;
            int new_distance = current_distance + weight;

            if (new_distance < distances[next_node]) {
                distances[next_node] = new_distance;
                pq.push({new_distance, next_node});
            }
        }
    }
    cout << "Shortest distances from node " << start_node << " are:" << endl;
    for (int i = 0; i < vertices; ++i) {
        cout << "To node " << i << ": ";
        if (distances[i] == INF) {
            cout << "Not reachable" << endl;
        } else {
            cout << distances[i] << endl;
        }
    }

    return 0;
}

