package Interview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yongyangyu on 4/22/15.
 * Write an algorithm to determine if a number is "happy".
 * A happy number is a number defined by the following process:
 * Starting with any positive integer, replace the number by the sum
 * of the squares of its digits, and repeat the process until the number
 * equals 1 (where it will stay), or it loops endlessly in a cycle which
 * does not include 1. Those numbers for which this process ends in 1 are happy numbers.
 * Example: 19 is a happy number
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 *
 */
public class HappyNumber {
    private static int squareSum(int x) {
        String digits = x + "";
        int res = 0;
        for (int i = 0; i < digits.length(); i ++) {
            int d = digits.charAt(i) - '0';
            res += d * d;
        }
        return res;
    }

    public static boolean isHappy(int n) {
        Map<Integer, Boolean> map = new HashMap<>();
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
