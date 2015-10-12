package Interview;

import java.util.Stack;

/**
 * Created by yongyangyu on 10/11/15.
 * Given n non-negative integers representing the histogram's bar height
 * where the width of each bar is 1, find the area of largest rectangle in the histogram.
 * For example,
 * Given height = [2,1,5,6,2,3],
 * return 10.
 */
public class LargestRectangleInHistogram {
    public int largesRectangletArea(int[] height) {
        Stack<Integer> s = new Stack<>();
        int area = 0;
        for (int i = 0; i <= height.length; i ++) {
            while (!s.isEmpty() && (i == height.length || height[i] < height[s.peek()])) {
                int h = height[s.pop()];
                area = Math.max(area, h * (s.isEmpty() ? i : i - s.peek() - 1));
            }
            s.push(i);
        }
        return area;
    }

    // This problem can be viewed as an extension to the previous problem.
    // Given a 2D binary matrix with 0's and 1's, find the largest rectangle containing
    // all ones and return its area.
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int area = 0;
        for (int i = 0; i < m; i ++) {
            int[] height = new int[n];
            for (int j = 0; j < n; j ++) {
                for (int k = i; k >= 0; k --) {
                    if (matrix[k][j] == '1') {
                        height[j] ++;
                    }
                    else {
                        break;
                    }
                }
            }
            area = Math.max(area, largesRectangletArea(height));
        }
        return area;
    }
}
