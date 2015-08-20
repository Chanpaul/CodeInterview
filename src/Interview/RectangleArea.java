package Interview;

/**
 * Created by yongyangyu on 8/20/15.
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * Assume that the total area is never beyond the maximum possible value of int.
 * For more details, refer to https://leetcode.com/problems/rectangle-area/
 */
public class RectangleArea {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area = 0;
        area += (C - A) * (D - B);
        area += (G - E) * (H - F);
        int x_overlap = Math.max(0, Math.min(C, G) - Math.max(A, E));
        int y_overlap = Math.max(0, Math.min(D, H) - Math.max(B, F));
        return area - x_overlap * y_overlap;
    }
}
