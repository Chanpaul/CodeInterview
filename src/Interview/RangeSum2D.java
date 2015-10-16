package Interview;

/**
 * Created by yongyangyu on 10/15/15.
 * From Google interview.
 * Give an N-by-N board, support 2 operations:
 * void update(x, y, v)
 * int sum(x1, y1, x2, y2)
 */
public class RangeSum2D {
    private int[][] cells;
    private int[][] rowSum;
    public RangeSum2D(int n) {
        cells = new int[n][n];
        rowSum = new int[n][n];
    }

    public void update(int x, int y, int v) throws Exception{
        if (x < 0 || x >= cells.length) throw new IllegalArgumentException("x out of range");
        if (y < 0 || y >= cells.length) throw new IllegalArgumentException("y out of range");
        int diff = v - cells[x][y];
        cells[x][y] = v;
        for (int j = y; j < cells.length; j ++) {
            rowSum[x][j] += diff;
        }
        return;
    }

    public int sum(int x1, int y1, int x2, int y2) throws Exception {
        if (x1 < 0 || x1 >= cells.length) throw new IllegalArgumentException("x1 out of range");
        if (y1 < 0 || y1 >= cells.length) throw new IllegalArgumentException("y1 out of range");
        if (x2 < 0 || x2 >= cells.length) throw new IllegalArgumentException("x2 out of range");
        if (y2 < 0 || y2 >= cells.length) throw new IllegalArgumentException("y2 out of range");
        int res = 0;
        for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i ++) {
            // don't forget to add cell[i][min(y1, y2)]
            res += rowSum[i][Math.max(y1, y2)] - rowSum[i][Math.min(y1, y2)] + cells[i][Math.min(y1, y2)];
        }
        return res;
    }

    public static void main(String[] args) {
        RangeSum2D rs = new RangeSum2D(5);
        try {
            rs.update(0, 0, 1);
            rs.update(1, 1, 1);
            rs.update(2, 0, 2);
            System.out.println(rs.sum(0, 0, 2, 2));
            rs.update(1, 1, -1);
            System.out.println(rs.sum(0, 0, 2, 2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
