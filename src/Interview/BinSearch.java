package Interview;

/**
 * Created by yongyangyu on 8/12/15.
 * This piece of code adopted from Column 9 of "Programming Pearls (2nd Edition)".
 * Two versions of binary searches.
 * Version 1 returns an arbitrary position if multiple instances of target exist in array x.
 * Version 2 always returns the position of the first appearance of target in array x.
 */
public class BinSearch {
    // return the index of target in array x
    // return -1 if target not exist in x
    public static int search(int[] x, int target) {
        int lo, hi;
        lo = 0; hi = x.length - 1;
        // invariant: if target is present, it is in x[lo .. hi]
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (x[mid] < target) {
                lo = mid + 1;
            }
            else if (x[mid] == target) {
                return mid;
            }
            else {
                hi = mid - 1;
            }
        }
        return -1;
    }

    // Version 2 is potentially more efficient than Version 1,
    // since only one comparison is conducted in each iteration.
    public static int search_first(int[] x, int target) {
        int lo, hi;
        lo = -1; hi = x.length;
        // invariant: x[lo] < target && x[hi] >= target && lo < hi
        while (lo + 1 != hi) {
            int mid = lo + (hi - lo) / 2;
            if (x[mid] < target) {
                lo = mid;
            }
            else {
                hi = mid;
            }
        }
        //assert lo + 1 == hi && x[lo] < target && x[hi] >= target
        if (hi >= x.length || x[hi] != target) {
            return -1;
        }
        return hi;
    }

    // This optimized version of binary search utilized techniques,
    // such as loop unrolling to hack the performance of binary search on a array of 1000 elements.
    public static int search1000(int[] x, int target) {
        if(x.length != 1000) {
            System.err.println("x.length != 1000, x.length = " + x.length);
            return -1;
        }
        int lo = -1;
        if (x[511] < target) lo = 1000 - 512; // cannot start lo from 511, otherwise overflow
        // assert x[lo] < target && x[lo + 512] >= target
        if (x[lo + 256] < target) lo += 256;
        // assert x[lo] < target && x[lo + 256] >= target
        if (x[lo + 128] < target) lo += 128;
        if (x[lo + 64 ] < target) lo += 64;
        if (x[lo + 32 ] < target) lo += 32;
        if (x[lo + 16 ] < target) lo += 16;
        if (x[lo + 8  ] < target) lo += 8;
        if (x[lo + 4  ] < target) lo += 4;
        if (x[lo + 2  ] < target) lo += 2;
        if (x[lo + 1  ] < target) lo += 1;
        int pos = lo + 1;
        if (pos > 1000 || x[pos] != target) {
            pos = -1;
        }
        return pos;
    }

    public static void main(String[] args) {
        int[] x = {1,1,1,1,1,1,1,1,1,1};
        System.out.println(search(x, 1));
        System.out.println(search_first(x, 1));
        int[] x1000 = new int[1000];
        for (int i = 0; i < 1000; i ++) {
            x1000[i] = i + 1;
        }
        System.out.println(search1000(x1000, 567));
    }
}
