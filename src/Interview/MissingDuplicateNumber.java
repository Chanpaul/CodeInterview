package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 9/6/15.
 * Given an array of n integers, each between 0 and n - 1 inclusive,
 * Only one element appears twice, implying exactly one number between
 * 0 and n - 1 is missing from the array.
 * Find the missing and duplicate number.
 */
public class MissingDuplicateNumber {
    /**
     * @param nums the array of integers
     * @return [duplicate, missing]
     */
    public int[] findNumbers(int[] nums) {
        int missXorDup = 0;
        for (int i = 0; i < nums.length; i ++) {
            missXorDup ^= i ^ nums[i];
        }

        int diffBit = missXorDup & (~(missXorDup - 1)); // find the difference in LSB
        int missOrDup = 0;
        for (int i = 0; i < nums.length; i ++) {
            if ((i & diffBit) != 0) {
                missOrDup ^= i;
            }
            if ((nums[i] & diffBit) != 0) {
                missOrDup ^= nums[i];
            }
        }
        for (int x : nums) {
            if (x == missOrDup) {
                return new int[]{missOrDup, missOrDup ^ missXorDup};
            }
        }
        return new int[]{missOrDup ^ missXorDup, missOrDup};
    }

    public static void main(String[] args) {
        int[] nums = {5,3,0,3,1,2};
        int[] res = new MissingDuplicateNumber().findNumbers(nums);
        System.out.println(Arrays.toString(res));
    }
}
