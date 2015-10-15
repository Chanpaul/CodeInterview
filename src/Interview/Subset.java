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

    // recusive version of subset solutionaddadd
    public List<List<Integer>> subSet(int[] nums) {
        List<Integer> tmp = new LinkedList<>();
        for (int x: nums)
            tmp.add(x);
        return getSubset(tmp);
    }

    public List<List<Integer>> getSubset(List<Integer> nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.size() == 0) {
            result.add(new ArrayList<>()); // empty set
            return result;
        }
        int curr = nums.remove(0);
        List<List<Integer>> mySubset = getSubset(nums);
        for (List<Integer> list: mySubset) {
            result.add(new ArrayList<>(list));
            List<Integer> tmp = new ArrayList<>(list);
            tmp.add(0, curr);
            result.add(tmp);
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
