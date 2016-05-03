package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 5/3/16.
 * Given a non-empty array of integers, return the k most frequent elements.
 * For example,
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
public class TopKFrequentElements {
    public List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> freq = new HashMap<>();
        for (int x: nums) {
            if (freq.containsKey(x)) {
                freq.put(x, freq.get(x) + 1);
            }
            else {
                freq.put(x, 1);
            }
        }
        Comparator<int[]> comp = (a, b) -> {
            if (a[1] == b[1]) return 0;
            else if (a[1] > b[1]) return -1;
            else return 1;
        };
        PriorityQueue<int[]> q = new PriorityQueue<>(k, comp);
        for (int key : freq.keySet()) {
            q.add(new int[]{key, freq.get(key)});
        }
        for (int i = 0; i < k; i ++) {
            res.add(q.poll()[0]);
        }
        return res;
    }
}
