package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 7/2/15.
 * This source file contains methods of k-sum problems.
 */
public class KSum {
    /*
     * Given an array of integers, find two numbers such that they add up to a specific target number.

     * The function twoSum should return indices of the two numbers such that they add up to the target,
     * where index1 must be less than index2. Please note that your returned answers (both index1 and index2)
     * are not zero-based.

     * You may assume that each input would have exactly one solution.

     * Input: numbers={2, 7, 11, 15}, target=9
     * Output: index1=1, index2=2
     */
    public int[] twoSum(int[] numbers, int target)
    {
        int []a = new int[2];
        Map<Integer, Integer> map = new HashMap<>();
        for (int i=0; i<numbers.length; i++)
        {
            //put number into hash table (if it's not already there)
            if (!map.containsKey(numbers[i])) {
                map.put(numbers[i], i);
            }
            if (map.containsKey(target-numbers[i]) && map.get(target-numbers[i]) < i) {
                a[0] = map.get(target-numbers[i]) + 1;
                a[1] = i + 1;
                return a;
            }
        }
        return a;
    }
    /*
     * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0?
     * Find all unique triplets in the array which gives the sum of zero.
     * Note:
     * Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
     * The solution set must not contain duplicate triplets.
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        int i = 0;
        while (i < n - 2) {
            if (i > 0 && nums[i] == nums[i-1]) {
                i ++;
                continue;
            }
            int a = nums[i];
            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                int b = nums[l];
                int c = nums[r];
                if (a + b + c == 0) {
                    List<Integer> elem = new ArrayList<>();
                    elem.add(a); elem.add(b); elem.add(c);
                    result.add(elem);
                    l ++;
                    r --;
                    while (l < r && nums[l] == nums[l - 1]) {
                        l ++;
                    }
                    while(l < r && nums[r] == nums[r + 1]) {
                        r --;
                    }
                }
                else if (a + b + c > 0) {
                    r --;
                }
                else {
                    l ++;
                }
            }
            i ++;
        }
        return result;
    }
    /*
     *  Given an array S of n integers, are there elements a, b, c, and d in S such that
     *  a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

     *  Note:
     *  Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
     *  The solution set must not contain duplicate quadruplets.
     *  For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

     *  A solution set is:
     *    (-1,  0, 0, 1)
     *    (-2, -1, 1, 2)
     *    (-2,  0, 0, 2)
     */

    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        if (n < 4) {
            return new ArrayList<List<Integer>>();
        }
        Arrays.sort(nums);
        Map<Integer, List<int[]>> d = new HashMap<>();
        for (int i = 0; i < nums.length; i ++) {
            for (int j = i + 1; j < nums.length; j ++) {
                if (!d.containsKey(nums[i] + nums[j])) {
                    List<int[]> elem = new ArrayList<>();
                    int[] pair = new int[2];
                    pair[0] = i; pair[1] = j;
                    elem.add(pair);
                    d.put(nums[i]+nums[j], elem);
                }
                else {
                    int[] pair = new int[2];
                    pair[0] = i; pair[1] = j;
                    List<int[]> elem = d.get(nums[i] + nums[j]);
                    elem.add(pair);
                    d.put(nums[i]+nums[j], elem);
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        Set<Integer> deduplicate = new HashSet<>();
        for (int i = 0; i < nums.length; i ++) {
            for (int j = i + 1; j < nums.length; j ++) {
                int remain = target - nums[i] - nums[j];
                if (d.containsKey(remain)) {
                    List<int[]> elem = d.get(remain);
                    for (int[] pair: elem) {
                        if (pair[0] > j) {
                            /*int[] hashArray = new int[4];
                            hashArray[0] = nums[i];
                            hashArray[1] = nums[j];
                            hashArray[2] = nums[pair[0]];
                            hashArray[3] = nums[pair[1]]; */
                            int hash = 17;//Arrays.hashCode(hashArray);
                            hash = 31 * hash + nums[i];
                            hash = 31 * hash + nums[j];
                            hash = 31 * hash + nums[pair[0]];
                            hash = 31 * hash + nums[pair[1]];
                            if (!deduplicate.contains(hash)) {
                                deduplicate.add(hash);
                                List<Integer> quinTuple = new ArrayList<>();
                                quinTuple.add(nums[i]);
                                quinTuple.add(nums[j]);
                                quinTuple.add(nums[pair[0]]);
                                quinTuple.add(nums[pair[1]]);
                                result.add(quinTuple);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-5,-4,-3,-2,-1,0,0,1,2,3,4,5};
        int target = 0;
        new KSum().fourSum(nums, target);
    }
}
