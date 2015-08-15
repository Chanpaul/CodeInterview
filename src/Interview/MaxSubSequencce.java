package Interview;

/**
 * Created by yongyangyu on 11/18/14.
 */
public class MaxSubSequencce {
    public static int maxLengthPalindrome(int[] values) {
        int[][] dp = new int[values.length][values.length];
        for (int i = 0; i < dp.length; i ++) {
            dp[i][i] = 1;
        }
        // scan from the diagnal to the up-right corner
        int len = dp.length;
        for (int c = 1; c < len; c ++) { // distance from diag to the corner
            for (int i = 0; i < len - c; i ++) { // row index
                int j = i + c;
                if (values[i] == values[j]) {
                    dp[i][j] = 2 + dp[i+1][j-1];
                }
                else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][values.length - 1];
    }

    public static void main(String[] args) {
        int[] values = {4,1,2,3,4,5,6,5,4,3,4,4,4,4,4,4,4};
        System.out.println(maxLengthPalindrome(values));
    }
}
