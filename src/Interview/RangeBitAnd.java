package Interview;

/**
 * Created by yongyangyu on 4/16/15.
 * Given a range [m, n] where 0 <= m <= n <= 2147483647,
 * return the bitwise AND of all numbers in this range, inclusive.
 *
 * For example, given the range [5, 7], you should return 4.
 */
public class RangeBitAnd {
    public static int rangeBitwiseAnd(int m, int n) {
        if (m == 0) {
            return 0;
        }
        int move = 1;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            move <<= 1;
        }
        return m * move;
    }

    public static void main(String[] args) {
        System.out.println(rangeBitwiseAnd(600000000, 2147483645));
    }
}
