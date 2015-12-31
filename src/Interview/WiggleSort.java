package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 11/24/14.
 * Write a function to convert the array into alternate increasing decreasing numbers:
 *
 * a[0] <= a[1] >= a[2] <= a[3] >=...
 *
 * Note: You should solve it in place and one pass.
 */
public class WiggleSort {
    public static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void wsort(int[] A) {
        for (int i = 1; i < A.length; i ++){
            if (((i % 2 == 1) && A[i-1] > A[i] || ((i % 2 == 0) && A[i-1] < A[i]))) {
                swap(A, i-1, i);
            }
        }
    }

    // a[0] < a[1] > a[2] < a[3] >...
    // equal numbers should not be adjacent to each other
    public static void wsort2(int[] A) {
        Arrays.sort(A);
        int[] B = new int[A.length];
        int i = (A.length-1) / 2, j = A.length - 1;
        for (int k = 0; k < B.length; k ++) {
            if (k % 2 == 0) B[k] = A[i--];
            else B[k] = A[j--];
        }
        System.arraycopy(B, 0, A, 0, A.length);
    }

    public static void main(String[] args) {
        int [] A = {4,5,5,6};
        wsort2(A);
        System.out.println(Arrays.toString(A));
    }
}
