// z-function

// time complexity: O(n)
// z(s) returns z-function z of s z, where z[i] = length of longest common prefix between s and s[i:]

static int[] z(char[] s) {
    int n = s.length, z[] = new int[n];
    for(int i = 1, l = 0, r = 0; i < n; ++i) {
        if(i <= r) {
            z[i] = min(r - i + 1, z[i - l]);
        }
        while(i + z[i] < n && s[i + z[i]] == s[z[i]]) {
            ++z[i];
        }
        if(i + z[i] - 1 > r) {
            l = i;
            r = i + z[i] - 1;
        }
    }
    return z;
}
