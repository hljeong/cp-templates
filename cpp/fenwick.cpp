// fenwick tree (binary indexed tree)

// time complexity: O(log n) per query
// upd(i, x) adds x to a[i]
// qry(i) returns sum(a[:i])

// not tested

int fw[MAXN + 1];

void upd(int i, int x) {
    for (++i; i <= n; i += i & -i) fw[i] += x;
}

int qry(int i) {
    int ans = 0;
    for (; i; i -= i & -i) ans += fw[i];
    return ans;
}
