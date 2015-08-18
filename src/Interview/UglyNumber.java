package Interview;

/**
 * Created by yongyangyu on 8/18/15.
 * Write a program to check whether a given number is an ugly number.
 *
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5.
 * For example, 6, 8 are ugly while 14 is not ugly since it includes another prime factor 7.
 *
 * Note that 1 is typically treated as an ugly number.
 */
public class UglyNumber {
    public boolean isUgly(int num) {
        if (num <= 0) return false;
        while (num % 5 == 0) {
            num /= 5;
        }
        if (num == 1) return true;
        while (num % 3 == 0) {
            num /= 3;
        }
        if (num == 1) return true;
        while (num % 2 == 0) {
            num /= 2;
        }
        if (num == 1) return true;
        return false;
    }

    /**
     * For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
     * The key is how to maintain the order of the ugly numbers.
     * Try a similar approach of merging from three sorted lists: L1, L2, and L3.
     * Assume you have Uk, the kth ugly number. Then Uk+1 must be Min(L1 * 2, L2 * 3, L3 * 5).
     * @param n nth ugly number index
     * @return the nth ugly number
     */
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0; // 3 pointers pointing to the starting position
        for (int i = 1; i < n; i ++) {
            while (ugly[i] == 0 || ugly[i] == ugly[i - 1]) {
                int min = Math.min(Math.min(ugly[i2] * 2, ugly[i3] * 3), ugly[i5] * 5);
                ugly[i] = min;
                if (min == ugly[i2] * 2) i2++;
                else if (min == ugly[i3] * 3) i3++;
                else i5++;
            }
        }
        return ugly[n - 1];
    }

    public static void main(String[] args) {
        UglyNumber ugly = new UglyNumber();
        System.out.println(ugly.isUgly(32));
        System.out.println(ugly.isUgly(6));
        System.out.println(ugly.isUgly(14));
        System.out.println(ugly.nthUglyNumber(1000));
    }
}
