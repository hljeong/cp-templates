// edmonds-karp algorithm for maximum flow

// time complexity: O(ve^2)
// ek(g, c, s, t) returns the maximum flow on graph g with capacity function c, source s, and sink t

int ek_bfs(const vector<vector<int>> &g, const vector<vector<int>> &c, int s, int t, vector<int> &p) {
    fill(p.begin(), p.end(), -1);
    p[s] = -2;
    queue<pair<int, int>> q;
    q.push({s, 1000000007});
    while (!q.empty()) {
        int u = q.front().first, f = q.front().second;
        q.pop();
        for (int v : g[u]) if (p[v] == -1 and c[u][v]) {
            p[v] = u;
            if (v == t) return min(f, c[u][v]);
            q.push({v, min(f, c[u][v])});
        }
    }
    return 0;
}

// note that the contents of capacity may be changed
int ek(const vector<vector<int>> &g, vector<vector<int>> &c, int s, int t) {
    int f = 0, inc;
    vector<int> p(g.size());
    while (inc = ek_bfs(g, c, s, t, p)) {
        f += inc;
        for (int cur = t, prev = p[cur]; cur != s; prev = p[cur = prev]) {
            c[prev][cur] -= inc;
            c[cur][prev] += inc;
        }
    }
    return f;
}
