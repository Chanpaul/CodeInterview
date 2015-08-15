package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 8/11/15.
 * Maximum subarray problem:
 * finding the contiguous subarray within a one-dimensional array of numbers
 * (containing at least one positive number) which has the largest sum.
 * For example, for the sequence of values −2, 1, −3, 4, −1, 2, 1, −5, 4;
 * the contiguous subarray with the largest sum is 4, −1, 2, 1, with sum 6.
 * Implementation of Kadane's algorithm. The complexity is linear O(n).
 * The problem can be extended to the 2-dimensinal version with an M-by-N matrix,
 * and the best known algorithm solves this in O(M^2 * N);
 * @see <a href="Maximum subarray problem">https://en.wikipedia.org/wiki/Maximum_subarray_problem</a>
 * The 2-d version of the problem originated from Problem 13 of Column 8 from "Programming Peals (2nd Edition)".
 */
public class MaxSubarray {
    /*
     * @param[in] arr: input array
     * @param[out]: an array of 3 elements, [starting_pos, end_pos, max_sum]
     */
    public static int[] max_subarray(int[] arr) {
        int start, end;
        start = end = -1;
        int cur, max_sum;
        cur = max_sum = 0;
        for (int i = 0; i < arr.length; i ++) {
            cur = Math.max(0, cur + arr[i]);
            start = (cur == arr[i]) ? i : start;
            max_sum = Math.max(max_sum, cur);
            end = (max_sum == cur) ? i : end;
        }
        return new int[]{start, end, max_sum};
    }

    /*
     * This is a 2-dimensional extension to the original problem.
     * Given an M-by-N matrix of positive and negative integers, find the
     * submatrix with the largest possible sum.
     * @param[in] matrix: two dimensional array
     * @param[out]: an array of 5 elements, [row_start, row_end, col_start, col_end, max_sum]
     */
    public static int[] max_subarray_2d(int[][] matrix) {
        int nrows = matrix.length;
        int ncols = matrix[0].length;
        int[] partialSum = new int[ncols];
        int max_sum = 0;
        int row_start, row_end, col_start, col_end;
        row_start = row_end = -1;
        col_start = col_end = -1;
        for (int i = 0; i < nrows; i ++) {
            clearArray(partialSum);
            for (int j = i; j < nrows; j ++) {
                for (int k = 0; k < ncols; k ++) {
                    partialSum[k] += matrix[j][k];
                }
                int[] res = max_subarray(partialSum);
                max_sum = Math.max(max_sum, res[2]);
                if (max_sum == res[2]) {
                    row_start = i; row_end = j;
                    col_start = res[0]; col_end = res[1];
                }
            }
        }
        return new int[]{row_start, row_end, col_start, col_end, max_sum};
    }

    private static void clearArray(int[] array) {
        for (int i = 0; i < array.length; i ++) {
            array[i] = 0;
        }
    }

    public static void main(String[] args) {
        int[] arr = {31, -41, 59, 26, -53, 58, 97, -93, -23, 84};  // [2, 6, 187]
        System.out.println(Arrays.toString(max_subarray(arr)));
        int [][] arr2d = {{9, -8, 1, 3, -2},
                          {-3, 7, 6, -2, 4},
                          {6, -4, -4, 8, -7}};  // [0, 2, 0, 3, 19]
        System.out.println(Arrays.toString(max_subarray_2d(arr2d)));
    }
}
