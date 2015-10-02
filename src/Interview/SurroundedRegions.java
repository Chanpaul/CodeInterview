package Interview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yongyangyu on 10/2/15.
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.
 *
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 *
 * For example,
 *   X X X X
 *   X O O X
 *   X X O X
 *   X O X X
 * After running your function, the board should be:
 *
 *   X X X X
 *   X X X X
 *   X X X X
 *   X O X X
 *
 * Find any 'O' element on the boundary. If no such elements exist, just fill all the
 * 'O' with 'X'. Otherwise, start from all these elements and perform BFS for the 'O's.
 */
public class SurroundedRegions {
    private class Tuple {
        final private int v1;
        final private int v2;
        Tuple (int v1, int v2) {
            this.v1 = v1;
            this.v2 = v2;
        }

        @Override public int hashCode() {
            return Arrays.hashCode(new int[]{v1, v2});
        }

        @Override public boolean equals(Object other) {
            if (other instanceof Tuple) {
                return v1 == ((Tuple) other).v1 &&
                        v2 == ((Tuple) other).v2;
            }
            else
                return false;
        }
    }

    public void fill(char[][] board) {
        Set<Tuple> keep = new HashSet<>();
        if (board.length < 1) return;
        int m = board.length, n = board[0].length;
        for (int j = 0; j < n; j ++) {
            // top row
            if (board[0][j] == 'O')
                keep.add(new Tuple(0, j));
            // bottom row
            if (board[m - 1][j] == 'O')
                keep.add(new Tuple(m - 1, j));
        }
        for (int i = 0; i < m; i ++) {
            // leftmost col
            if (board[i][0] == 'O')
                keep.add(new Tuple(i, 0));
            // rightmost col
            if (board[i][n - 1] == 'O')
                keep.add(new Tuple(i, n - 1));
        }
        bfs(keep, board);
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n;j ++) {
                if (board[i][j] == 'O' && !keep.contains(new Tuple(i, j)))
                    board[i][j] = 'X';
            }
        }
        return;
    }

    private void bfs(Set<Tuple> keep, char[][] board) {
        Set<Tuple> check = new HashSet<>(keep);
        int m = board.length, n = board[0].length;
        // bfs until check is empty
        while (!check.isEmpty()) {
            Tuple currPos = check.iterator().next();
            check.remove(currPos);
            // check for the 4 possible directions
            int i = currPos.v1 - 1, j = currPos.v2;
            if (i >= 0 && board[i][j] == 'O') {
                if (!keep.contains(new Tuple(i, j))) {
                    check.add(new Tuple(i, j));
                    keep.add(new Tuple(i, j));
                }
            }
            i = currPos.v1 + 1;
            if (i < m && board[i][j] == 'O') {
                if (!keep.contains(new Tuple(i, j))) {
                    check.add(new Tuple(i, j));
                    keep.add(new Tuple(i, j));
                }
            }
            i = currPos.v1; j = currPos.v2 - 1;
            if (j >= 0 && board[i][j] == 'O') {
                if (!keep.contains(new Tuple(i, j))) {
                    check.add(new Tuple(i, j));
                    keep.add(new Tuple(i, j));
                }
            }
            j = currPos.v2 + 1;
            if (j < n && board[i][j] == 'O') {
                if (!keep.contains(new Tuple(i, j))) {
                    check.add(new Tuple(i, j));
                    keep.add(new Tuple(i, j));
                }
            }
        }
    }

    public static void main(String[] args) {
        char[][] board = {{'X', 'X', 'X', 'X'},
                          {'X', 'O', 'O', 'X'},
                          {'X', 'X', 'O', 'X'},
                          {'X', 'O', 'X', 'X'}};
        new SurroundedRegions().fill(board);
        for (char[] arr: board) {
            System.out.println(Arrays.toString(arr));
        }
    }
}
