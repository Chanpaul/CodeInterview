package Interview;

/**
 * Created by yongyangyu on 11/24/15.
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like
 * (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
 * After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
 *
 * Example:
 *
 *  prices = [1, 2, 3, 0, 2]
 *  maxProfit = 3
 *  transactions = [buy, sell, cooldown, buy, sell]
 */
public class BuySellStockCooldown {
    public int matProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int n = prices.length;
        int[] dp = new int[n];
        dp[1] = Math.max(0, prices[1] - prices[0]);
        int buy = Math.max(-prices[0], -prices[1]); // largest profit when buy at i-th day
        for (int i = 2; i < n; i ++) {
            // i-th day is cooldown, profit is dp[i-1] or buy stock
            dp[i] = Math.max(dp[i-1], prices[i]+buy);
            // i-th day is cooldown, buy profit keeps the same or update the buy profit
            // here buy means max buy profit at (i-1)-th day
            buy = Math.max(buy, dp[i-2] - prices[i]);
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        int[] prices = {1,2,3,0,2};
        BuySellStockCooldown bsscd = new BuySellStockCooldown();
        System.out.println(bsscd.matProfit(prices));
    }
}
