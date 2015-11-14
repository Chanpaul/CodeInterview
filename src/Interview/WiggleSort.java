package Interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yongyangyu on 11/24/14.
 * Write a function to convert the array into alternate increasing decreasing numbers:
 *
 * a[0] <= a[1] >= a[2] <= a[3] >=...
 *
 * Note: You should solve it in place and one pass.
 */
public class WiggleSort {
    public static void swap(List<Integer> A, int i, int j) {
        int tmp = A.get(i);
        A.set(i, A.get(j));
        A.set(j, tmp);
    }

    public static void rearrange(List<Integer> A) {
        for (int i = 1; i < A.size(); i ++){
            if (((i % 2 == 1) && A.get(i-1) > A.get(i) || ((i % 2 == 0) && A.get(i-1) < A.get(i)))) {
                swap(A, i-1, i);
            }
        }
    }

    public static void main(String[] args) {
        int [] A = {1,2,3,4,5,6,7,8,9,10};
        List<Integer> B = new ArrayList<Integer>();
        for (int x : A) {
            B.add(x);
        }
        Collections.shuffle(B);
        for (Integer x : B) {
            System.out.print(x + " ");
        }
        System.out.println();
        rearrange(B);
        for (Integer x : B) {
            System.out.print(x + " ");
        }
        System.out.println();
    }
}
