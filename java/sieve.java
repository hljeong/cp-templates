// sieve of eratosthenes

// time complexity: O(n log log n)
// sieve(n) returns an array div, where div[i] = 1 if i is prime otherwise a prime divisor of i, for i up to n

static int[] sieve(int n) {
    int[] div = new int[n + 1];
    for (int i = 2; i <= n; ++i) {
        if (div[i] == 0) {
            div[i] = 1;
            if ((long) i * i <= n) {
                int j = i * i;
                while (j <= n) {
                    div[j] = i;
                    j += i;
                }
            }
        }
    }
    return div;
}
