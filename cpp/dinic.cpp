// dinic's algorithm for maximum flow

// O(v^2 e)
// dinic(g, c, s, t<, inf>) returns the maximum flow through the flow network consiting of graph g, capacity function c, source s, and sink t

// note that the contents of c may be modified

bool dinic_bfs(const vector<vector<int>> &g, const vector<vector<int>> &c, int s, int t, vector<int> &d) {
    fill(d.begin(), d.end(), -1);
    d[s] = 0;
    queue<int> q;
    q.push(s);
    while (!q.empty()) {
        int u = q.front();
        q.pop();
        for (int v : g[u]) if (d[v] == -1 && c[u][v]) {
            d[v] = d[u] + 1;
            q.push(v);
        }
    }
    return d[t] != -1;
}

int dinic_dfs(const vector<vector<int>> &g, vector<vector<int>> &c, int t, const vector<int> &d, int u, int f) {
    if (u == t) return f;
    int a = f, inc;
    for (int v : g[u]) {
        if (a == 0) break;
        if (d[v] == d[u] + 1 && c[u][v]) {
            a -= (inc = dinic_dfs(g, c, t, d, v, min(c[u][v], a)));
            c[u][v] -= inc;
            c[v][u] += inc;
        }
    }
    return f - a;
}

int dinic(const vector<vector<int>> &g, vector<vector<int>> &c, int s, int t, int inf = 1e9 + 7) {
    int f = 0;
    vector<int> d(g.size());
    while (dinic_bfs(g, c, s, t, d)) f += dinic_dfs(g, c, t, d, s, inf);
    return f;
}
