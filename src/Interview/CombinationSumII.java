package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 10/9/15.
 * Given a collection of candidate numbers (C) and a target number (T),
 * find all unique combinations in C where the candidate numbers sums to T.
 *
 * Each number in C may only be used once in the combination.
 * Note:
 * 1. All numbers (including target) will be positive integers.
 * 2. Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
 * 3. The solution set must not contain duplicate combinations.
 * For example, given candidate set 10,1,2,7,6,1,5 and target 8,
 * A solution set is:
 * [1, 7]
 * [1, 2, 5]
 * [2, 6]
 * [1, 1, 6]
 */
public class CombinationSumII {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Set<List<Integer>>> res = new ArrayList<>();
        for (int i = 0; i <= target; i ++) {
            Set<List<Integer>> elem = new HashSet<>();
            res.add(elem);
        }
        int[] dp = new int[target+1];
        dp[0] = 1;
        res.get(0).add(new ArrayList<>());
        Arrays.sort(candidates);
        Set<Integer> parent = new HashSet<>();
        for (int c = 0; c < candidates.length && candidates[c] <= target; c ++) {
            parent.clear();
            for (int i = 0; i + candidates[c] <= target; i ++) {
                if (dp[i] > 0) {
                    parent.add(i);
                }
            }
            Map<Integer, Set<List<Integer>>> tmp = new HashMap<>();
            for (int p: parent) {
                tmp.put(p, new HashSet<>(res.get(p)));
            }
            for (int p: parent) {
                for (List<Integer> elem: tmp.get(p)) {
                    List<Integer> curr = new ArrayList<>(elem);
                    curr.add(candidates[c]);
                    if (res.get(p+candidates[c]).add(curr)) {
                        dp[p+candidates[c]] ++;
                    }
                }
            }
        }
        List<List<Integer>> result = new ArrayList<>();
        result.addAll(res.get(target));
        return result;
    }

    public static void main(String[] args) {
        int[] candidates = {4,4,2,1,4,2,2,1,3};
        int target = 6;
        List<List<Integer>> res = new CombinationSumII().combinationSum2(candidates, target);
        for (List<Integer> x: res) {
            System.out.println(Arrays.toString(x.toArray()));
        }
    }
}
