// miller-rabin primality test

// time complexity: O(k log n) where k = 7
// is_prime(x) returns true if x is prime
// is_prime_odd(x) returns true if x is prime provided that x is odd

// not tested

final static int[] __witnesses = {2, 325, 9375, 28178, 450775, 9780504, 1795265022};

static boolean is_prime(int x) {
    return x < 3 || x % 2 == 0 ? x == 2 : is_prime_odd(x);
}

static boolean is_prime_odd(int x) {
    int d = x - 1, r = 0;
    while (d % 2 == 0) {
        d >>= 1;
        ++r;
    }
    for (int a : __witnesses) {
        int v = miller_rabin_qpow(a, d, x);
        if (v <= 1 || v == x - 1) {
            continue;
        }
        for (int i = 0; i < r; ++i) {
            v = (int) (((long) v * v) % x);
            if (v == x - 1) {
                v = 1;
                break;
            }
            if (v == 1) {
                return false;
            }
        }
        if (v != 1) {
            return false;
        }
    }
    return true;
}

static int miller_rabin_qpow(long a, int n, int p) {
    long ans = 1;
    while (n > 0) {
        if (n % 2 == 1) {
            ans *= a;
            ans %= p;
        }
        a *= a;
        a %= p;
        n >>= 1;
    }
    return (int) ans;
}
