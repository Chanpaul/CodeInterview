package Interview;

/**
 * Created by yongyangyu on 11/16/14.
 */
public class Pow {
    public static double power(double x, int p) {
        boolean sign = false;
        if (p == 0) {
            return 1.0;
        }
        if (p == 1) {
            return x;
        }
        if (p < 0) {
            sign = true;
        }
        if (p % 2 == 1) {
            double tmp = x * power(x, p-1);
            if (sign) {
                return 1.0 / tmp;
            }
            else {
                return tmp;
            }
        }
        else {
            double tmp = power(x, p / 2);
            tmp *= tmp;
            if (sign) {
                return 1.0 / tmp;
            } else {
                return tmp;
            }
        }
    }
    public static void main(String [] args) {
        double x = 2.0;
        for (int p = 0; p < 11; p ++) {
            System.out.println(Pow.power(x, p));
        }
    }
}
