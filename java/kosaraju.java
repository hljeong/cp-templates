// kosaraju's algorithm for strongly connected components

// time complexity: O(v + e)
// kosaraju(g) returns list of lists of vertices of each strongly connected component in graph g

static List<List<Integer>> kosaraju(List<? extends Collection<Integer>> g) {
    List<Integer> ordered = new ArrayList<>();
    int n = g.size();
    boolean[] vis = new boolean[n];
    for (int i = 0; i < n; ++i) if (!vis[i]) kosaraju_dfs(g, i, vis, ordered);
    List<List<Integer>> transpose = new ArrayList<>();
    for (int i = 0; i < n; ++i) transpose.add(new ArrayList<>());
    for (int i = 0; i < n; ++i) for (int j : g.get(i)) transpose.get(j).add(i);
    for (int i = 0, end = n / 2; i < end; ++i) {
        int __swap = ordered.get(i);
        ordered.set(i, ordered.get(n - i - 1));
        ordered.set(n - i - 1, __swap);
    }
    List<List<Integer>> scc = new ArrayList<>();
    fill(vis, false);
    for (int i : ordered) {
        if (!vis[i]) {
            List<Integer> comp = new ArrayList<>();
            kosaraju_dfs(transpose, i, vis, comp);
            scc.add(comp);
        }
    }
    return scc;
}

static void kosaraju_dfs(List<? extends Collection<Integer>> g, int i, boolean vis[], List<Integer> ordered) {
    vis[i] = true;
    for (int j : g.get(i)) {
        if (!vis[j]) {
            kosaraju_dfs(g, j, vis, ordered);
        }
    }
    ordered.add(i);
}
