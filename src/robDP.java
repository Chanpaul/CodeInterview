/**
 * Created by yongyangyu on 3/31/15.
 * cannot rob two adjacent cells from num
 * compute the max value can obtain from the array
 */
public class robDP {
    public static int rob(int[] num) {
        if (num == null || num.length == 0) {
            return 0;
        }
        int[] include = new int[num.length];
        int[] notInclude = new int[num.length];
        include[0] = num[0];
        for (int i = 1; i < num.length; i ++) {
            include[i] += notInclude[i-1] + num[i];
            notInclude[i] = Math.max(notInclude[i-1], include[i-1]);
        }
        return Math.max(include[num.length-1], notInclude[num.length-1]);
    }

    private static int rob(int[] num, int lo, int hi) {
        int include = 0, exclude = 0;
        for (int j = lo; j <= hi; j++) {
            int i = include, e = exclude;
            include = e + num[j];
            exclude = Math.max(e, i);
        }
        return Math.max(include, exclude);
    }

    public static int robCircle(int[] num) {
        if (num.length == 1) {
            return num[0];
        }
        return Math.max(rob(num, 0, num.length - 2), rob(num, 1, num.length-1));
    }

    public static void main(String[] args) {
        int num[] = {2,2,4,3,2,5};//{2,1,1,2};
        System.out.println(rob(num));
        System.out.println(robCircle(num));
    }
}
