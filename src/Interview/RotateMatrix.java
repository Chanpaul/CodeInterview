package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 8/23/15.
 * Rotate a matrix in 90 degree clockwise.
 * e.g.,
 *  1  2  3  4
 *  5  6  7  8
 *  9 10 11 12
 * 13 14 15 16
 * is converted to
 * 13  9 5 1
 * 14 10 6 2
 * 15 11 7 3
 * 16 12 8 4
 * Solution:
 * 1) transpose the matrix
 * 2) reverse each row of the transposed matrix
 */
public class RotateMatrix {
    public static int[][] rotate(int[][] matrix) {
        if (matrix.length == 0) return matrix;
        int m = matrix.length, n = matrix[0].length;
        // to handle non-square matrix, allocate some space
        int[][] res = new int[n][m];
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                res[i][j] = matrix[j][i];
            }
        }
        for (int i = 0; i < n; i ++) {
            reverseArray(res[i]);
        }
        return res;
    }
    private static void reverseArray(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (i <= j) {
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {{1,2,3,4}, {5,6,7,8}, {9,10,11,12}, {13,14,15,16}};
        int[][] res = rotate(matrix);
        for (int[] row : res) {
            System.out.println(Arrays.toString(row));
        }
    }
}
