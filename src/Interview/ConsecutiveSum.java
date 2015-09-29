package Interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by yongyangyu on 12/2/14.
 */
public class ConsecutiveSum {
    public static int[] getIndex(int[] A, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();
        if (A == null) {
            return null;
        }
        int curr = 0;
        int [] res;
        for (int i = A.length - 1; i >= 0; i --) {
            curr += A[i];
            map.put(curr, i);
        }
        res = new int[2];
        if (map.containsKey(sum)) {
            res[0] = map.get(sum);
            res[1] = A.length - 1;
        }

        for (int key: map.keySet()) {
            if (map.containsKey(key - sum)) {
                res[0] = map.get(key);
                res[1] = map.get(key - sum) - 1;
                return res;
            }
        }
        return res;
    }

    public static void main(String [] args) {
        int[] A = {2,6,1,10,15,8};
        System.out.println(Arrays.toString(getIndex(A,23)));
    }
}
