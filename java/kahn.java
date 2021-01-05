// kahn's algorithm for topological sorting

// time complexity: O(v + e)
// kahn(g<, indeg>) returns vertices in topologically sorted order, optionally provided with the indegree array indeg

// not tested

static List<Integer> kahn(List<? extends Collection<Integer>> g) {
    return topsort(g, kahn_indeg(g));
}

static List<Integer> kahn(List<? extends Collection<Integer>> g, int[] indeg) {
    int n = g.size();
    List<Integer> ans = new ArrayList<>(n);
    Queue<Integer> q = new ArrayDeque<>();
    for (int i = 0; i < n; ++i) if (indeg[i] == 0) q.offer(i);
    while (!q.isEmpty()) {
        int i = q.poll();
        ans.add(i);
        for (int j : g.get(i)) if (--indeg[j] == 0) q.offer(j);
    }
    return ans;
}

static int[] kahn_indeg(List<List<Integer>> g) {
    int n = g.size(), indeg[] = new int[n];
    for (List<Integer> adj : g) for (int i : adj) ++indeg[i];
    return indeg;
}
