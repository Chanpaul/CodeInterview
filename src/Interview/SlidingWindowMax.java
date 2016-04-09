package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 7/18/15.
 * Given an array nums, there is a sliding window of size k which is moving from the
 * very left of the array to the very right. You can only see the k numbers in the window.
 * Each time the sliding window moves right by one position.
 *
 * For example,
 * Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 * Window position                Max
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 * Therefore, return the max sliding window as [3,3,5,5,6,7].
 */
public class SlidingWindowMax {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        // q stores the index of the largest element in current window
        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < k; i ++) {
            while (!q.isEmpty() && nums[i] >= nums[q.getLast()]) {
                q.pollLast();
            }
            q.add(i);
        }
        int[] res = new int[nums.length - k + 1];
        for (int i = k; i < nums.length; i ++) {
            res[i-k] = nums[q.getFirst()];
            // remove any index smaller than current element from the window
            while (!q.isEmpty() && nums[i] >= nums[q.getLast()]) {
                q.pollLast();
            }
            // remove any invalid index, i.e., index smaller than left boundary
            while (!q.isEmpty() && q.getFirst() <= i - k) {
                q.pollFirst();
            }
            // each element is added to q once
            q.add(i);
        }
        res[nums.length-k] = nums[q.getFirst()];
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        System.out.println(Arrays.toString(new SlidingWindowMax().maxSlidingWindow(nums, k)));
    }
}
