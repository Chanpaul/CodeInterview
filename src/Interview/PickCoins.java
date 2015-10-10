package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 10/10/15.
 * A line of coins lie on the table.
 * Two players take turns to pick up a coin from either end
 * of the line. Compute the max profit can a play get if he
 * chooses to play first.
 */
public class PickCoins {
    public int pick(int[] coins) {
        int[][] profit = new int[coins.length][coins.length];
        for (int[] t: profit) {
            Arrays.fill(t, -1);
        }
        return helper(coins, 0, coins.length-1, profit);
    }

    // a, b are the two indices of the coins left to be chosen
    private int helper(int[] coins, int a, int b, int[][] profit) {
        if (a > b) return 0;
        if (profit[a][b] == -1) {
            profit[a][b] = Math.max(coins[a] + Math.min(helper(coins, a+2, b, profit),
                                                        helper(coins, a+1, b-1, profit)),
                                    coins[b] + Math.min(helper(coins, a+1, b-1, profit),
                                                        helper(coins, a, b-2, profit)));
        }
        return profit[a][b];
    }

    public static void main(String[] args) {
        int[] coins = {25,5,10,5,10,5,10,25,1,25,1,25,1,25,5,10};
        System.out.println(new PickCoins().pick(coins));
    }
}
