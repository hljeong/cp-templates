// prefix function

// time complexity: O(n)
// pi(s) returns prefix function pi of s, where pi[i] = length of longest proper prefix that is a suffix of s[:i + 1]

static int[] pi(char[] s) {
    int len = 0, i = 1, pi[] = new int[s.length];
    while (i < s.length) {
        if (s[i] == s[len]) pi[i++] = ++len;
        else if (len != 0) len = pi[len - 1];
        else pi[i++] = len;
    }
    return pi;
}
