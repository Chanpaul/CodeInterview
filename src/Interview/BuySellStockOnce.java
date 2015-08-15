package Interview;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by yongyangyu on 8/15/15.
 * This is the sample question from EPI and two solutions are
 * presented; one is a recursion version and the other is the linear scan version.
 */
public class BuySellStockOnce {
    public int bestRecursion(int[] stocks) {
        if (stocks == null || stocks.length < 2) {
            return 0;
        }
        return maxProfit(stocks, 0, stocks.length - 1);
    }

    // The recursion solution takes O(n*logn) time.
    private int maxProfit(int[] stocks, int lo, int hi) {
        if (lo >= hi) return 0;
        int mid = lo + (hi - lo) / 2;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = lo; i <= mid; i ++) {
            min = Math.min(min, stocks[i]);
        }
        for (int i = mid + 1; i <= hi; i ++) {
            max = Math.max(max, stocks[i]);
        }
        int max1 = maxProfit(stocks, lo, mid);
        int max2 = maxProfit(stocks, mid + 1, hi);
        return Math.max(Math.max(max - min, max1), max2);
    }

    // linear scan takes O(n) time
    public int bestLinearScan(int[] stocks) {
        if (stocks == null || stocks.length < 2) {
            return 0;
        }
        int curMin = stocks[0];
        int best = Integer.MIN_VALUE;
        for (int i = 1; i < stocks.length; i ++) {
            curMin = Math.min(curMin, stocks[i]);
            best = Math.max(best, stocks[i] - curMin);
        }
        return best;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int[] stocks = new int[20];
        for (int i = 0; i < stocks.length; i ++) {
            stocks[i] = rand.nextInt(1000);
        }
        System.out.println(Arrays.toString(stocks));
        BuySellStockOnce bss = new BuySellStockOnce();
        System.out.println(bss.bestLinearScan(stocks));
        System.out.println(bss.bestRecursion(stocks));
    }
}
