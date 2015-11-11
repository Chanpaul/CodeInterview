package Interview;

import java.util.List;

/**
 * Created by yongyangyu on 11/11/15.
 * Implement an iterator to flatten a 2d vector.
 * For example,
 * Given 2d vector =
 *  [
 *    [1,2],
 *    [3],
 *    [4,5,6]
 *  ]
 * By calling next repeatedly until hasNext returns false,
 * the order of elements returned by next should be: [1,2,3,4,5,6].
 * -- What if the 2d vector contains empty arrays, e.g. [ ], [ ], 1 2 3 ?
 * In this case, the next() should not output anything, but the return type is int.
 * There the hasNext() should be more complicated in which it handles this situation.
 * -- What if the 2d vector itself is empty? Again, handle it in hasNext()
 */
public class Flatten2DVector {
    private List<List<Integer>> vec2d;
    private int row;
    private int col;
    private final int nrows;

    public Flatten2DVector(List<List<Integer>> vec2d) {
        this.vec2d = vec2d;
        row = 0;
        col = 0;
        nrows = vec2d.size();
    }

    public int next() {
        int res = 0;
        if (col < vec2d.get(row).size()) {
            res = vec2d.get(row).get(col);
        }
        col ++;
        if (col == vec2d.get(row).size()) {
            col = 0;
            row ++;
        }
        return res;
    }

    public boolean hasNext() {
        while (row < nrows &&
                (vec2d.get(row) == null || vec2d.get(row).isEmpty())) {
            row ++;
        }
        return vec2d != null &&
                !vec2d.isEmpty() && row < nrows;
    }
}
