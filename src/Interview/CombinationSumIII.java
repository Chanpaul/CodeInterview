package Interview;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
/**
 * Created by yongyangyu on 5/26/15.
 * Find all possible combinations of k numbers that add up to a number n, 
 * given that only numbers from 1 to 9 can be used and each combination should be 
 * a unique set of numbers.
 * Ensure that numbers within the set are sorted in ascending order.
 * Example 1:
 *  Input: k = 3, n = 7
 *  Output:
 *      [[1,2,4]]
 * Example 2:
 *  Input: k = 3, n = 9
 *  Output:
 *      [[1,2,6], [1,3,5], [2,3,4]]
 */
public class CombinationSumIII {
    public static List<List<Integer>> combinationSum3(int k, int n) {
        return combine(k, n, 1);
    }

    private static List<List<Integer>> combine(int num, int target, int low) {
        if (num == 1) {
            if (target >= low && target <= 9) {
                List<Integer> elem = new LinkedList<>();
                elem.add(target);
                List<List<Integer>> res = new LinkedList<>();
                res.add(elem);
                return res;
            }
            else {
                return new LinkedList<>();
            }
        }
        List<List<Integer>> rval = new LinkedList<>();
        for (int i = low; i <= target - num; i ++) {
            List<List<Integer>> partial = combine(num - 1, target - i, i + 1);
            if (partial.size() > 0) {
                for (List<Integer> elem : partial) {
                    elem.add(0, i);
                    rval.add(elem);
                }
            }
        }
        return rval;
    }

    public static void main(String[] args) {
        List<List<Integer>> rval = combinationSum3(2, 18);
        for (List<Integer> elem : rval) {
            System.out.println(Arrays.toString(elem.toArray()));
        }
    }
}
