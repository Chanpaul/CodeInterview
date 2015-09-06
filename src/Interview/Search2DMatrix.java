package Interview;

/**
 * Created by yongyangyu on 7/22/15.
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:
 * 1. Integers in each row are sorted in ascending from left to right.
 * 2. Integers in each column are sorted in ascending from top to bottom.
 * For example,
 * Consider the following matrix:
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * Given target = 5, return true.
 * Given target = 20, return false.
 */
public class Search2DMatrix {
    public boolean searchMat(int[][] matrix, int target) {
        int row = 0, col = matrix[0].length - 1;
        while (row < matrix.length && col >= 0) {
            if (target == matrix[row][col]) return true;
            else if (matrix[row][col] < target) {
                row ++;
            }
            else {
                col --;
            }
        }
        return false;
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        return search(matrix, target, 0, matrix.length - 1, 0, matrix[0].length - 1);
    }

    private boolean search(int[][] matrix, int target, int rlo, int rhi, int clo, int chi) {
        if (rlo >= matrix.length || clo >= matrix[0].length) {
            return false;
        }
        int tmp = binSearch(matrix[rlo], clo, chi, target);
        if (tmp < 0) {
            return false;
        }
        else if (tmp >= 0 && target == matrix[rlo][tmp]) {
            return true;
        }
        else if (tmp >= 0 && tmp < chi) {
            chi = tmp;
        }
        else if (tmp == chi) {
            rlo ++;
        }
        int arr[] = new int[matrix.length];
        for (int i = 0; i < arr.length; i ++) {
            arr[i] = matrix[i][clo];
        }
        tmp = binSearch(arr, rlo, rhi, target);
        if (tmp >= 0 && target == matrix[tmp][clo]) {
            return true;
        }
        else if (tmp >= 0 && tmp < rhi) {
            rhi = tmp;
        }
        else if (tmp == rhi) {
            clo ++;
        }
        return search(matrix, target, rlo, rhi, clo, chi);
    }

    private int binSearch(int[] arr, int lo, int hi, int target) {
        while (lo <= hi) {
            int mid =  lo + (hi - lo) / 2;
            if (arr[mid] == target) {
                return mid;
            }
            else if (arr[mid] < target) {
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }
        return hi;
    }

    public static void main(String[] args) {
        /*int[][] matrix = {{1,4,7,11,15},
                          {2,5,8,12,19},
                          {3,6,9,16,22},
                          {10,13,14,17,24},
                          {18,21,23,26,30}}; */
        int[][] matrix = {{1, 1}};

        Search2DMatrix s = new Search2DMatrix();
        System.out.println(s.searchMatrix(matrix, 2));
        //System.out.println(s.searchMatrix(matrix, 9));
    }
}
