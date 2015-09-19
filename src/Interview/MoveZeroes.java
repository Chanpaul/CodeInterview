package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 9/19/15.
 * Given an array nums, write a function to move all 0's to the end of it
 * while maintaining the relative order of the non-zero elements.
 *
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function,
 * nums should be [1, 3, 12, 0, 0].
 *
 * Note:
 * You must do this in-place without making a copy of the array.
 * Minimize the total number of operations.
 */
public class MoveZeroes {
    public void move(int[] nums) {
        int zidx = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] != 0) {
                int tmp = nums[zidx];
                nums[zidx++] = nums[i];
                nums[i] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        new MoveZeroes().move(nums);
        System.out.println(Arrays.toString(nums));
    }
}
