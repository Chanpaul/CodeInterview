package Interview;

/**
 * Created by yongyangyu on 6/7/15.
 * Rectangles are defined by bottom left corner and top right corner
 * Rectangle X is defined as (A, B) and (C, D);
 * Rectangle Y is defined as (E, F) and (G, H).
 */
public class OverlappingRectangle {
    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area = 0;
        area += (C - A) * (D - B);
        area += (G - E) * (H - F);
        int x_overlap = Math.max(0, Math.min(C, G) - Math.max(A, E));
        int y_overlap = Math.max(0, Math.min(D, H) - Math.max(B, F));
        return area - x_overlap * y_overlap;
    }

    public static void main(String[] args) {
        System.out.println(computeArea(-2, -2, 2, 2, -3, -3, 3, -1));
    }
}
