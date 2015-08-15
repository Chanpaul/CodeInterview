package Interview;

/**
 * Created by yongyangyu on 1/11/15.
 */
public class NumberFactor {
    public static void PrintFactors(int num) {
        if (num < 0) {
            return;
        }
        System.out.println(num + " * 1");
        printHelper("", num, num);
    }

    private static void printHelper(String currExpr, int dividend, int preFactor) {
        for (int factor = dividend-1; factor >= 2; factor --) {
            if (dividend % factor == 0 && factor <= preFactor) {
                int nextFactor = dividend / factor;
                if (nextFactor <= preFactor && nextFactor <= factor) {
                    System.out.println(currExpr + factor + " * " + nextFactor);
                }
                printHelper(currExpr + factor + " * ", nextFactor, factor);
            }
        }
    }

    public static void main(String[] args) {
        PrintFactors(64);
    }
}
