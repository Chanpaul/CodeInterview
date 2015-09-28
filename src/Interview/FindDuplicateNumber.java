package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 9/28/15.
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number,
 * find the duplicate one. (Pigeon hole principle)
 *
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n^2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class FindDuplicateNumber {
    public int findDuplicate(int[] nums) {
        int res = -1;
        for (int i = 0; i < nums.length; i ++) {
            if (nums[Math.abs(nums[i])] < 0) res = Math.abs(nums[i]);
            else {
                nums[Math.abs(nums[i])] = - nums[Math.abs(nums[i])];
            }
        }
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] < 0) {
                nums[i] = -nums[i];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,3};
        System.out.println(new FindDuplicateNumber().findDuplicate(nums));
        System.out.println(Arrays.toString(nums));
    }
}
