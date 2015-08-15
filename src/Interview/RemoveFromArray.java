package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 11/26/14.
 */
public class RemoveFromArray {
    public static int removeKey(int[] A, int key) {
        int i = 0, j = 0;
        while (j < A.length) {
            if (A[j] == key) {
                j ++;
            }
            A[i ++] = A[j ++];
        }
        return i;
    }

    public static int removeDuplicate(int[] A) {
        if (A == null) {
            return -1;
        }
        if (A.length < 2) {
            return A.length;
        }
        int i = 0, j = 1;
        while (j < A.length) {
            if (A[j] != A[j-1]) {
                i++;
                A[i] = A[j];
            }
            j ++;
        }
        return i+1;
    }

    public static void main(String[] args) {
        int [] A = {5,3,7,11,2,3,13,5,7};
        int size = removeKey(A, 3);
        System.out.println("size = " + size);
        System.out.println(Arrays.toString(A));
        int[] B = {2,3,5, 5,7,11,11,11,13};
        int size2 = removeDuplicate(B);
        System.out.println("size2 = " + size2);
        System.out.println(Arrays.toString(B));
    }
}
