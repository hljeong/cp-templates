// fenwick tree (binary indexed tree)

// time complexity: O(log n) per query
// upd(i, x) adds x to a[i]
// qry(i) returns sum(a[:i])

// not tested

static int fw[MAXN + 1];

static void upd(int i, int x) {
    for (++i; i <= n; i += i & -i) fw[i] += x;
}

static int qry(int i) {
    int ans = 0;
    for (; i != 0; i -= i & -i) ans += fw[i];
    return ans;
}
