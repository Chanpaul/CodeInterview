package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 11/26/14.
 */
public class Permute {
    public static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void perm(int[] A, int i, int n) {
        if (i == n) {
            System.out.println(Arrays.toString(A));
            return;
        }
        for (int j = i; j <=n; j ++) {
            swap(A, i, j);
            perm(A, i+1, n);
            swap(A, i, j);
        }
    }


    public static int[] nextPerm(int[] A) {
        /*if (isDec(A)) {
            // elements in A are decreasing, return the sorted array
            Arrays.sort(A);
            return A;
        }*/
        for (int i = A.length - 1; i > 0; i --) {
            if (A[i] > A[i-1]) {
                int pos1 = i - 1;
                int pos2 = -1;
                for (int j = i; j < A.length; j ++) {
                    // find the smallest element larger than A[pos1]
                    if (A[j] > A[pos1]) {
                        if (pos2 < 0) {
                            pos2 = j;
                        }
                        else {
                            if (A[j] < A[pos2]) {
                                pos2 = j;
                            }
                        }
                    }
                }
                swap(A, pos1, pos2);
                Arrays.sort(A, pos1+1, A.length);
                return A;
            }
        }
        // A is decreasing
        Arrays.sort(A);
        return A;
    }

    public static void main(String [] args) {
        int [] A = {1,2,3};
        perm(A, 0, A.length-1);
        System.out.println("================");
        System.out.println(Arrays.toString(A));
        System.out.println("================");
        int [] P = {3,2,1,0};//{2,3,1,0};
        System.out.println(Arrays.toString(nextPerm(P)));
    }
}
