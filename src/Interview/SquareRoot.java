package Interview;

/**
 * Created by yongyangyu on 3/16/15.
 *
 * double sqrt(double x) implementation for Newton's method and bisection method.
 */
public class SquareRoot {
    public final static double EPS = 1e-4;
    public static double sqrt_newton(double a) {
        double x1 = a;
        double x2 = x1 / 2.0 + a / 2.0 / x1;
        while (Math.abs(x2 - x1) > EPS) {
            x1 = x2;
            x2 = x1 / 2.0 + a / 2.0 / x1;
        }
        return x2;
    }

    public static double sqrt_bsearch(double a) {
        double low = 0.0;
        double high = a;
        if (a < 1.0) {
            high = 1.0;
        }
        double mid = low + (high - low) / 2.0;
        while (Math.abs(mid * mid - a) > EPS) {
            if (mid * mid > a) {
                high = mid;
            }
            else {
                low = mid;
            }
            mid = low + (high - low) / 2.0;
        }
        return mid;
    }

    public static void main(String[] args) {
        System.out.println("sqrt(2.0) newton = " + sqrt_newton(2.0));
        System.out.println("sqrt(2.0) bsearch = " + sqrt_bsearch(2.0));
        System.out.println("sqrt(10.0) newton = " + sqrt_newton(10.0));
        System.out.println("sqrt(10.0) bsearch = " + sqrt_bsearch(10.0));
        System.out.println("sqrt(0.5) newton = " + sqrt_newton(0.5));
        System.out.println("sqrt(0.5 bsearch = " + sqrt_bsearch(0.5));
    }
}
