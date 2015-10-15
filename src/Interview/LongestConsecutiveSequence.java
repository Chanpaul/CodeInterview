package Interview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yongyangyu on 10/15/15.
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 *
 * Your algorithm should run in O(n) complexity.
 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] nums) {
        Map<Integer, Boolean> used = new HashMap<>();
        for (int x: nums) {
            used.put(x, false);
        }
        int longest = 0;
        for (int x: nums) {
            if (used.get(x)) continue;
            int len = 1;
            used.put(x, true);
            for (int i = x+1; used.containsKey(i); i ++) {
                used.put(i, true);
                len ++;
            }
            for (int i = x-1; used.containsKey(i); i --) {
                used.put(i, true);
                len ++;
            }
            longest = Math.max(longest, len);
        }
        return longest;
    }
}
