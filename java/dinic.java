// dinic's algorithm for maximum flow

// O(v^2 e)
// dinic(g, c, s, t<, inf>) returns the maximum flow through the flow network consiting of graph g, capacity function c, source s, and sink t

// note that the contents of c may be modified

static int dinic(List<? extends Collection<Integer>> g, int[][] c, int s, int t) {
    return dinic(g, c, s, t, 1000000007);
}

static int dinic(List<? extends Collection<Integer>> g, int[][] c, int s, int t, int inf) {
    int f = 0, d[] = new int[g.size()];
    while (dinic_bfs(g, c, s, t, d)) f += dinic_dfs(g, c, t, d, s, inf);
    return f;
}

static boolean dinic_bfs(List<? extends Collection<Integer>> g, int[][] c, int s, int t, int[] d) {
    fill(d, -1);
    d[s] = 0;
    Queue<Integer> q = new ArrayDeque<>();
    q.offer(s);
    while (!q.isEmpty()) {
        int u = q.poll();
        for (int v : g.get(u)) if (d[v] == -1 && c[u][v] != 0) {
            d[v] = d[u] + 1;
            q.offer(v);
        }
    }
    return d[t] != -1;
}

static int dinic_dfs(List<? extends Collection<Integer>> g, int[][] c, int t, int[] d, int u, int f) {
    if (u == t) return f;
    int a = f, inc;
    for (int v : g.get(u)) {
        if (a == 0) break;
        if (d[v] == d[u] + 1 && c[u][v] != 0) {
            a -= (inc = dinic_dfs(g, c, t, d, v, min(c[u][v], a)));
            c[u][v] -= inc;
            c[v][u] += inc;
        }
    }
    return f - a;
}
