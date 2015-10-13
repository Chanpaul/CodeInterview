package Interview;

/**
 * Created by yongyangyu on 7/7/15.
 * Given an integer n, count the total number of digit 1
 * appearing in all non-negative integers less than or equal to n.
 *
 * For example:
 * Given n = 13,
 * Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.
 *
 * Hint:
 *
 * Beware of overflow.
 */
public class NumberDigitOnes {
    public int countDigitOne(int n) {
        int count = 0;
        int len = ("" + n).length();
        for (int i = 0; i < len; i ++) {
            count += count1sInRangeAtDigit(n, i);
        }
        return count;
    }

    // count #1's at d-th digit position
    private int count1sInRangeAtDigit(int number, int d) {
        long power = (long)Math.pow(10, d);
        long nextPower = power * 10;
        long right = number % power;
        long down = number - number % nextPower;
        long up = down + nextPower;
        long digit = (number / power) % 10;
        if (digit < 1) return (int)(down / 10);
        else if (digit == 1) return (int)(down / 10 + right + 1);
        else return (int)(up / 10);
    }

    public static void main(String[] args) {
        int n = 1410065408;
        System.out.println(new NumberDigitOnes().countDigitOne(n));
    }
}
