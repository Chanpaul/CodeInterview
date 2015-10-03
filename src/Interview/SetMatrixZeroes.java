package Interview;

/**
 * Created by yongyangyu on 10/3/15.
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
 */
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        boolean rowZeros = false;
        boolean colZeros = false;
        for (int j = 0; j < n; j ++) {
            if (matrix[0][j] == 0) { // use first row as indicator for cols
                rowZeros = true;
                break;
            }
        }
        for (int i = 0; i < m; i ++) { // use first col as indicator for rows
            if (matrix[i][0] == 0) {
                colZeros = true;
                break;
            }
        }
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        for (int i = 1; i < m; i ++) {
            for (int j = 1; j < n; j ++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
        }
        if (rowZeros) {
            for (int j = 0; j < n; j ++)
                matrix[0][j] = 0;
        }
        if (colZeros) {
            for (int i = 0; i < m; i ++)
                matrix[i][0] = 0;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,1,1},
                          {0,1,2}};
        new SetMatrixZeroes().setZeroes(matrix);
    }
}
