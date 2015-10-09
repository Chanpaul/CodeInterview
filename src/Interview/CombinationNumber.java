package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 10/9/15.
 * Given an array of integers and a target value,
 * compute the number of ways to get the sum of the
 * target from the given array.
 */
public class CombinationNumber {
    public int numberOfWays(int[] nums, int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        Arrays.sort(nums);
        for (int x: nums) {
            for (int i = x; i <= target; i ++) {
                dp[i] += dp[i-x];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        int[] nums = {2,3,7};
        System.out.println(new CombinationNumber().numberOfWays(nums, 12)); // 4
    }
}
