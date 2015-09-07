package Interview;

/**
 * Created by yongyangyu on 9/7/15.
 * You are a product manager and currently leading a team to develop a new product. Unfortunately,
 * the latest version of your product fails the quality check. Since each version is developed based
 * on the previous version, all the versions after a bad version are also bad.
 *
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one,
 * which causes all the following ones to be bad.
 *
 * You are given an API bool isBadVersion(version) which will return whether version is bad.
 * Implement a function to find the first bad version. You should minimize the number of calls to the API.
 *
 * This problem is equivalent to find the first element in a sorted array.
 * Using binary search for the first false version.
 */
public class FirstBadVersion {
    public static boolean isBadVersion(int version) {
        if (version >= Integer.MAX_VALUE) return true;
        return false;
    }

    public int firstBadVersion(int n) {
        int lo = 0;
        long hi = (long)n + 1;
        // invariant: isBadVersion(lo) returns false and isBadVersion(hi) returns true
        while (lo + 1 != hi) {
            int mid = lo + (int)((hi - lo) / 2);
            if (isBadVersion(mid)) {
                hi = mid;
            }
            else {
                lo = mid;
            }
        }
        return (int)hi;
    }

    public static void main(String[] args) {
        int n = 2147483647;
        FirstBadVersion fbv = new FirstBadVersion();
        System.out.println(fbv.firstBadVersion(n));
    }
}
