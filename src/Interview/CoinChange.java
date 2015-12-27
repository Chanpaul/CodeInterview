package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 12/27/15.
 * You are given coins of different denominations and a total amount of money amount.
 * Write a function to compute the fewest number of coins that you need to make up that amount.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * Example 1:
 * coins = [1, 2, 5], amount = 11
 * return 3 (11 = 5 + 5 + 1)
 *
 * Example 2:
 * coins = [2], amount = 3
 * return -1.
 *
 * Note:
 * You may assume that you have an infinite number of each kind of coin.
 */
public class CoinChange {
    public int change(int[] coins, int amount) {
        Arrays.sort(coins);
        int[][] dp = new int[coins.length+1][amount+1];
        for (int j = 0; j <= amount; j ++) {
            dp[0][j] = -1;
        }
        for (int i = 1; i <= coins.length; i ++) {
            for (int j = 1; j <= amount; j ++) {
                if (coins[i-1] == j) { // coins[i-1] == curr amount
                    dp[i][j] = 1;
                }
                else if (coins[i-1] > j) { // exclude coins[i-1]
                    dp[i][j] = dp[i-1][j];
                }
                else { // coins[i-1] < curr amount
                    if (dp[i-1][j] > 0 && dp[i][j - coins[i-1]] > 0) {
                        // not pick current coin or pick current coin
                        dp[i][j] = Math.min(dp[i - 1][j], 1 + dp[i][j - coins[i - 1]]);
                    }
                    else {
                        dp[i][j] = Math.max(dp[i - 1][j], 1 + dp[i][j - coins[i - 1]]);
                        dp[i][j] = dp[i][j] <= 0 ? -1 : dp[i][j];
                    }
                }
            }
        }
        return dp[coins.length][amount];
    }

    public static void main(String[] args) {
        int[] coins = {233,408,101,448,235,339,40,211};
        CoinChange cc = new CoinChange();
        System.out.println(cc.change(coins, 7392));
    }
}
