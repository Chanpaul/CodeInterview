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
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        if (A == null) {
            return null;
        }
        int curr = 0;
        int [] res = null;
        for (int i = A.length - 1; i >= 0; i --) {
            curr += A[i];
            map.put(curr, i);
        }
        if (map.containsKey(sum)) {
            res = new int[2];
            res[0] = map.get(sum);
            res[1] = A.length - 1;
        }
        Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, Integer> elem = iter.next();
            if (map.containsKey(elem.getKey() - sum)) {
                res = new int[2];
                res[0] = elem.getValue();
                res[1] = map.get(elem.getKey() - sum) - 1;
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
