package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 12/6/14.
 * Given a set of candidate numbers (C) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.
 *
 * The same repeated number may be chosen from C unlimited number of times.
 * Note:
 * All numbers (including target) will be positive integers.
 * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 * The solution set must not contain duplicate combinations.
 * For example, given candidate set 2,3,6,7 and target 7,
 * A solution set is:
 * [7]
 * [2, 2, 3]
 */
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<List<Integer>>> res = new ArrayList<>();
        for (int i = 0; i <= target; i ++) {
            List<List<Integer>> elem = new ArrayList<>();
            res.add(elem);
        }
        int[] dp = new int[target+1];
        dp[0] = 1;
        Arrays.sort(candidates);
        for (int c = 0; c < candidates.length; c ++) {
            if (c > 0 && candidates[c] == candidates[c-1]) continue;
            for (int i = candidates[c]; i <= target; i ++) {
                if (i-candidates[c] == 0) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(candidates[c]);
                    res.get(i).add(tmp);
                }
                else if (dp[i-candidates[c]] > 0) {
                    List<List<Integer>> curr = res.get(i-candidates[c]);
                    for (List<Integer> x: curr) {
                        List<Integer> y = new ArrayList<>(x);
                        y.add(candidates[c]);
                        res.get(i).add(y);
                    }
                }
                dp[i] += dp[i-candidates[c]];
            }
        }
        return res.get(target);
    }


    public static void main(String[] args) {
        int[] num = {14,6,25,9,30,20,33,34,28,30,16,12,31,9,9,12,
                     34,16,25,32,8,7,30,12,33,20,21,29,24,17,27,34,11,
                     17,30,6,32,21,27,17,16,8,24,12,12,28,11,33,10,32,
                     22,13,34,18,12};
        int target = 27;
        List<List<Integer>> res = new CombinationSum().combinationSum(num, target);
        if (res != null) {
            for (List<Integer> x : res) {
                System.out.println(Arrays.toString(x.toArray()));
            }
        }
    }
}