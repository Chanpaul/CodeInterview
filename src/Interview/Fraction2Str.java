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
        if (numerator % denominator == 0) { // corner case when either = Integer.MIN_VALUE
            long res = (long)numerator / (long)denominator;
            return new Long(res).toString();
        }
        boolean sign = false;
        if ((numerator > 0 && denominator < 0 ) || (numerator < 0 && denominator > 0)) {
            sign = true;
        }
        long num = Math.abs((long)numerator);
        long denom = Math.abs((long)denominator);
        StringBuilder sb = new StringBuilder();
        Map<Long, Integer> map = new HashMap<Long, Integer>();  // map(p1) = p1 / denominator
        int repeatPos = -1;
        boolean repeat = false;
        if (sign) {
            sb.append('-');
        }
        long p1 = num / denom;
        sb.append(p1);
        sb.append('.');
        p1 = num % denom;
        map.put(p1*10, sb.length());
        sb.append(p1 * 10 / denom);
        p1 = p1 * 10 % denom;
        while (p1 * 10 % denom != 0) {
            if (map.containsKey(p1 * 10)) {
                repeat = true;
                repeatPos = map.get(p1 * 10);
                break;
            }
            map.put(p1*10, sb.length());
            long p2 = p1 * 10 / denom;
            p1 = p1 * 10 % denom;
            sb.append(p2);
        }
        if (repeat) {
            // add parenthesis
            sb.insert(repeatPos, '(');
            sb.append(')');
        }
        else {
            if (p1 * 10 / denom != 0) {
                sb.append(p1 * 10 / denom);
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
