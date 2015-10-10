package Interview;

import java.util.List;

/**
 * Created by yongyangyu on 10/10/15.
 * Given a triangle, find the minimum path sum from top to bottom.
 * Each step you may move to adjacent numbers on the row below.
 *
 * For example, given the following triangle
 * [
 *      [2],
 *     [3,4],
 *    [6,5,7],
 *   [4,1,8,3]
 * ]
 * The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 */
public class TriangleMinPath {
    public int minmumTotal(List<List<Integer>> triangle) {
        int[] upper = new int[1];
        int[] below;
        upper[0] = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); i ++) {
            List<Integer> curr = triangle.get(i);
            below = new int[curr.size()];
            for (int k = 0 ; k < below.length; k ++) {
                below[k] = Integer.MAX_VALUE;
            }
            for (int j = 0; j < upper.length; j ++) {
                below[j] = Math.min(below[j], curr.get(j) + upper[j]);
                below[j+1] = Math.min(below[j+1], curr.get(j+1) + upper[j]);
            }
            upper = below;
        }
        int res = Integer.MAX_VALUE;
        for (int x: upper) {
            res = Math.min(res, x);
        }
        return res;
    }
}
