package Interview;

/**
 * Created by yongyangyu on 8/24/15.
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n,
 * find the one that is missing from the array.
 *
 * For example,
 * Given nums = [0, 1, 3] return 2.
 *
 * Note:
 * Your algorithm should run in linear runtime complexity.
 * Could you implement it using only constant extra space complexity?
 * Solution:
 * 1) XOR all the array elements, let the result of XOR be X1.
 * 2) XOR all numbers from 0 to n, let XOR be X2.
 * 3) XOR of X1 and X2 gives the missing number.
 * This is very similar to SingleNumber, where XOR just cancels the same elements.
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        int x1 = 0, x2;
        for (int i = 0; i <= nums.length; i ++) x1 ^= i;
        x2 = nums[0];
        for (int i = 1; i < nums.length; i ++) x2 ^= nums[i];
        return x1 ^ x2;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,3};
        MissingNumber missing = new MissingNumber();
        System.out.println(missing.missingNumber(nums));
    }
}
