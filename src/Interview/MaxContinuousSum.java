package Interview;

/**
 * Created by yongyangyu on 11/19/14.
 */
public class MaxContinuousSum {
    public static int getMaxSum(int[] data) {
        int rval = Integer.MIN_VALUE;
        if (data == null) {
            return -1;
        }
        if (data.length <= 1) {
            return data[0];
        }
        int curSum = data[0];
        for (int i = 1; i < data.length; i ++) {
            curSum = Math.max(curSum+data[i], data[i]);
            rval = Math.max(rval, curSum);
        }
        return rval;
    }

    public static void main(String[] args) {
        System.out.println(getMaxSum(new int[]{-2,11,-4,13,-5,-2})); // 20
        System.out.println(getMaxSum(new int[]{1,-3,4,-2,-1,6}));  // 7
    }
}
