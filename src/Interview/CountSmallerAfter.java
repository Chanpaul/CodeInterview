package Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yongyangyu on 12/6/15.
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i].
 *
 * Example:
 *
 * Given nums = [5, 2, 6, 1]
 *
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 * Return the array [2, 1, 1, 0].
 */
public class CountSmallerAfter {
    public List<Integer> countSmaller(int[] nums) {
        List<Integer> sorted = new ArrayList<>(); // using ArrayList for fast binary search
        Integer[] res = new Integer[nums.length];
        for (int i = nums.length - 1; i >= 0; i --) {
            int idx = findIndex(sorted, nums[i]);
            res[i] =  idx;
            sorted.add(idx, nums[i]);
        }
        return Arrays.asList(res);
    }

    private int findIndex(List<Integer> sorted, int target) {
        if (sorted.size() == 0) return 0;
        int lo = 0, hi = sorted.size() - 1;
        if (sorted.get(hi) < target) return hi + 1;
        if (sorted.get(lo) >= target) return 0; // sorted[0] == target means no number smaller than target
        while (lo+1 < hi) {
            int mid = lo + (hi - lo) / 2;
            if (sorted.get(mid) < target) {
                lo = mid + 1;
            }
            else {
                hi = mid;
            }
        }
        if (sorted.get(lo) >= target) return lo; // lo position is returned
        return hi;
    }

    public static void main(String[] args) {
        int[] nums = {5,2,6,1};
        CountSmallerAfter csa = new CountSmallerAfter();
        System.out.println(Arrays.toString(csa.countSmaller(nums).toArray()));
    }
}
