package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 12/16/14.
 */
public class ThreeIncreasingElement {
    public static boolean find(int[] arr) {
        Stack<Integer> s = new Stack<Integer>();
        for (int i = 0; i < arr.length; i ++) {
            s.clear();
            s.push(arr[i]);
            for (int j = i+1; j < arr.length; j ++) {
                if (arr[j] > s.peek()) {
                    s.push(arr[j]);
                }
                if (s.size() >= 3) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        List<Integer> arr = new ArrayList<Integer>();
        for (int i = 0; i < 10; i ++) {
            arr.add(i);
        }
        Collections.shuffle(arr);
        System.out.println(Arrays.toString(arr.toArray()));
        int[] A = new int[arr.size()];
        for (int i = 0; i < A.length; i ++) {
            A[i] = arr.get(i);
        }
        System.out.println(find(A));
    }
}
