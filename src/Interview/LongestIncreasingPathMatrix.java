package Interview;

/**
 * Created by yongyangyu on 1/22/16.
 * Given an integer matrix, find the length of the longest increasing path.
 *
 * From each cell, you can either move to four directions: left, right, up or down. You may NOT move diagonally
 * or move outside of the boundary (i.e. wrap-around is not allowed).
 *
 * Example 1:
 *
 * nums = [
 *   [9,9,4],
 *   [6,6,8],
 *   [2,1,1]
 * ]
 * Return 4
 * The longest increasing path is [1, 2, 6, 9].
 *
 * Example 2:
 *
 * nums = [
 *  [3,4,5],
 *  [3,2,6],
 *  [2,2,1]
 * ]
 * Return 4
 * The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 */
public class LongestIncreasingPathMatrix {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] memo = new int[m][n];
        int res = 1;
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                res = Math.max(res, search(matrix, i, j, memo));
            }
        }
        return res;
    }

    private int search(int[][] matrix, int row, int col, int[][] memo) {
        // for all the neighbors (i, j)
        // memo[r][c] = max{ memo[i][j] } + 1 if matrix[r][c] > matrix[i][j]
        // memo also helps to remove duplicates
        if (memo[row][col] == 0) {
            int m = matrix.length, n = matrix[0].length;
            // up
            if (row-1 >= 0 && matrix[row-1][col] > matrix[row][col]) {
                memo[row][col] = Math.max(memo[row][col], search(matrix, row-1, col, memo));
            }
            // right
            if (col+1 < n && matrix[row][col+1] > matrix[row][col]) {
                memo[row][col] = Math.max(memo[row][col], search(matrix, row, col+1, memo));
            }
            // down
            if (row+1 < m && matrix[row+1][col] > matrix[row][col]) {
                memo[row][col] = Math.max(memo[row][col], search(matrix, row+1, col, memo));
            }
            // left
            if (col-1 >= 0 && matrix[row][col-1] > matrix[row][col]) {
                memo[row][col] = Math.max(memo[row][col], search(matrix, row, col-1, memo));
            }
            memo[row][col] ++;
        }
        return memo[row][col];
    }

    public static void main(String[] args) {
        int[][] matrix = {{9,9,4}, {6,6,8}, {2,1,1}};
        LongestIncreasingPathMatrix lipm = new LongestIncreasingPathMatrix();
        System.out.println(lipm.longestIncreasingPath(matrix));
    }
}
