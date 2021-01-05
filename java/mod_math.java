// modular arithmetic

// madd(...a) returns sum of array a modulo mmod
// msub(a, b) returns a - b modulo mmod
// mmul(...a) returns product of array a modulo mmod
// minv(x) returns multiplicative inverse of x modulo mmod
// mpow(a, b) returns a raised to the power of b modulo mmod

static int mmod = 1000000007;

static int madd(int a, int b) {
    return (a + b) % mmod;
}

static int madd(int... a) {
    int ans = a[0];
    for (int i = 1; i < a.length; ++i) ans = madd(ans, a[i]);
    return ans;
}

static int msub(int a, int b) {
    return (a - b + mmod) % mmod;
}

static int mmul(int a, int b) {
    return (int) ((long) a * b % mmod);
}

static int mmul(int... a) {
    int ans = a[0];
    for (int i = 1; i < a.length; ++i) ans = mmul(ans, a[i]);
    return ans;
}

// todo: which one is more efficient?
static int minv(int x) {
    // return mpow(x, mmod - 2);
    return (mod_math_exgcd(x, mmod)[0] % mmod + mmod) % mmod;
}

static int mpow(int a, long b) {
    if (a == 0) return 0;
    int ans = 1;
    while (b > 0) {
        if ((b & 1) > 0) ans = mmul(ans, a);
        a = mmul(a, a);
        b >>= 1;
    }
    return ans;
}

static int[] mod_math_exgcd(int a, int b) {
    if (b == 0) return new int[] {1, 0};
    int[] y = mod_math_exgcd(b, a % b);
    return new int[] {y[1], y[0] - y[1] * (a / b)};
}
