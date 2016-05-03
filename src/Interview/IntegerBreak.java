package Interview;

/**
 * Created by yongyangyu on 5/3/16.
 * Given a positive integer n, break it into the sum of at least two positive integers and
 * maximize the product of those integers. Return the maximum product you can get.
 *
 * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 *
 * Hint: remember the relationship between arithmetic mean and geometric mean, i.e.,
 * (x1 + x2 + ... + xn) >= sqrt(x1*x2*...*xn, n), the equality only only when
 * x1 = x2 = ... = xn
 */
public class IntegerBreak {
    public int integerBreak(int n) {
        int max = 0;
        for (int div = 2; div <= n; div ++) {
            if (n % div == 0) {
                int mean = n / div;
                int tmp = 1;
                for (int i = 0; i < div; i ++) {
                    tmp *= mean;
                }
                max = Math.max(max, tmp);
            }
            else {
                // test for 2 cases:
                // I: mean = n / div, or II: mean = div / n + 1
                int mean1 = n / div;
                int mean2 = (mean1 + 1) * (div - 1) <= n ? (mean1 + 1) : 1;
                int tmp1 = 1, tmp2 = 1;
                for (int i = 0; i < div-1; i ++) {
                    tmp1 *= mean1;
                    tmp2 *= mean2;
                }
                tmp1 *= n - mean1 * (div - 1);
                tmp2 *= n - mean2 * (div - 1);
                max = Math.max(max, tmp1);
                max = Math.max(max, tmp2);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int n = 43;
        IntegerBreak ib = new IntegerBreak();
        System.out.println(ib.integerBreak(n));
    }
}
