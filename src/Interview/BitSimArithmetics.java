package Interview;

/**
 * Created by yongyangyu on 8/19/15.
 * Compute a X b without arithmetical operations for positive integers.
 */
public class BitSimArithmetics {
    // Add() method works not only for negative integers but also negative integers.
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

    /**
     * As an extension to Add() and Multiply(), simulate division with add, subtract, and shift operations.
     * @param a dividend
     * @param b divisor
     * @return quotient a / b
     */
    public static int Divide(int a, int b) {
        int res = 0;
        int shift = 0;
        while (b <= a) {
            if ((b << shift) <= a) {
                while ((b << shift) <= a) {
                    shift++;
                }
                shift--;
            }
            else {
                while ((b << shift) > a) {
                    shift --;
                }
            }
            res += 1 << shift;
            a -= b << shift;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Add(100, 20));
        System.out.println(Add(-50, 30));
        System.out.println(Multiply(5, 9));
        System.out.println(Divide(1000, 8));
    }
}
