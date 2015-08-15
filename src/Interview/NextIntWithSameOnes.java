package Interview;

/**
 * Created by yongyangyu on 11/23/14.
 */
public class NextIntWithSameOnes {
    public static boolean allOnes(int x) {
        int count = 0;
        int z = x;
        int y;
        while (z != 0) {
            y = z & (~(z-1));
            z ^= y;
            count ++;
        }
        return x == ((1 << count) - 1);
    }

    public static int nextBigger(int x) {
        int lowZero = -1;
        int highZero = -1;
        int lowOne = -1;
        int highOne = -1;
        for (int i = 0; i < 32; i ++) {
            if (((0x1 << i) & x) == 0) {
                if (lowZero < 0) {
                    lowZero = i;
                }
                highZero = i;
            }
            else {
                if (lowOne < 0) {
                    lowOne = i;
                }
                highOne = i;
            }
        }
        for (int i = lowZero; i < highOne; i ++) {
            if (((0x1 << i) & x) == 0) {
                highZero = i;
            }
        }
        int rval = x;
        if (lowZero > highOne) {  // x contains all 1s
            rval |= 0x1 << (highOne + 1);
            rval -= 0x1 << highOne;
            return rval;
        }
        else {
            if (lowOne == highOne) {  // only a single 1
                return rval << 0x1;
            }
            else {
                if (lowOne > highZero) {
                    int q = (x - (0x1 << highOne)) >>> lowOne;
                    rval = x - (q << lowOne);
                    rval = rval << 0x1;
                    rval += q;
                    return rval;
                }
                else {
                    // 0s intervene with 1s
                    int j = lowOne + 1;
                    while (j < highOne) {
                        if ((x & (0x1 << j)) == 0) {
                            break;
                        }
                        j ++;
                    }
                    // swap j-th position and lowOne
                    rval |= 0x1 << j;
                    rval -= 0x1 << lowOne;
                    return rval;
                }
            }
        }
    }

    public static void main(String[] args) {
        int x = 20;
        System.out.println(nextBigger(x));

        System.out.println(Integer.toBinaryString(Integer.MIN_VALUE));
        System.out.println(Integer.toBinaryString(-1));
    }
}
