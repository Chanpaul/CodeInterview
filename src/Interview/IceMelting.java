package Interview;

/**
 * Created by yongyangyu on 2/14/16.
 * A question from Google on campus interview.
 * Given an array of integers, one can assume each value represents the height of a ice cube.
 * The ice melting rate is proportional to the area exposed to the air.
 * For example,
 * heights = [4, 0, 6, 0, 3], then time = 1 since each cube needs a unit time to melt.
 * If heights = [7,6,5,4,3,2,1], then time = 4.
 * This can be computed such that each cube is associated with a time value
 * times = [1,2,3,4,3,2,1].
 * Give an O(n) algorithm that can compute the longest time for the ice to melt.
 */
public class IceMelting {
    public int time(int[] heights) {
        if (heights.length == 0) return 0;
        int[] dp = new int[heights.length];
        int largest = Integer.MIN_VALUE;
        dp[0] = dp[dp.length-1] = 1;
        for (int i = 1; i <= heights.length-2; i++) {
            dp[i] = Math.min(heights[i], dp[i-1]+1);
        }
        for (int i = heights.length-2; i > 0; i--) {
            dp[i] = Math.min(dp[i], dp[i+1]+1);
            largest = Math.max(largest, dp[i]);
        }
        return largest;
    }

    public static void main(String[] args) {
        int[] heights = {4, 0, 6, 0, 3};
        IceMelting im = new IceMelting();
        System.out.println(im.time(heights));
    }
}
