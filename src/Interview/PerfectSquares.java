package Interview;

/**
 * Created by yongyangyu on 9/9/15.
 * Given a positive integer n, find the least number of perfect square numbers
 * (for example, 1, 4, 9, 16, ...) which sum to n.
 *
 *For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13,
 * return 2 because 13 = 4 + 9.
 */
public class PerfectSquares {
    public int numSquares(int n) {
        if (isSquare(n)) return 1;
        int[] dp = new int[n+1];
        dp[1] = 1;
        for (int i = 1; i <= n; i ++) {
            if (isSquare(i)) dp[i] = 1;
            else {
                dp[i] = i;
                int end = (int)Math.sqrt(i);
                for (int j = 1; j <= end; j ++) {
                    dp[i] = Math.min(dp[i], dp[i - j*j] + 1); // search for square numbers (j*j)
                }
            }
        }
        return dp[n];
    }

    private boolean isSquare(int n) {
        int root = (int)Math.sqrt(n);
        return root * root == n;
    }

    public static void main(String[] args) {
        PerfectSquares ps = new PerfectSquares();
        System.out.println(ps.numSquares(12));
        System.out.println(ps.numSquares(29));
    }
}
