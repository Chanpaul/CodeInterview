package Interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yongyangyu on 9/30/15.
 * Given a 2D board and a word, find if the word exists in the grid.
 *
 * The word can be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once.
 */
public class WordSearchI {
    public boolean exist(char[][] board, String word) {
        if (word.length() == 0) return true;
        Map<Character, List<int[]>> char2pos = new HashMap<>();
        int rows = board.length;
        int cols = board[0].length;
        for (int i = 0; i < rows; i ++) {
            for (int j = 0; j < cols; j ++) {
                if (!char2pos.containsKey(board[i][j])) {
                    List<int[]> cur = new ArrayList<>();
                    cur.add(new int[]{i, j});
                    char2pos.put(board[i][j], cur);
                }
                else {
                    List<int[]> cur = char2pos.get(board[i][j]);
                    cur.add(new int[]{i, j});
                    char2pos.put(board[i][j], cur);
                }
            }
        }
        boolean[][] visited = new boolean[rows][cols];
        if (char2pos.containsKey(word.charAt(0))) {
            List<int[]> starts = char2pos.get(word.charAt(0));
            for (int[] start : starts) {
                if (dfs(word, visited, start, rows, cols, board)) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<int[]> possibleDirections(int rows, int cols, int[] x) {
        List<int[]> directions = new ArrayList<>();
        int i = x[0], j = x[1];
        if (i - 1 >= 0) directions.add(new int[]{i-1, j});
        if (i + 1 < rows) directions.add(new int[]{i+1, j});
        if (j - 1 >= 0) directions.add(new int[]{i, j - 1});
        if (j + 1 < cols) directions.add(new int[]{i, j + 1});
        return directions;
    }

    private boolean dfs(String remains, boolean[][] visited, int[] curPos, int rows, int cols, char[][] board) {
        if (remains.length() == 0) return true;
        if (remains.charAt(0) != board[curPos[0]][curPos[1]]) return false;
        if (remains.substring(1).length() == 0) return true;
        List<int[]> directions = possibleDirections(rows, cols, curPos);
        for (int[] dir: directions) {
            if (!visited[dir[0]][dir[1]]) {
                visited[dir[0]][dir[1]] = true;
                if (dfs(remains.substring(1), visited, dir, rows, cols, board)) {
                    return true;
                } else {
                    visited[dir[0]][dir[1]] = false;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'},
                          {'S', 'F', 'C', 'S'},
                          {'A', 'D', 'E', 'E'}};
        String word1 = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";
        WordSearchI ws = new WordSearchI();
        System.out.println(ws.exist(board, word1));
        System.out.println(ws.exist(board, word2));
        System.out.println(ws.exist(board, word3));

        char[][] board2 = {{'a'}};
        System.out.println(new WordSearchI().exist(board2, "a"));
    }
}
