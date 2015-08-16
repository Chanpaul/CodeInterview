package Interview;
import java.util.Arrays;

/**
 * Created by yongyangyu on 11/18/14.
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal
 * to the product of all the elements of nums except nums[i].
 *
 * Solve it without division and in O(n).
 *
 * For example, given [1,2,3,4], return [24,12,8,6].
 */
public class ExcludingProduct {
    public static int[] selfExcludingProduct(int[] nums) {
        int[] rval = new int[nums.length];
        int[] prodHead = new int[nums.length];
        int[] prodTail = new int[nums.length];
        int zero = 0, prod = 1;
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] == 0) {
                zero ++;
                if (zero >= 2) {
                    prod = 0;
                    break;
                }
            }
            else {
                prod *= nums[i];
            }
        }
        if (zero >= 2) {
            for (int i = 0; i < nums.length; i ++) {
                rval[i] = prod;
            }
        }
        else if (zero == 1) {
            for (int i = 0; i < nums.length; i ++) {
                if (nums[i] == 0) {
                    rval[i] = prod;
                    break;
                }
            }
        }
        else {
            // implement without division
            prodHead[0] = nums[0];
            prodTail[nums.length - 1] = nums[nums.length - 1];
            for (int i = 1; i < nums.length; i ++) {
                prodHead[i] = nums[i] * prodHead[i - 1];
            }
            for (int i = nums.length - 2; i >= 0; i --) {
                prodTail[i] = nums[i] * prodTail[i + 1];
            }
            for (int i = 0; i < rval.length; i ++) {
                if (i == 0) {
                    rval[i] = prodTail[i + 1];
                }
                else if (i == rval.length - 1) {
                    rval[i] = prodHead[i - 1];
                }
                else {
                    rval[i] = prodHead[i - 1] * prodTail[i + 1];
                }
            }
        }
        return rval;
    }

    public static void main(String [] args) {
        int[] nums = {3,1,4,2};
        System.out.println(Arrays.toString(ExcludingProduct.selfExcludingProduct(nums)));
    }
}
