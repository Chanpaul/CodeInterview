package Interview;

/**
 * Created by yongyangyu on 10/10/15.
 * Given n non-negative integers a1, a2, ..., an,
 * where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the two endpoints of
 * line i is at (i, ai) and (i, 0). Find two lines, which together
 * with x-axis forms a container, such that the container contains the most water.
 */
public class ContainerMostWater {
    public int maxArea(int[] height) {
        int lo = 0, hi = height.length - 1;
        int water = 0, curr = 0;
        while (lo < hi) {
            curr = (hi - lo) * (height[lo] <= height[hi] ? height[lo] : height[hi]);
            if (curr > water) {
                water = curr;
            }
            if (height[lo] > height[hi]) {
                hi --;
            }
            else {
                lo ++;
            }
        }
        return water;
    }
}
