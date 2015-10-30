package Interview;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yongyangyu on 10/10/15.
 * Given an array of A of n numbers, find the length of a longest subsequence such that
 * it is increasing.
 */
public class LongestIncreasingSubseq {
    // an O(nlogn) algorithm for computing the length
    public int length(int[] nums) {
        if (nums == null) return 0;
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
            if (tail[mid] > target) right = mid;
            else left = mid;
        }
        return right;
    }

    List<Integer> longestSeq(int[] nums) {
        int[] len = new int[nums.length];
        int[] parent = new int[nums.length];
        for (int i = 0; i < nums.length; i ++) {
            len[i] = 1;
            parent[i] = i;
            for (int j = 0; j < i; j ++) {
                if (nums[j] <= nums[i]) {
                    len[i] = Math.max(len[i], len[j] + 1);
                    if (len[i] == len[j] + 1)
                        parent[i] = j;
                }
            }
        }
        List<Integer> res = new LinkedList<>();
        int end = -1;
        int longest = 0;
        for (int i = 0; i < len.length; i ++) {
            if (len[i] > longest) {
                longest = len[i];
                end = i;
            }
        }
        res.add(0, nums[end]);
        while (parent[end] != end) {
            end = parent[end];
            res.add(0, nums[end]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 10, 2, 9, 3, 8, 4, 7, 5, 6};
        LongestIncreasingSubseq lis = new LongestIncreasingSubseq();
        List<Integer> res = lis.longestSeq(nums);
        System.out.println(Arrays.toString(res.toArray()));
        System.out.println(lis.length(nums));
    }
}
