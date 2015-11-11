package Interview;

/**
 * Created by yongyangyu on 11/11/15.
 * There is a fence with n posts, each post can be painted with one of the k colors.
 *
 * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 *
 * Return the total number of ways you can paint the fence.
 *
 * Note:
 * n and k are non-negative integers.
 * dp1[i] means #soln when the color of last 2 fences are the same
 * dp2[i] means #soln when the colof of last 2 fences are different
 * dp1[i] = dp2[i-1]
 * dp2[i] = (k-1)(dp2[i-1] + dp1[i-1]) = (k-1)(dp2[i-1] + dp2[i-2])
 */
public class PaintFences {
    public int numWays(int n, int k) {
        if (n < 2 && k != 0) return n*k;
        int s = k, d1 = k, d2 = k*(k-1);
        for (int i = 2; i < n; i ++) {
            s = d2;
            d2 = (k-1) * (d1 + d2);
            d1 = s;
        }
        return s + d2;
    }
}
