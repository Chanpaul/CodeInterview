package Interview;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

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
        int len = nums.length - k + 1;
        int[] res = new int[len];
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new maxComparator());
        for (int i = 0; i < k; i ++) {
            pq.add(nums[i]);
        }
        res[0] = pq.peek();
        for (int i = k; i < nums.length; i ++) {
            if (nums[i - k] != nums[i]) {
                pq.remove(nums[i - k]);
                pq.add(nums[i]);
            }
            res[i - k + 1] = pq.peek();
        }
        return res;
    }

    class maxComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer x, Integer y) {
            if (x == y) {
                return 0;
            }
            else if (x > y) {
                return -1;
            }
            else {
                return 1;
            }
        }
    }


    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 8;
        System.out.println(Arrays.toString(new SlidingWindowMax().maxSlidingWindow(nums, k)));
    }
}
