package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 8/17/15.
 * Given an array of numbers nums, in which exactly two elements appear only once and all the
 * other elements appear exactly twice. Find the two elements that appear only once.
 *
 * For example:
 *
 * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 *
 * Note:
 * The order of the result is not important. So in the above example, [5, 3] is also correct.
 * Your algorithm should run in linear runtime complexity. Could you implement it using only
 * constant space complexity?
 *
 * Solution:
 * Once again, we need to use XOR to solve this problem. But this time, we need to do it in two passes:
 * In the first pass, we XOR all elements in the array, and get the XOR of the two numbers we need to find.
 * Note that since the two numbers are distinct, so there must be a set bit in the XOR result. Find out the bit.
 * In the second pass, we divide all numbers into two groups, one with the aforementioned bit set, another with
 * the aforementinoed bit unset. Two different numbers we need to find must fall into thte two distrinct groups.
 * XOR numbers in each group, we can find a number in either group.
 *
 * Complexity:
 * Time: O (n)
 * Space: O (1)
 */
public class SingleNumberIII {
    public int[] singleNumber(int[] nums) {
        int xor = 0;
        for (int x : nums) {
            xor ^= x;
        }
        int mask1, mask2;
        mask1 = mask2 = 0;
        for (int i = 0; i < 32; i ++) {
            if ((xor & (1 << i)) != 0) {
                if (mask1 == 0) {
                    mask1 = 1 << i;
                }
                else if (mask2 == 0) {
                    mask2 = 1 << i;
                }
                else {
                    break;
                }
            }
        }
        int single1 = 0;
        int single2 = 0;
        for (int x : nums) {
            if ((x & mask1) != 0) {
                single1 ^= x;
            }
            if ((x & mask2) != 0) {
                single2 ^= x;
            }
        }
        return new int[]{single1, single2};
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 2, 5};
        System.out.println(Arrays.toString(new SingleNumberIII().singleNumber(nums)));
    }
}
