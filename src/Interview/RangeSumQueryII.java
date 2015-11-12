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
    private int matrix[][];

    public RangeSumQueryII(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return;
        int m = matrix.length, n = matrix[0].length;
        this.matrix = matrix;
        sum = new int[m][n];
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (j == 0) sum[i][j] = matrix[i][j];
                else sum[i][j] = sum[i][j-1] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (sum == null) return 0;
        int res = 0;
        for (int i = row1; i <= row2; i ++) {
            res += sum[i][col2] - sum[i][col1] + matrix[i][col1];
        }
        return res;
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
