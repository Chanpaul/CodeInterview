package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 10/8/15.
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * For example,
 * If n = 4 and k = 2, a solution is:
 *
 *  [
 *    [2,4],
 *    [3,4],
 *    [2,3],
 *    [1,2],
 *    [1,3],
 *    [1,4],
 *  ]
 */
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> set = new ArrayList<>();
        for (int i = 1; i <= n; i ++) {
            set.add(i);
        }
        List<List<Integer>> res= combination(Math.min(k, n-k), set);
        if (k == n) {
            res.add(set);
            return res;
        }
        if (k <= n - k) return res;
        else {
            List<List<Integer>> tmp = new ArrayList<>();
            for (List<Integer> elem: res) {
                List<Integer> set2 = new ArrayList<>(set);
                set2.removeAll(elem);
                tmp.add(set2);
            }
            return tmp;
        }
    }

    private List<List<Integer>> combination(int k, List<Integer> set) {
        List<List<Integer>> res = new ArrayList<>();
        if (k == 0) return res;
        else if (k == 1) {
            for (int x: set) {
                List<Integer> elem = new ArrayList<>();
                elem.add(x);
                res.add(elem);
            }
            return res;
        }
        else {
            Set<List<Integer>> sortRes = new HashSet<>();
            for (int i = 0; i < set.size(); i ++) {
                int tmp = set.remove(i);
                List<List<Integer>> part = combination(k-1, set);
                for(List<Integer> elem: part) {
                    elem.add(tmp);
                    Collections.sort(elem);
                    sortRes.add(elem);
                }
                set.add(i, tmp);
            }
            res.addAll(sortRes);
            return res;
        }
    }

    public static void main(String[] args) {
        List<List<Integer>> res = new Combinations().combine(13, 13);
        for (List<Integer> elem: res) {
            System.out.println(Arrays.toString(elem.toArray()));
        }
    }
}
