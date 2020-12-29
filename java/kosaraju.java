// kosaraju's strongly connected component algorithm

static List<List<Integer>> scc(List<? extends Collection<Integer>> g) {
    List<Integer> ordered = new ArrayList<>();
    int n = g.size();
    boolean[] vis = new boolean[n];
    for (int i = 0; i < n; ++i) {
        if (!vis[i]) {
            dfs(g, i, vis, ordered);
        }
    }
    List<List<Integer>> transpose = new ArrayList<>();
    for (int i = 0; i < n; ++i) {
        transpose.add(new ArrayList<>());
    }
    for (int i = 0; i < n; ++i) {
        for (int j : g.get(i)) {
            transpose.get(j).add(i);
        }
    }
    for (int i = 0; i < n / 2; ++i) {
        int __swap = ordered.get(i);
        ordered.set(i, ordered.get(n - i - 1));
        ordered.set(n - i - 1, __swap);
    }
    List<List<Integer>> scc = new ArrayList<>();
    fill(vis, false);
    for (int i : ordered) {
        if (!vis[i]) {
            List<Integer> comp = new ArrayList<>();
            dfs(transpose, i, vis, comp);
            scc.add(comp);
        }
    }
    return scc;
}

static void dfs(List<? extends Collection<Integer>> g, int i, boolean vis[], List<Integer> ordered) {
    vis[i] = true;
    for (int j : g.get(i)) {
        if (!vis[j]) {
            dfs(g, j, vis, ordered);
        }
    }
    ordered.add(i);
}
