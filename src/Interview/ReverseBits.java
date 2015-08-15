package Interview;

/**
 * Created by yongyangyu on 3/7/15.
 *
 * Reverse bits of a given 32-bit unsigned integer.
 */
public class ReverseBits {
    public static int  reversebits(int n) {
        int rval = 0x0;
        for (int i = 0; i < 32; i ++) {
            if ( ((1 << i) & n) != 0) {
                rval |= 1 << (31 - i);
            }
        }
        return rval;
    }

    public static void main(String[] args) {
        int n = 43261596;

        System.out.println(reversebits(n));  // 964176192
    }
}
