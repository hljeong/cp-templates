// lowest common ancestor & distance in tree using binary lifting technique

// time complexity: O(n log n) precomputation, O(log n) per query
// bin_lift(g, rt) runs the precomputation on graph g rooted at rt
// dist(a, b) returns the distance between nodes a and b
// lca(a, b) returns the lowest common ancestor of nodes a and b

// not tested

static int dep[], anc[][];

static int dist(int a, int b) {
    int lca = lca(a, b);
    return (dep[a] - dep[lca]) + (dep[b] - dep[lca]);
}

static int lca(int a, int b) {
    if (dep[a] < dep[b]) {
        int __swap = a;
        a = b;
        b = __swap;
    }
    for (int i = anc[a].length - 1; i >= 0; --i) if (dep[a] - (1 << i) >= dep[b]) a = anc[a][i];
    if (a == b) return a;
    else {
        for (int i = anc[a].length - 1; i >= 0; --i) {
            if (anc[a][i] != anc[b][i]) {
                a = anc[a][i];
                b = anc[b][i];
            }
        }
        return anc[a][0];
    }
}

static void bin_lift(List<? extends Collection<Integer> g, int rt) {
    int n = g.size(), buf = n, lg = 1;
    while (buf != 0) {
        ++lg;
        buf >>= 1;
    }
    dep = new int[n];
    anc = new int[n][lg];
    for (int[] row : anc) fill(row, -1);
    bin_lift_dfs(g, rt, -1);
}

static void bin_lift_dfs(List<? extends Collection<Integer>> g, int i, int p) {
    if (p == -1) dep[i] = 0;
    else dep[i] = dep[p] + 1;
    anc[i][0] = p;
    for (int j = 1; j < anc[i].length && anc[i][j - 1] >= 0; ++j) anc[i][j] = anc[anc[i][j - 1]][j - 1];
    for (int n : g.get(i)) if (n != p) bin_lift_dfs(g, n, i);
}
