package Interview;

/**
 * Created by yongyangyu on 10/12/15.
 * Divide two integers without using multiplication, division and mod operator.
 *
 * If it is overflow, return MAX_INT.
 */
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        int result = 0;
        boolean sign = (dividend > 0 && divisor < 0) ||
                       (dividend < 0 && divisor > 0);
        long a = dividend >= 0 ? dividend : -(long)dividend;
        long b = divisor >= 0 ? divisor : -(long)divisor;
        while (a >= b) {
            int mult = 1;
            long bb = b;
            while (a >= bb) {
                a -= bb;
                result += mult;
                if (bb < Integer.MAX_VALUE >> 1) {
                    bb += bb;
                    mult += mult;
                }
            }
        }
        if (sign) return -result;
        else return result;
    }
}
