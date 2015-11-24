package Interview;

/**
 * Created by yongyangyu on 11/24/15.
 * Say you have an array for which the ith element is the price of a given stock on day i.
 *
 * Design an algorithm to find the maximum profit. You may complete at most k transactions.
 */
public class BuySellStockKTimes {
    public int maxProfit(int k, int[] prices) {
        int len = prices.length;
        if (len == 0) {
            return 0;
        }
        if (k >= len / 2)
            return largeK(prices);
        // local[i][j]: max profit till day i, j transactions, and there is a transaction on day i
        int[][] local = new int[len][k+1];
        // global[i][j]: max profit till day i, j transactions
        int[][] global = new int[len][k+1];
        for (int i = 1; i < len; i ++) {
            int diff = prices[i] - prices[i-1];
            for (int j = 1; j <= k; j ++) {
                local[i][j] = Math.max(global[i-1][j-1] + Math.max(diff, 0), local[i-1][j] + diff);
                global[i][j] = Math.max(global[i-1][j], local[i][j]);
            }
        }
        return global[len-1][k];
    }

    private int largeK(int[] prices) {
        int profit = 0;
        for (int i = 1; i < prices.length; i ++) {
            if (prices[i] > prices[i-1]) {
                profit += prices[i] - prices[i-1];
            }
        }
        return profit;
    }
}
