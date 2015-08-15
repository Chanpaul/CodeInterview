package Interview;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by yongyangyu on 1/15/15.
 */
public class MostFreqSum {
    public static int getSum(int end) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i <= end; i ++) {
            String s = String.valueOf(i);
            int sum = 0;
            for (int j = 0; j < s.length(); j ++) {
                sum += (int)(s.charAt(j) - '0');
            }
            if (!map.containsKey(sum)) {
                map.put(sum, 1);
            }
            else {
                int tmp = map.get(sum);
                map.put(sum, tmp+1);
            }
        }
        int cnt = 0;
        Iterator<Integer> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            int curr = iter.next();
            if (map.get(curr) > cnt) {
                cnt = map.get(curr);
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(getSum(10));
        System.out.println(getSum(50));
        System.out.println(getSum(7777));
    }
}
