// suffix array & longest common prefix array

// time complexity: O(n)
// suffix(s) returns suffix array of string s
// lcp(s<, suf>) returns longest common prefix array of string s, optionally providing the suffix array suf of s

static int[] lcp(char[] s) {
    return lcp(s, suffix(s));
}

static int[] lcp(char[] s, int[] suf) {
    int n = s.length, rank[] = new int[n + 1], ans[] = new int[n], k = 0;
    for (int i = 0; i <= n; ++i) rank[suf[i]] = i;
    for (int i = 0; i < n; ++i) {
        if (k > 0) --k;
        int p = suf[rank[i] - 1];
        while (i + k < n && p + k < n && s[i + k] == s[p + k]) ++k;
        ans[rank[i] - 1] = k;
    }
    return ans;
}

static int[] suffix(char[] s) {
    int alp = 128, n = s.length + 1, ans[] = new int[n], cls[] = new int[n], cnt[] = new int[alp], ncls[] = new int[n];
    ans[0] = n - 1;
    for (char c : s) ++cnt[c];
    for (int i = 1; i < alp; ++i) cnt[i] += cnt[i - 1];
    for (int i = 0; i < n - 1; ++i) ans[cnt[s[i]]--] = i;
    cls[ans[1]] = 1;
    for (int i = 2; i < n; ++i) cls[ans[i]] = cls[ans[i - 1]] + (s[ans[i]] > s[ans[i - 1]] ? 1 : 0);
    for (int offset = 1; offset <= n; offset <<= 1) {
        cntsort(ans, cls, n, offset);
        cntsort(ans, cls, n, 0);
        fill(ncls, 0);
        for (int i = 1; i < n; ++i) ncls[ans[i]] = ncls[ans[i - 1]] + (cls[ans[i]] > cls[ans[i - 1]] || cls[(ans[i] + offset) % n] > cls[(ans[i - 1] + offset) % n] ? 1 : 0);
        cls = copy(ncls);
    }
    return ans;
}

static void cntsort(int[] a, int[] by, int n, int offset) {
    int[] cnt = new int[n + 1], ans = new int[n];
    for (int b : by) ++cnt[b + 1];
    for (int i = 1; i <= n; ++i) cnt[i] += cnt[i - 1];
    for (int i = 0; i < n; ++i) ans[cnt[by[(a[i] + offset) % n]]++] = a[i];
    for (int i = 0; i < n; ++i) a[i] = ans[i];
}
