// edmonds-karp algorithm for maximum flow

// time complexity: O(ve^2)
// ek(g, c, s, t<, inf>) returns the maximum flow through the flow network consisting of graph g, capacity function c, source s, and sink t

// note that the contents of c may be modified

static int ek(List<? extends Collection<Integer>> g, int[][] c, int s, int t) {
    return ek(g, c, s, t, 1000000007);
}

static int ek(List<? extends Collection<Integer>> g, int[][] c, int s, int t, int inf) {
    int f = 0, p[] = new int[g.size()], inc;
    while ((inc = ek_bfs(g, c, s, t, p, inf)) != 0) {
        f += inc;
        for (int cur = t, prev = p[cur]; cur != s; prev = p[cur = prev]) {
            c[prev][cur] -= inc;
            c[cur][prev] += inc;
        }
    }
    return f;
}

static int ek_bfs(List<? extends Collection<Integer>> g, int[][] c, int s, int t, int[] p, int inf) {
    fill(p, -1);
    p[s] = -2;
    Queue<int[]> q = new ArrayDeque<>();
    q.offer(new int[] {s, inf});
    while (!q.isEmpty()) {
        int e[] = q.poll(), u = e[0], f = e[1];
        for (int v : g.get(u)) if (p[v] == -1 && c[u][v] != 0) {
            p[v] = u;
            if (v == t) return min(f, c[u][v]);
            q.offer(new int[] {v, min(f, c[u][v])});
        }
    }
    return 0;
}
