package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 11/26/14.
 */
public class RemoveFromArray {
    // Given a unsorted array, remove all the elements equal to the key.
    public static int removeKey(int[] A, int key) {
        int slow = 0;
        for (int fast = 0; fast < A.length; fast ++) {
            if (A[fast] == key) continue;
            A[slow++] = A[fast];
        }
        return slow;
    }

    // Given a sorted array, remove all the duplicated elements.
    public static int removeDuplicate(int[] A) {
        int slow = 0, fast;
        if (A == null) return -1;
        else if (A.length < 2) return ++slow;
        else {
            for (fast = 1; fast < A.length; fast ++) {
                if (A[fast] == A[slow]) continue;
                else {
                    A[++slow] = A[fast];
                }
            }
            return ++slow;
        }
    }

    public static void main(String[] args) {
        int [] A = {5,3,7,11,2,3,13,5,7};
        int size = removeKey(A, 3);
        System.out.println("size = " + size);
        System.out.println(Arrays.toString(A));
        int[] B = {2,3,5,5,7,11,11,11,13};
        int size2 = removeDuplicate(B);
        System.out.println("size2 = " + size2);
        System.out.println(Arrays.toString(B));
    }
}
