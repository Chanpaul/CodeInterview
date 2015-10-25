package Interview;

/**
 * Created by yongyangyu on 9/11/15.
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.
 *
 * Each element in the array represents your maximum jump length at that position.
 *
 * Determine if you are able to reach the last index.
 *
 * For example:
 * A = [2,3,1,1,4], return true.
 *
 * A = [3,2,1,0,4], return false.
 */
public class JumpGame {
    public boolean canJump(int[] nums) {
        int maxDist = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (i <= maxDist) {
                maxDist = Math.max(maxDist, nums[i] + i);
            }
        }
        return maxDist >= nums.length - 1;
    }

    // reach the last index in the minimum number of jumps
    public int jump(int[] nums) {
        int steps = 0;
        int maxDist = 0;
        int last = 0;
        for (int i = 0; i < nums.length; i ++) {
            if (i > last) {
                last = maxDist;
                steps ++;
            }
            maxDist = Math.max(maxDist, nums[i] + i);
        }
        return steps;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1,0,4};
        JumpGame game = new JumpGame();
        System.out.println(game.canJump(nums));
        int[] num2 = {2,3,1,1,4}; // 2
        System.out.println(game.jump(num2));
    }
}
