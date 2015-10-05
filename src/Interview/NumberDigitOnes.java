package Interview;

/**
 * Created by yongyangyu on 7/7/15.
 * Given an integer n, count the total number of digit 1
 * appearing in all non-negative integers less than or equal to n.

 * For example:
 * Given n = 13,
 * Return 6, because digit 1 occurred in the following numbers: 1, 10, 11, 12, 13.

 * Hint:

 * Beware of overflow.
 */
public class NumberDigitOnes {
    public int countDigitOne(int n) {
        if (n <= 0) {
            return 0;
        }
        String sn = n + "";
        int accum = 0;
        for (int i = 1; i < sn.length(); i ++) {
            accum += onesLevelK(i);
        }
        return currentLevel(n, sn) + accum;
    }

    private int currentLevel(int n, String sn) {
        int count = 0;
        if (n < 10) {
            if (n == 0) {
                return 0;
            }
            else {
                return 1;
            }
        }

        int div = n / (int)Math.pow(10, sn.length() - 1);
        if (div > 1) {
            int smaller = 0;
            for (int i = 1; i < sn.length(); i++) {
                smaller += onesLevelK(i);
            }
            count += smaller + (int) Math.pow(10, sn.length() - 1);
            count += (div - 2) * smaller;
        } else {
            count += n - (int) Math.pow(10, sn.length() - 1) + 1;
        }
        n %= (int) Math.pow(10, sn.length() - 1);
        sn = n + "";
        // compute the number of 1's from Y00..0 ~ Yxx..x, where Y-th position is computed
        int accum = 0;
        for (int i = 1; i < sn.length(); i ++) {
            accum += onesLevelK(i);
        }
        return count + currentLevel(n, sn) + accum;
    }

    private int onesLevelK(int k) {
        if (k == 1) {
            return 1;
        }
        int[] level = new int[k];
        level[0] = 1;
        for (int i = 1; i < level.length; i ++) {
            int tmp = 0;
            for (int j = 0; j <= i - 1; j ++) {
                tmp += level[j];
            }
            level[i] = (int)Math.pow(10, i) + 9 * tmp;
        }
        return level[level.length - 1];
    }

    public static void main(String[] args) {
        int n = 1000;
        System.out.println(new NumberDigitOnes().countDigitOne(n));
        //System.out.println(new NumberDigitOnes().onesLevelK(3));
    }
}
