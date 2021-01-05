// shortest path faster algorithm

// time complexity: worst-case O(ve); O(e) on random graphs
// spfa(g, src<, inf>) returns {dist, par}, where dist[i] = distance from node src to node i and par[i] = predecessor of node i in shortest path from node src to node i

// not tested

static int[][] spfa(List<? extends Collection<int[]>> g, int src) {
    return spfa(g, src, 1000000007);
}

static int[][] spfa(List<? extends Collection<int[]>> g, int src, int inf) {
    int n = g.size(), dist[] = new int[n], par[] = new int[n];
    fill(dist, inf);
    fill(par, -1);
    dist[src] = 0;
    Queue<int[]> q = new ArrayDeque<>();
    Set<Integer> s = new HashSet<>();
    q.offer(new int[] {0, src});
    s.add(src);
    while (!q.isEmpty()) {
        int e[] = q.poll(), i = e[1], d = e[0];
        s.remove(i);
        for (int ed[] : g.get(i)) {
            int j = ed[0], w = ed[1], nd = d + w;
            if (nd < dist[j]) {
                dist[j] = nd;
                par[j] = i;
                if (!s.contains(j)) {
                    q.offer(new int[] {nd, j});
                    s.add(j);
                }
            }
        }
    }
    return new int[][] {dist, par};
}
