package Interview;

/**
 * Created by yongyangyu on 2/16/16.
 * Given an unsorted array return whether an increasing subsequence of length 3
 * exists or not in the array.
 * Formally the function should:
 *   Return true if there exists i, j, k
 *   such that arr[i] < arr[j] < arr[k] given 0 ≤ i < j < k ≤ n-1 else return false.
 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * Examples:
 * Given [1, 2, 3, 4, 5],
 * return true.
 *
 * Given [5, 4, 3, 2, 1],
 * return false.
 */
public class IncreasingTriplet {
    public boolean increasingTriplet(int[] nums) {
        int min = Integer.MAX_VALUE;
        int larger = min;
        int prev = min;  // remembers previous larger relationship
        for (int x : nums) {
            if (x > larger && min != larger) return true;
            if (x < min) {
                if (larger > min) prev = larger; // keep track of 2 larger relationships
                min = x;
                larger = min;
            }
            else if (x > min) {
                larger = x;
            }
        }
        if (larger > prev) return true;
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1,0,2,0,0,-1,-1,3};
        IncreasingTriplet it = new IncreasingTriplet();
        System.out.println(it.increasingTriplet(nums));
    }
}
