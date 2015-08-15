package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 12/6/14.
 */
public class CombinationSum {
    public static void combination(int[] num, int start, int target, ArrayList<Integer> curr,
                                   ArrayList<ArrayList<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<Integer>(curr));
            return;
        }
        if (target < 0 || start >= num.length) {
            return;
        }
        for (int i = start; i < num.length; i ++) {
            if (i > start && num[i] == num[i-1]) {  // handle duplicate elements
                continue;
            }
            curr.add(num[i]);
            combination(num, i + 1, target-num[i], curr, res);
            curr.remove(curr.size() - 1);
        }
    }

    public static ArrayList<ArrayList<Integer>> solve(int[] num, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        if (num == null || num.length == 0) {
            return res;
        }
        Arrays.sort(num);
        combination(num, 0, target, new ArrayList<Integer>(), res);
        return res;
    }

    public static void main(String[] args) {
        int[] num = {14,6,25,9,30,20,33,34,28,30,16,12,31,9,9,12,34,16,25,32,8,7,30,12,33,20,21,29,24,17,27,34,11,17,30,6,32,21,27,17,16,8,24,12,12,28,11,33,10,32,22,13,34,18,12};
        int target = 27;
        ArrayList<ArrayList<Integer>> res = solve(num, target);
        if (res != null) {
            for (List<Integer> x : res) {
                System.out.print("[");
                for (int i = 0; i < x.size(); i++) {
                    if (i < x.size() - 1) {
                        System.out.print(x.get(i) + ", ");
                    } else {
                        System.out.print(x.get(i));
                    }
                }
                System.out.println("]");
            }
        }
    }
}