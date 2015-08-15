package Interview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yongyangyu on 4/22/15.
 */
public class HappyNumber {
    private static int squareSum(int x) {
        String digits = String.valueOf(x);
        int res = 0;
        for (int i = 0; i < digits.length(); i ++) {
            int d = digits.charAt(i) - '0';
            res += d * d;
        }
        return res;
    }

    public static boolean isHappy(int n) {
        Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        while (true) {
            n = squareSum(n);
            if (n == 1) {
                return true;
            }
            if (!map.containsKey(n)) {
                map.put(n, true);
            }
            else {
                break;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 20;
        System.out.println(isHappy(n));
    }
}
