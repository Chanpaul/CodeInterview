package Interview;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yongyangyu on 11/25/14.
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 *
 * For example,
 * Given the following matrix:
 *
 * [
 *   [ 1, 2, 3 ],
 *   [ 4, 5, 6 ],
 *   [ 7, 8, 9 ]
 * ]
 *
 * You should return [1,2,3,6,9,8,7,4,5].
 */
public class PrintMatrix {

    public static void spiral(int i, int m, int n, int[][] A, List<Integer> res) {
        if (m <= 0 || n <= 0) {
            return;
        }
        for (int cid = 0; cid < n; cid ++) {
            res.add(A[i][i+cid]);
        }
        for (int rid = 1; rid < m-1; rid ++) {
            res.add(A[i+rid][i+n-1]);
        }
        if (m > 1) {
            for (int cid = n-1; cid >= 0; cid --) {
                res.add(A[i+m-1][i+cid]);
            }
        }
        if (n > 1) {
            for (int rid = m-2; rid > 0; rid --) {
                res.add(A[i+rid][i]);
            }
        }
        spiral(i+1, m-2, n-2, A, res);
    }

    public static List<Integer> printSpiral(int[][] A) {
        List<Integer> res = new LinkedList<Integer>();
        int m = A.length;
        if (m == 0) {
            return res;
        }
        int n = A[0].length;
        spiral(0, m, n, A, res);
        return res;
    }

    public static void main(String[] args) {
        int[][] A = {{1,2,3}, {4,5,6}, {7,8,9}};
        List<Integer> res = printSpiral(A);
        System.out.println(Arrays.toString(res.toArray()));
    }

}
