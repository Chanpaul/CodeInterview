package Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by yongyangyu on 12/16/14.
 */
public class Selection {
    public static void swap(int[]A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }
    public static int select (int[] A, int k) {
        int pivot = A[0];
        int i, j;
        i = 1;
        while (i < A.length && A[i] <= pivot) {
            i ++;
        }
        for (j = i; j < A.length; j ++) {
            if (A[j] <= pivot) {
                swap(A, i, j);
                i ++;
            }
        }
        if (i > 0) {
            i --;
        }
        swap(A, 0, i);
        if (i == k-1) {
            return pivot;
        }
        else {
            if (i < k) {
                return select(Arrays.copyOfRange(A, i+1, A.length), k-i-1);
            }
            else {
                return select(Arrays.copyOfRange(A, 0, i), k);
            }
        }
    }

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < 9; i ++) {
            arr.add(i);
        }
        Collections.shuffle(arr);
        int[] A = new int[arr.size()];
        for (int i = 0; i < A.length; i++) {
            A[i] = arr.get(i);
        }

        System.out.println(select(A, 5));

        int[] Arr = {3,2,1,5,6,4};
        System.out.println(select(Arr, 5));
    }
}
