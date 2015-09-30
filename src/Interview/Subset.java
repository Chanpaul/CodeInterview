package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 3/16/15.
 * Given a set of distinct integers, nums, return all possible subsets.
 * Elements in a subset must be in non-descending order.
 * The solution set must not contain duplicate subsets.
 */
public class Subset {
    public List<List<Integer>> subset(int[] nums) {
        // use binary representation for enumerating
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < (1 << n); i ++) {
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j < n; j ++) {
                if (((1 << j) & i) != 0) {
                    cur.add(nums[j]);
                }
            }
            res.add(cur);
        }
        return res;
    }

    /**
     * Given a collection of integers that might contain duplicates, nums, return all possible subsets.
     * Elements in a subset must be in non-descending order.
     * The solution set must not contain duplicate subsets.
     */
    public List<List<Integer>> subsetDup(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> res = new HashSet<>();
        Arrays.sort(nums);
        for (int i = 0; i < (1 << n); i ++) {
            List<Integer> cur = new ArrayList<>();
            for (int j = 0; j < n; j ++) {
                if (((1 << j) & i) != 0) {
                    cur.add(nums[j]);
                }
            }
            if (!res.contains(cur)) {
                res.add(cur);
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        for (List x : res) {
            result.add(x);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2};
        List<List<Integer>> res = new Subset().subsetDup(nums);
        for (List x : res) {
            System.out.println(Arrays.toString(x.toArray()));
        }
    }
}
