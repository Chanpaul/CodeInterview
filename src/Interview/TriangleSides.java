package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 11/12/15.
 * Three segments of lengths A, B, C form a triangle iff
 *
 *      A + B > C
 *      B + C > A
 *      A + C > B
 *
 *  e.g.
 *  6, 4, 5 can form a triangle
 *  10, 2, 7 can't
 *
 * Given a list of segments lengths algorithm should find at least one triplet of segments that form a triangle (if any).
 *
 * Method should return an array of either:
 * - 3 elements: segments that form a triangle (i.e. satisfy the condition above)
 * - empty array if there are no such segments
 */
public class TriangleSides {
    public int[] getTriangleSides(int[] segments) {
        int[] res = new int[0];
        int n = segments.length;
        if (segments == null || n < 3) return res;
        Arrays.sort(segments);
        for (int i = 0; i < n-2; i ++) {
            int j = n - 1;
            while (i + 1 < j) {
                if (segments[i] + segments[j - 1] <= segments[j]) j--;
                else {
                    res = new int[]{segments[i], segments[j - 1], segments[j]};
                    break;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] segements = {10,2,7};
        TriangleSides ts = new TriangleSides();
        System.out.println(Arrays.toString(ts.getTriangleSides(segements)));
    }
}
