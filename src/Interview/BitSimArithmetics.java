package Interview;

/**
 * Created by yongyangyu on 8/19/15.
 * Compute x*y without arithmetical operations for positive integers.
 */
public class BitSimArithmetics {
    public static int Add(int a, int b) {
        if (b == 0) return a;
        int sum = a ^ b;
        int carry = (a & b) << 1;
        return Add(sum, carry);
    }

    public static int Multiply(int a, int b) {
        int sum = 0;
        while (a != 0) {
            if ((a & 1) != 0) {
               sum = Add(sum, b);
            }
            a >>= 1;
            b <<= 1;
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(Add(100, 20));
        System.out.println(Add(-50, 30));
        System.out.println(Multiply(5, 9));
    }
}
