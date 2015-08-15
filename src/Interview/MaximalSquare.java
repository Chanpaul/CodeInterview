package Interview;

/**
 * Created by yongyangyu on 6/3/15.
 */
public class MaximalSquare {
    public int maxSquare(char[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        // cp 1st row and col from matrix to dp
        int rval = 0;
        for (int i = 0; i < dp.length; i ++) {
            dp[i][0] = matrix[i][0] - '0';
            rval = dp[i][0] > rval ? dp[i][0] : rval;
        }
        for (int j = 0; j < dp[0].length; j ++) {
            dp[0][j] = matrix[0][j] - '0';
            rval = dp[0][j] > rval ? dp[0][j] : rval;
        }
        for (int i = 1; i < matrix.length; i ++) {
            for (int j = 1; j < matrix[0].length; j ++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                }
                else {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    rval = dp[i][j] > rval ? dp[i][j] : rval;
                }
            }
        }
        return rval * rval;
    }
    public static void main(String[] args) {
        char[][] matrix = {{'1', '1'}, {'1', '1'}};
        System.out.println(new MaximalSquare().maxSquare(matrix));
    }

}
