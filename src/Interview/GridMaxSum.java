package Interview;

/**
 * Created by yongyangyu on 11/6/15. (from Yelp)
 * Given a 2D grid with points in it, find the maximum points that the player can achieve.
 * Player can move only up,  up-left, up-right in the grid. Player always starts
 * from bottom-most row and middle column
 * points_grid = [
 *  [9, 7, 10, 100],
 *  [2, 7, 9, 1],
 *  [0, 6, 2, 3],
 *  [1, 0, 2, 0]
 * ]
 *
 * start_x = 3 # m - 1
 * start_y = 2 # [_ n / 2 _]
 *
 *  2 -> 6 -> 2 -> 9 = 19
 *  optimal: 2 -> 6 -> 9 -> 100  = 117
 */
public class GridMaxSum {
    public int maxPoints(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] keep = new int[m][n];
        for (int j = 0; j < n; j ++) // init last row
            keep[m-1][j] = -1;
        keep[m-1][n/2] = grid[m-1][n/2]; // starting point
        for (int i = m-2; i >= 0; i --) {
            for (int j = 0; j < n; j ++) {
                // keep[i][j] <- keep[i+1][j], keep[i+1][j-1], keep[i+1][j+1]
                if (j - 1 >= 0 && keep[i+1][j-1] >= 0)
                    keep[i][j] = Math.max(keep[i][j], grid[i][j] + keep[i+1][j-1]);
                if (j + 1 < n && keep[i+1][j+1] >= 0)
                    keep[i][j] = Math.max(keep[i][j], grid[i][j] + keep[i+1][j+1]);
                if (keep[i+1][j] >= 0)
                    keep[i][j] = Math.max(keep[i][j], grid[i][j] + keep[i+1][j]);
                if (keep[i][j] == 0)
                    keep[i][j] = -1;
            }
        }
        int res = Integer.MIN_VALUE;
        for (int j = 0; j < n; j ++) {
            res = Math.max(res, keep[0][j]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] grid = {{9, 7, 10, 100}, {2, 7, 9, 1}, {0, 6, 2, 3}, {1, 0, 2, 0}};
        GridMaxSum gms = new GridMaxSum();
        System.out.println(gms.maxPoints(grid));
    }
}
