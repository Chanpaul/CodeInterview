package Interview;

/**
 * Created by yongyangyu on 10/10/15.
 * Given n non-negative integers representing an elevation map
 * where the width of each bar is 1, compute how much water it is able to trap after raining.
 *
 * For example,
 * Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.
 */
public class TrappingRainWater {
    public int trap(int[] height) {
        if (height.length < 2) return 0;
        // 1st scan to compute lmaxh array from left to right
        int[] lmaxh = new int[height.length];
        int maxh = height[0];
        for (int i = 1; i < lmaxh.length; i ++) {
            lmaxh[i] = maxh;
            if (height[i] > maxh)
                maxh = height[i];
        }
        // 2nd scan to find rmaxh from right to left
        int rain = 0;
        maxh = height[height.length - 1];
        for (int i = height.length - 2; i >=0; i--) {
            int left = lmaxh[i];
            int right = maxh;
            int min = Math.min(left, right);
            if (min > height[i])
                rain += min - height[i];
            if (height[i] > maxh)
                maxh = height[i];
        }
        return rain;
    }
}
