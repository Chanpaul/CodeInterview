package Interview;

/**
 * Created by yongyangyu on 10/10/15.
 * Given an array of A of n numbers, find the length of a longest subsequence such that
 * it is increasing.
 *
 * For example,
 * Given [10, 9, 2, 5, 3, 7, 101, 18],
 * The longest increasing subsequence is [2, 3, 7, 101], therefore the length is 4.
 * Note that there may be more than one LIS combination, it is only necessary for you to
 * return the length.
 */
public class LongestIncreasingSubseq {
    // an O(nlogn) algorithm for computing the length
    public int length(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] tail = new int[nums.length];
        int len = 1;
        tail[0] = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] < tail[0]) {
                tail[0] = nums[i];
            }
            else if (nums[i] > tail[len - 1]) {
                tail[len ++] = nums[i];
            }
            else {
                tail[binSearchLarger(0, len-1, nums[i], tail)] = nums[i];
            }
        }
        return len;
    }
    // find the index where the previous element is smaller than target
    private int binSearchLarger(int left, int right, int target, int[] tail) {
        while (right > left + 1) {
            int mid = left + (right - left) / 2;
            if (tail[mid] >= target) right = mid; // using >= for the case when equal elements exist
            else left = mid;
        }
        return right;
    }

    public int lengthDP(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int[] len = new int[nums.length];
        len[0] = 1;
        int max = 0;
        for (int i = 1; i < len.length; i ++) {
            for (int j = 0; j < i; j ++) {
                if (nums[i] > nums[j]) {
                    len[i] = Math.max(len[i], len[j] + 1);
                }
            }
            max = Math.max(max, len[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {3,5,6,2,5,4,19,5,6,7,12};
        LongestIncreasingSubseq lis = new LongestIncreasingSubseq();
        System.out.println(lis.length(nums));
        System.out.println(lis.lengthDP(nums));
    }
}
