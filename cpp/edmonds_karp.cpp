// edmonds-karp algorithm for maximum flow

// time complexity: O(ve^2)
// ek(g, c, s, t<, inf>) returns the maximum flow through the flow network consisting of graph g, capacity function c, source s, and sink t

// note that the contents of c may be modified

int ek_bfs(const vector<vector<int>> &g, const vector<vector<int>> &c, int s, int t, vector<int> &p) {
    fill(p.begin(), p.end(), -1);
    p[s] = -2;
    queue<pair<int, int>> q;
    q.push({s, inf});
    while (!q.empty()) {
        int u = q.front().first, f = q.front().second;
        q.pop();
        for (int v : g[u]) if (p[v] == -1 && c[u][v]) {
            p[v] = u;
            if (v == t) return min(f, c[u][v]);
            q.push({v, min(f, c[u][v])});
        }
    }
    return 0;
}

int ek(const vector<vector<int>> &g, vector<vector<int>> &c, int s, int t, int inf = 1e9 + 7) {
    int f = 0, inc;
    vector<int> p(g.size());
    while (inc = ek_bfs(g, c, s, t, p, inf)) {
        f += inc;
        for (int cur = t, prev = p[cur]; cur != s; prev = p[cur = prev]) {
            c[prev][cur] -= inc;
            c[cur][prev] += inc;
        }
    }
    return f;
}
