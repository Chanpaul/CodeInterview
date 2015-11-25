package Interview;

/**
 * Created by yongyangyu on 11/11/15.
 * Given a 2D matrix matrix, find the sum of the elements inside the rectangle
 * defined by (row1, col1), (row2, col2).
 *
 * Example:
 * Given matrix = [
 *  [3, 0, 1, 4, 2],
 *  [5, 6, 3, 2, 1],
 *  [1, 2, 0, 1, 5],
 *  [4, 1, 0, 1, 7],
 *  [1, 0, 3, 0, 5]
 * ]
 *
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * Note:
 * You may assume that the matrix does not change.
 * There are many calls to sumRegion function.
 * You may assume that row1 ≤ row2 and col1 ≤ col2.
 */
public class RangeSumQueryII {
    private int sum[][] = null;

    public RangeSumQueryII(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        int m = matrix.length, n = matrix[0].length;
        sum = new int[m+1][n+1];
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                sum[i+1][j+1] = sum[i+1][j] + sum[i][j+1] + matrix[i][j] - sum[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return sum[row2+1][col2+1] - sum[row1][col2+1] - sum[row2+1][col1] + sum[row1][col1];
    }

    public static void main(String[] args) {
        int[][] matrix = {{3, 0, 1, 4, 2},
                          {5, 6, 3, 2, 1},
                          {1, 2, 0, 1, 5},
                          {4, 1, 0, 1, 7},
                          {1, 0, 3, 0, 5}};
        RangeSumQueryII rsq = new RangeSumQueryII(matrix);
        System.out.println(rsq.sumRegion(2, 1, 4, 3));
        System.out.println(rsq.sumRegion(1, 1, 2, 2));
        System.out.println(rsq.sumRegion(1, 2, 2, 4));
    }
}
