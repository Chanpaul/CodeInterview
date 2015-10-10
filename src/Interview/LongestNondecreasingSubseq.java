package Interview;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yongyangyu on 10/10/15.
 * Given an array of A of n numbers, find a longest subsequence such that
 * it is nondecreasing.
 */
public class LongestNondecreasingSubseq {
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
        int[] nums = {0,8,4,12,2,10,6,14,1,9};
        List<Integer> res = new LongestNondecreasingSubseq().longestSeq(nums);
        System.out.println(Arrays.toString(res.toArray()));
    }
}
