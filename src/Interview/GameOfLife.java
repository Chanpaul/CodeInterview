package Interview;

/**
 * Created by yongyangyu on 10/3/15.
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the
 * following four rules (taken from the above Wikipedia article):
 *
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * Write a function to compute the next state (after one update) of the board given its current state.
 *
 * Follow up:
 * Could you solve it in-place? Remember that the board needs to be updated at the same time:
 * You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite,
 * which would cause problems when the active area encroaches the border of the array.
 * How would you address these problems?
 */
public class GameOfLife {
    public void nextState(int[][] board) {
        int m = board.length, n = board[0].length;
        int[][] state = new int[m][n];
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (board[i][j] == 0) {
                    if (countNeighbors(board, i, j, 1) == 3) state[i][j] = 1;
                }
                else {
                    int liveNeighbors = countNeighbors(board, i, j, 1);
                    if (liveNeighbors < 2) state[i][j] = 0;
                    else if (liveNeighbors == 2 || liveNeighbors == 3) state[i][j] = 1;
                    else state[i][j] = 0;
                }
            }
        }
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++)
                board[i][j] = state[i][j];
        }
        return;
    }

    private int countNeighbors(int[][] board, int i, int j, int value) {
        int count = 0;
        int m = board.length, n = board[0].length;
        if (i-1 >= 0 && j-1 >= 0 && board[i-1][j-1] == value) count ++;
        if (i-1 >=0 && board[i-1][j] == value) count ++;
        if (i-1 >= 0 && j+1 < n && board[i-1][j+1] == value) count ++;
        if (j-1 >= 0 && board[i][j-1] == value) count ++;
        if (j+1 < n && board[i][j+1] == value) count ++;
        if (i+1 < m && j-1 >= 0 && board[i+1][j-1] == value) count ++;
        if (i+1 < m && board[i+1][j] == value) count ++;
        if (i+1 < m && j+1 < n && board[i+1][j+1] == value) count ++;
        return count;
    }


    public static void main(String[] args) {
        int[][] board = {{1}};
        new GameOfLife().nextState(board);
    }
}
