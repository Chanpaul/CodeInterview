package Interview;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by yongyangyu on 8/12/15.
 * This code implements classical quick-sort with some variations from
 * Column 11 of "Programming Pearls (2nd Edition)"
 */
public class QuickSort {
    public static final int CUTOFF = 30; // value referred from the book

    private static void swap(int[] x, int i, int j) {
        if ((i < 0 || i >= x.length) || (j < 0 || j >= x.length)) {
            System.err.println("Index out of range");
            System.exit(1);
        }
        int tmp = x[i];
        x[i] = x[j];
        x[j] = tmp;
    }

    public static void init(int[] x) {
        Random rand = new Random();
        for (int i = 0; i < x.length; i ++) {
            x[i] = rand.nextInt(1000);
        }
    }

    // qsort always selects x[lo] as the pivot to partition the array x
    // i.e., it has very bad performance on an array that is already sorted, O(n^2) for this case;
    public static void qsort(int[] x, int lo, int hi) {
        if (lo >= hi) return;
        int m = lo;
        for (int i = lo + 1; i <= hi; i ++) {
            // invariant: x[lo+1 .. m] < x[lo] && x[m+1 .. hi] >= x[lo]
            if (x[i] < x[m]) {
                swap(x, ++m, i);
            }
        }
        swap(x, m, lo);
        qsort(x, lo, m - 1);
        qsort(x, m + 1, hi);
    }

    public static void qsortRand(int[] x) {
        qsortApprox(x, 0, x.length - 1); // sort the array approximately with some random clumps
        isort(x);                        // sort this almost sorted array with insertion sort
    }

    // qsortApprox computed a nearly sorted array.
    // any interval with length smaller than CUTOFF is ignored
    private static void qsortApprox(int[] x, int lo, int hi) {
        if (hi - lo < CUTOFF) return;
        swap(x, lo, randInt(lo, hi));
        int t = x[lo];
        int i = lo;
        int j = hi + 1;
        while (true) {
            // NOTE: handling equal elements:
            // stop each scan on equal elements and swap them
            // transform the worst case of all equal elements into a best case that
            // requires almost exactly nlogn comparisons
            do {
                i ++;
            } while (i <= hi && x[i] < t);
            do {
                j --;
            } while (x[j] > t);
            if (i > j) break;
            swap(x, i, j);
        }
        swap(x, lo, j);
        qsortApprox(x, lo, j - 1);
        qsortApprox(x, j + 1, hi);
    }

    public static int randInt(int min, int max) {
        Random rand = new Random();
        int randNum = rand.nextInt((max - min) + 1) + min;
        return randNum;
    }

    // insertion sort
    public static void isort(int[] x) {
        for (int i = 1; i < x.length; i ++) {
            int tmp = x[i];
            int j;
            for (j = i; j > 0 && x[j - 1] > tmp; j --) {
                x[j] = x[j - 1];
            }
            x[j] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] x = new int[100];
        init(x);
        System.out.println(Arrays.toString(x));
        qsort(x, 0, 99);
        System.out.println(Arrays.toString(x));
        init(x);
        System.out.println(Arrays.toString(x));
        qsortRand(x);
        System.out.println(Arrays.toString(x));
    }
}
