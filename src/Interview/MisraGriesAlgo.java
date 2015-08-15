package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 6/29/15.
 * Misra-Gries algo
 * Data stream algo and solve the problem of finding elements that
 * appear more than n / k times in the stream.
 */
public class MisraGriesAlgo {
    public static List<Integer> majorityElement(int[] nums, int k) {
        int size = k - 1;
        Map<Integer, Integer> valCntMap = new HashMap<>();
        for (int x : nums) {
            if (!valCntMap.isEmpty() && valCntMap.containsKey(x)) {
                valCntMap.put(x, valCntMap.get(x) + 1);
            }
            else if (valCntMap.isEmpty() || valCntMap.size() < size) {
                valCntMap.put(x, 1);
            }
            else {
                Set<Integer> keys = valCntMap.keySet();
                Set<Integer> removal = new HashSet<>();
                for (int key : keys) {
                    valCntMap.put(key, valCntMap.get(key) - 1);
                    if (valCntMap.get(key) == 0) {
                        removal.add(key);
                    }
                }
                for (int key : removal) {
                    valCntMap.remove(key);
                }
            }
        }
        List<Integer> majority = new ArrayList<>();
        for (int key : valCntMap.keySet()) {
            int cnt = 0;
            for (int x : nums) {
                if (x == key) {
                    cnt ++;
                }
            }
            if (cnt > nums.length / k) {
                majority.add(key);
            }
        }
        return majority;
    }
    public static void main(String[] args) {
        int[] nums = {1,1,2};
        List<Integer> majority = majorityElement(nums, 3);
        System.out.println(Arrays.toString(majority.toArray()));
    }
}
