package Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yongyangyu on 10/8/15.
 * The n-queens puzzle is the problem of placing n queens on an n×n
 * chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 *
 *  [
 *    [".Q..",  // Solution 1
 *    "...Q",
 *    "Q...",
 *    "..Q."],
 *
 *    ["..Q.",  // Solution 2
 *    "Q...",
 *    "...Q",
 *    ".Q.."]
 *  ]
 */
public class NQueen {
    public List<List<String>> solveNQueens(int n) {
        int[] cols = new int[n];
        List<List<String>> res = new ArrayList<>();
        queens(n, 0, cols, res);
        return res;
    }

    private void queens(int n, int row, int[] cols, List<List<String>> res) {
        if (row == n) {
            char[][] board = new char[n][n];
            for (int i = 0; i < n; i ++) {
                for (int j = 0; j < n; j ++) {
                    board[i][j] = '.';
                }
            }
            for (int i = 0; i < n; i ++) {
                board[i][cols[i]] = 'Q';
            }
            List<String> elem = new ArrayList<>();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i ++) {
                sb.setLength(0);
                for (int j = 0; j < n; j ++) {
                    sb.append(board[i][j]);
                }
                elem.add(sb.toString());
            }
            res.add(elem);
        }
        else {
            for (int i = 0; i < n; i ++) {
                cols[row] = i;
                if (valid(row, cols)) {
                    queens(n, row+1, cols, res);
                }
            }
        }
    }

    // check if the first row queens are valid
    private boolean valid(int row, int[] cols) {
        for (int i = 0; i < row; i ++) {
            if (cols[row] == cols[i] ||
                Math.abs(cols[row] - cols[i]) == Math.abs(row-i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        NQueen queens = new NQueen();
        List<List<String>> res = queens.solveNQueens(4);
        for (List<String> x: res) {
            System.out.println(Arrays.toString(x.toArray()));
        }
    }
}
