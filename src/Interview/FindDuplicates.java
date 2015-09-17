package Interview;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yongyangyu on 9/17/15.
 * Given an array of n elements which contains elements from 0 to n-1,
 * with any of these numbers appearing any number of times.
 * Find these repeating numbers in O(n) and using only constant memory space.
 *
 * For example, let n be 7 and array be {1, 2, 3, 1, 3, 6, 6}, the answer should be 1, 3 and 6.
 */
public class FindDuplicates {
    public List<Integer> duplicate(int[] nums) {
        List<Integer> dup = new LinkedList<>();
        for (int i = 0; i < nums.length; i ++) {
            if (nums[Math.abs(nums[i])] > 0) {
                nums[Math.abs(nums[i])] = -nums[Math.abs(nums[i])];
            }
            else {
                dup.add(Math.abs(nums[i]));
            }
        }
        return dup;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, 3, 6, 6};
        System.out.println(Arrays.toString(new FindDuplicates().duplicate(nums).toArray()));
    }
}
