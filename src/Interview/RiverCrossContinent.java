package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 2/15/16.
 * Given an n-by-n matrix A, where each point in A represents the height of the point.
 * Upper half borders of the matrix is the coastlines facing Pacific and lower half borders
 * is the coastlines face Atlantic. A river can be formed from a higher point to adjacent
 * lower points. Find the regions that covered by the rivers that across the two oceans.
 * Note: two corner points A[n-1][0] and A[0][n-1] are always valid points.
 * This can be further extended to the fact that all the 4 borders of the matrix are valid.
 *
 * For example, A is given as the following 2-d array,
 * [ [10, 7, 18, 6],
 *   [7, 20, 4, 9],
 *   [3, 21, 12, 20],
 *   [2, 15, 10, 17]
 * ]
 */
public class RiverCrossContinent {
    char[][] region(int[][] points) {
        int n = points.length;
        char[][] area = new char[n][n];
        // assign 'X' to all the 4 borders
        for (int j = 0; j < n; j ++) {
            area[0][j] = area[n-1][j] = 'X';
        }
        for (int i = 0; i < n; i ++) {
            area[i][0] = area[i][n-1] = 'X';
        }
        // search from upper half to label each point with 'P'
        for (int i = 1; i < n-1; i ++) {
            for (int j = 1; j < n-1; j ++) {
                if ((points[i][j] >= points[i-1][j] && (area[i-1][j] == 'X' || area[i-1][j] == 'P')) ||
                        (points[i][j] >= points[i][j-1] && (area[i][j-1] == 'X' || area[i][j-1] == 'P'))) {
                    area[i][j] = 'P';
                }
            }
        }
        // search from lower half to label each point with 'A'
        for (int i = n-2; i >= 1; i --) {
            for (int j = n-2; j >=1; j --) {
                if ((points[i][j] >= points[i][j+1] && (area[i][j+1] == 'X' || area[i][j+1] == 'A')) ||
                        (points[i][j] >= points[i+1][j] && (area[i+1][j] == 'X' || area[i+1][j] == 'A'))) {
                    if (area[i][j] == 'P') {
                        area[i][j] = 'X';
                    }
                    else {
                        area[i][j] = 'A';
                    }
                }
            }
        }
        // search again to check if 'P' or 'A' can be extended
        for (int i = 1; i < n-1; i ++) {
            for (int j = 1; j < n-1; j ++) {
                if (area[i][j] == 'P') {
                    if ((points[i+1][j] >= points[i][j] && (area[i+1][j] == 'X' || area[i+1][j] == 'A')) ||
                        (points[i][j+1] >= points[i][j] && (area[i][j+1] == 'X' || area[i][j+1] == 'A'))) {
                        area[i][j] = 'X';
                    }
                }
                else if (area[i][j] == 'A') {
                    if ((points[i-1][j] >= points[i][j] && (area[i-1][j] == 'X' || area[i-1][j] == 'P')) ||
                            (points[i][j-1] >= points[i][j] && (area[i][j-1] == 'X' || area[i][j-1] == 'P'))) {
                        area[i][j] = 'X';
                    }
                }
            }
        }
        return area;
    }

    public static void main(String[] args) {
        int[][] points = {{10, 7, 18, 6}, {7, 20, 4, 9}, {3, 21, 12, 20}, {2, 15, 10, 17}};
        RiverCrossContinent rcc = new RiverCrossContinent();
        char[][] area = rcc.region(points);
        for (char[] row: area) {
            System.out.println(Arrays.toString(row));
        }
    }
}
