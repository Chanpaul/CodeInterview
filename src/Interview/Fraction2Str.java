package Interview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yongyangyu on 12/16/14.
 * Given two integers representing the numerator and denominator of a fraction,
 * return the fraction in string format.
 *
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 *
 * For example,
 *
 * Given numerator = 1, denominator = 2, return "0.5".
 * Given numerator = 2, denominator = 1, return "2".
 * Given numerator = 2, denominator = 3, return "0.(6)".
 */
public class Fraction2Str {
    public static String fractionToDecimal(int numerator, int denominator) {
        long a = (long)numerator, b = (long)denominator;
        if (a % b == 0) { // corner case when either = Integer.MIN_VALUE
            long res = a / b;
            return String.valueOf(res);
        }
        boolean sign = false;
        if ((a > 0 && b < 0 ) || (a < 0 && b > 0)) {
            sign = true;
        }
        a = Math.abs(a);
        b = Math.abs(b);
        StringBuilder sb = new StringBuilder();
        Map<Long, Integer> pos = new HashMap<>();  // pos(x) = the position that x occurs
        int repeatPos = -1;
        boolean repeat = false;
        if (sign) {
            sb.append('-');
        }
        long p = a % b;
        sb.append(a / b);
        sb.append('.');
        while (p * 10 % b != 0) {
            if (pos.containsKey(p * 10)) {
                repeat = true;
                repeatPos = pos.get(p * 10);
                break;
            }
            pos.put(p*10, sb.length());
            sb.append(p * 10 / b);
            p = p * 10 % b;
        }
        if (repeat) { // add parenthesis
            sb.insert(repeatPos, '(');
            sb.append(')');
        }
        else {
            if (p1 * 10 / b != 0) {
                sb.append(p1 * 10 / b);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(fractionToDecimal(1, 2));  // "0.5"
        System.out.println(fractionToDecimal(2, 1));  // "2"
        System.out.println(fractionToDecimal(2, 3));  // "0.(6)"
        System.out.println(fractionToDecimal(9, 11)); // "0.(81)"
        System.out.println(fractionToDecimal(10, 101));
        System.out.println(fractionToDecimal(-1, -2147483648));
        System.out.println(fractionToDecimal(Integer.MIN_VALUE, -1));
        System.out.println(fractionToDecimal(1, 214748364));
    }
}
