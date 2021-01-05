// dijkstra's algorithm for shortest path

// time complexity: O(e log v)
// dijkstra(g, src<, inf>) returns {dist, par}, where dist[i] = distance from node src to node i and par[i] = predecessor of node i in shortest path from node src to node i

// not tested

static int[][] dijkstra(List<? extends Collection<int[]>> g, int src) {
    return dijkstra(g, src, 1000000007);
}

static int[][] dijkstra(List<? extends Collection<int[]>> g, int src, int inf) {
    int n = g.size(), dist[] = new int[n], par[] = new int[n];
    fill(dist, inf);
    fill(par, -1);
    dist[src] = 0;
    PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
    q.offer(new int[] {0, src});
    while (!q.isEmpty()) {
        int e[] = q.poll(), i = e[1], d = e[0];
        if (d > dist[i]) continue;
        for (int ed[] : g.get(i)) {
            int j = ed[0], w = ed[1], nd = d + w;
            if (nd < dist[j]) {
                dist[j] = nd;
                par[j] = i;
                q.offer(new int[] {nd, j});
            }
        }
    }
    return new int[][] {dist, par};
}
