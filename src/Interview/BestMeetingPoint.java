package Interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by yongyangyu on 11/11/15.
 * A group of two or more people wants to meet and minimize the total travel distance.
 * You are given a 2D grid of values 0 or 1, where each 1 marks the home of someone in the group.
 * The distance is calculated using Manhattan Distance,
 * where distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|.
 *
 * For example, given three people living at (0,0), (0,4), and (2,2):
 *
 *  1 - 0 - 0 - 0 - 1
 *  |   |   |   |   |
 *  0 - 0 - 0 - 0 - 0
 *  |   |   |   |   |
 *  0 - 0 - 1 - 0 - 0
 *
 * The point (0,2) is an ideal meeting point, as the total travel distance of 2+2+2=6 is minimal.
 * So return 6.
 *
 * Hint:
 *
 * Try to solve it in one dimension first. How can this solution apply to the two dimension case?
 * For the 1d case, the best meeting point is just the median point.
 */
public class BestMeetingPoint {
    public int minDistance(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        List<Integer> row = new ArrayList<>();
        List<Integer> col = new ArrayList<>();
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (grid[i][j] == 1) {
                    row.add(i);
                    col.add(j);
                }
            }
        }
        Collections.sort(row);
        Collections.sort(col);
        int rid = row.get(row.size() / 2);
        int cid = col.get(col.size() / 2);
        int dist = 0;
        for (int x: row) {
            dist += Math.abs(rid - x);
        }
        for (int y: col) {
            dist += Math.abs(cid - y);
        }
        return dist;
    }

    public static void main(String[] args) {
        int[][] grid = new int[3][5];
        grid[0][0] = 1; grid[0][4] = 1; grid[2][2] = 1;
        BestMeetingPoint bmp = new BestMeetingPoint();
        System.out.println(bmp.minDistance(grid));
    }
}
