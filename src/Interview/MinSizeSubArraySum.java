package Interview;

/**
 * Created by yongyangyu on 5/12/15.
 * Given an array of n positive integers and a positive integer s,
 * find the minimal length of a subarray of which the sum ≥ s. If there isn't one, return 0 instead.
 *
 * For example, given the array [2,3,1,2,4,3] and s = 7,
 * the subarray [4,3] has the minimal length under the problem constraint.
 */
public class MinSizeSubArraySum {
    public static int minSubArrayLen(int s, int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; i ++) {
            sum += nums[i];
        }
        if (s > sum) {
            return 0;
        }
        else if (s == sum) {
            return nums.length;
        }
        else {
            int res = 0;
            int[] subSum = new int[nums.length];
            subSum[0] = nums[0];
            for (int i = 1; i < nums.length; i ++) {
                subSum[i] = subSum[i-1] + nums[i];
            }
            // find the first place satisfying requirement
            int smallest = findSmallestLargerOrEqual(subSum, s);
            res = smallest + 1;
            if (smallest < subSum.length - 1) {
                smallest ++;
            }
            for (int i = smallest; i < subSum.length; i ++) {
                int target = subSum[i] - s;
                int lower = findLargestSmallerOrEqual(subSum, target, i - 1);
                res = (i - lower < res) ? (i - lower) : res;
            }
            return res;
        }
    }

    private static int findSmallestLargerOrEqual(int[] A, int x) {
        int left = 0;
        int right = A.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (A[mid] < x) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
            if (left == right) {
                return left;
            }
        }
        return left;
    }

    private static int findLargestSmallerOrEqual(int[] A, int x, int upper) {
        int left = 0;
        int right = upper;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (mid == left && right - left == 1) {
                if (A[right] <= x) {
                    return right;
                }
                else {
                    return left;
                }
            }
            if (A[mid] > x) {
                right = mid - 1;
            }
            else {
                left = mid;
            }
            if (left == right) {
                return left;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        int s = 11;
        System.out.println(minSubArrayLen(s, nums));
    }
}
