// knuth-morris-pratt algorithm for pattern searching

// time complexity: O(n + m)
// kmp(str, pat) returns indices of matches (indices of 1st char) of pattern pat in string str

static List<Integer> kmp(char[] str, char[] pat) {
    List<Integer> ans = new ArrayList<>();
    int len = 0, i = 1, j = 0, lps[] = new int[pat.length];
    while (i < pat.length) {
        if (pat[i] == pat[len]) lps[i++] = ++len;
        else if (len != 0) len = lps[len - 1];
        else lps[i++] = len;
    }
    i = 0;
    while (i < str.length) {
        if (str[i] == pat[j]) {
            ++i;
            ++j;
        }
        if (j == pat.length) {
            ans.add(i - j);
            j = lps[j - 1];
        } else if (i < str.length && str[i] != pat[j]) {
            if (j == 0) ++i;
            else j = lps[j - 1];
        }
    }
    return ans;
}
