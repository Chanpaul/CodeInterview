package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 11/24/14.
 */
public class SortNeg {
    public static void sort(int[] A) {  // O(1) space, O(n^2) time
        int p = 0, q = 0;
        while (p < A.length && q < A.length) {
            while (p < A.length && A[p] < 0) {
                p ++;
            }
            if (p == A.length) {
                return;
            }
            if (q == 0) {
                q = p + 1;
            }
            while (q < A.length && A[q] > 0) {
                q ++;
            }
            if (q == A.length) {
                return;
            }
            // shift A[p .. q]
            int tmp = A[q];
            int i = q;
            while (i != p) {
                A[i] = A[i-1];
                i --;
            }
            A[p] = tmp;
        }
    }

    public static void sort2(int[] A) {  // O(n) space, O(n) time
        int[] B = new int[A.length];
        int j = 0;
        // put negative nnumber in B
        for (int x : A) {
            if (x < 0) {
                B[j++] = x;
            }
        }
        for (int x : A) {
            if (x >= 0) {
                B[j++] = x;
            }
        }
        for (int i = 0; i < A.length; i ++) {
            A[i] = B[i];
        }
    }

/*    public static void sort3(int[] A) {  // O(1) space, O(n) time
        int negCnt = 0;
        for (int x : A) {
            if (x < 0) {
                negCnt ++;
            }
        }
        int p = 0, q = negCnt;
        boolean swap = false;
        while (p < negCnt) {
            while (A[p] < 0) {
                if (swap && p+1 < negCnt && A[p+1] < 0) {
                    int tmp = A[p];
                    A[p] = A[p+1];
                    A[p+1] = tmp;
                }
                p ++;
            }
            if (p == negCnt || q == A.length) {
                return;
            }
            if (A[p] > 0 || A[q] < 0) {
                int tmp = A[p];
                A[p] = A[q];
                A[q] = tmp;
                if (tmp > 0) {
                    q ++;
                }
                if (q < A.length && A[p] < 0) {
                    swap = true;
                }
            }
        }
    }*/

    public static void main(String[] args) {
        int[] A = {-1, 1, 3, -2, 2, -5};
        System.out.println(Arrays.toString(A));
        sort(A);
        System.out.println(Arrays.toString(A));
        int[] B = {-1, 1, 3, -2, 2, -5};
        sort2(B);
        System.out.println(Arrays.toString(B));
        int[] D = {-1, 1, -2, 2, -3, 3,-5,-9};
        sort2(D);
        System.out.println(Arrays.toString(D));
    }
}
