package Interview;

/**
 * Created by yongyangyu on 3/16/16.
 * After robbing those houses on that street, the thief has found himself a new place for his
 * thievery so that he will not get too much attention. This time, all houses at this place are
 * arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile,
 * the security system for these houses remain the same as for those in the previous street.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine
 * the maximum amount of money you can rob tonight without alerting the police.
 */
public class HouseRobberII {
    private int rob(int[] num, int lo, int hi) {
        int include = 0, exclude = 0;
        for (int j = lo; j <= hi; j++) {
            int i = include, e = exclude;
            include = e + num[j];
            exclude = Math.max(e, i);
        }
        return Math.max(include, exclude);
    }
    public int rob(int[] num) {
        if (num.length == 1) {
            return num[0];
        }
        return Math.max(rob(num, 0, num.length - 2), rob(num, 1, num.length-1));
    }
}
