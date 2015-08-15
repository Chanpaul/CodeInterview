package Interview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class ContainsDup {
    /**
     * Created by yongyangyu on 5/31/15.
     * Given an array of integers and an integer k, find out whether there there are two
     * distinct indices i and j in the array such that nums[i] = nums[j] and the difference
     * between i and j is at most k.
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, List<Integer>> indPos = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < nums.length; i ++) {
            if (!indPos.containsKey(nums[i])) {
                List<Integer> indList = new LinkedList<Integer>();
                indList.add(i);
                indPos.put(nums[i], indList);
            }
            else {
                for (int pos : indPos.get(nums[i])) {
                    if (Math.abs(i - pos) <= k) {
                        return true;
                    }
                }
                List<Integer> indList = indPos.get(nums[i]);
                indList.add(i);
                indPos.put(nums[i], indList);
            }
        }
        return false;
    }

    /**
     * Given an array of integers, find out whether there are two distinct indices i and j
     * in the array such that the difference between nums[i] and nums[j] is at most t and
     * the difference between i and j is at most k.
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k < 1 || t < 0) return false;
        Map<Long, Long> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            long remappedNum = (long) nums[i] - Integer.MIN_VALUE;
            long bucket = remappedNum / ((long) t + 1); // avoid t == 0
            if (map.containsKey(bucket)
                    || (map.containsKey(bucket - 1) && remappedNum - map.get(bucket - 1) <= t)
                    || (map.containsKey(bucket + 1) && map.get(bucket + 1) - remappedNum <= t))
                return true;
            if (map.entrySet().size() >= k) {
                long lastBucket = ((long) nums[i - k] - Integer.MIN_VALUE) / ((long) t + 1);
                map.remove(lastBucket);
            }
            map.put(bucket, remappedNum);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {0,2147483647};
        System.out.println(new ContainsDup().containsNearbyAlmostDuplicate(nums, 1, 2147483647));
    }
}
