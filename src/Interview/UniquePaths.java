package Interview;

/**
 * Created by yongyangyu on 10/9/15.
 * A robot is located at the top-left corner of a m x n grid.
 *
 * The robot can only move either down or right at any point in time.
 * The robot is trying to reach the bottom-right corner of the grid.
 *
 * How many possible unique paths are there?
 */
public class UniquePaths {
    public int numPaths(int m, int n) {
        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 1; i < m; i ++) {
            dp[i][0] = 1;
        }
        for (int j = 1; j < n; j ++) {
            dp[0][j] = 1;
        }
        for (int i = 1; i < m; i ++) {
            for (int j = 1; j < n; j ++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];
    }

    /*
     *  Now consider if some obstacles are added to the grids. How many unique paths would there be?
     * An obstacle and empty space is marked as 1 and 0 respectively in the grid.
     * For example,
     * There is one obstacle in the middle of a 3x3 grid as illustrated below.
     *
     *  [
     *    [0,0,0],
     *    [0,1,0],
     *    [0,0,0]
     *  ]
     * The total number of unique paths is 2.
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = 1 - obstacleGrid[0][0];
        for (int i = 1; i < m; i ++) {
            if (obstacleGrid[i][0] == 1) continue;
            if (dp[i-1][0] > 0) dp[i][0] = dp[i-1][0];
        }
        for (int j = 1; j < n; j ++) {
            if (obstacleGrid[0][j] == 1) continue;
            if (dp[0][j-1] > 0) dp[0][j] = dp[0][j-1];
        }
        for (int i = 1; i < m; i ++) {
            for (int j = 1; j < n; j ++) {
                if (obstacleGrid[i][j] == 1) dp[i][j] = 0;
                else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[m-1][n-1];
    }
}
