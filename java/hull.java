// gift wrapping convex hull algorithm

static int[] hull(int pts[][]) {
    int n = pts.length;
    double mid_x = 0, mid_y = 0;
    double angle[] = new double[n];
    Integer perm[] = new Integer[n];
    for (int i = 0; i < n; ++i) {
        mid_x += pts[i][0];
        mid_y += pts[i][1];
    }
    mid_x /= n;
    mid_y /= n;
    for (int i = 0; i < n; ++i) {
        angle[i] = atan2(pts[i][1] - mid_y, pts[i][0] - mid_x);
        perm[i] = i;
    }
    sort(perm, Comparator.comparingDouble(a -> angle[a]));

    int hull[] = new int[n], head = 0, tail = 2;
    hull[0] = perm[0];
    hull[1] = perm[1];
    for (int i = 2; i < n - 1; ++i) {
        while (tail > 1 && z_cross(sub(pts[hull[tail - 1]], pts[hull[tail - 2]]), sub(pts[perm[i]],pts[hull[tail - 2]] )) < 0) {
            --tail;
        }
        hull[tail++] = perm[i];
    }

    int p = perm[n - 1];
    while (tail > 1 && z_cross(sub(pts[hull[tail - 1]], pts[hull[tail - 2]]), sub(pts[p], pts[hull[tail - 2]])) < 0) {
        --tail;
    }

    boolean flag;
    do {
        flag = false;
        if (tail - head >= 2 && z_cross(sub(pts[p], pts[hull[tail - 1]]), sub(pts[hull[head]], pts[p])) < 0) {
            p = hull[--tail];
            flag = true;
        }
        if (tail - head >= 2 && z_cross(sub(pts[hull[head]], pts[p]), sub(pts[hull[head + 1]], pts[hull[head]])) < 0) {
            ++head;
            flag = true;
        }
    } while (flag);
    hull[tail++] = p;

    int ans[] = new int[tail - head];
    for (int i = 0; i < ans.length; ++i) {
        ans[i] = hull[head + i];
    }
    return ans;
}

static int[] sub(int[] a, int[] b) {
    return new int[] {a[0] - b[0], a[1] - b[1]};
}

static int z_cross(int[] a, int[] b) {
    return a[0] * b[1] - a[1] * b[0];
}
