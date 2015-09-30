package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 5/24/15.
 * Given a 2D board and a list of words from the dictionary, find all words in the board.

 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" 
 * cells are those horizontally or vertically neighboring. The same letter cell may not be used
 * more than once in a word.

 * For example,
 * Given words = ["oath","pea","eat","rain"] and board =

 * [
 * ['o','a','a','n'],
 * ['e','t','a','e'],
 * ['i','h','k','r'],
 * ['i','f','l','v']
 * ]
 * Return ["eat","oath"].
 */
public class WordSearchII {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
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
        for (String x : words) {
            if (char2pos.containsKey(x.charAt(0))) {
                List<int[]> starts = char2pos.get(x.charAt(0));
                for (int[] start : starts) {
                    if (dfs(x, visited, start, rows, cols, board)) {
                        res.add(x);
                        break;
                    }
                }
            }
        }
        return res;
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

    private boolean dfs(String remains, boolean[][] visited, int[] curPos,
                        int rows, int cols, char[][] board) {
        if (remains.length() == 0) return true;
        if (remains.charAt(0) != board[curPos[0]][curPos[1]]) return false;
        if (remains.substring(1).length() == 0) return true;
        List<int[]> directions = possibleDirections(rows, cols, curPos);
        for (int[] dir: directions) {
            if (!visited[dir[0]][dir[1]]) {
                visited[dir[0]][dir[1]] = true;
                if (dfs(remains.substring(1), visited, dir, rows, cols, board)) {
                    visited[dir[0]][dir[1]] = false;
                    return true;
                } else {
                    visited[dir[0]][dir[1]] = false;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String[] words = {"oath","pea","eat","rain"};
        char[][] board = {{'o','a','a','n'},
                          {'e','t','a','e'},
                          {'i','h','k','r'},
                          {'i','f','l','v'}};
        WordSearchII ws = new WordSearchII();
        List<String> res = ws.findWords(board, words);
        System.out.println(Arrays.toString(res.toArray()));

        String[] words2 = {"aaaa", "aaaa", "aaaa"};
        char[][] board2 = {{'a', 'a', 'a', 'a', 'a', 'a','a','a','a','a','a'},
                           {'a', 'a', 'a', 'a', 'a', 'a','a','a','a','a','a'},
                           {'a', 'a', 'a', 'a', 'a', 'a','a','a','a','a','b'}};
        List<String> res2 = ws.findWords(board2, words2);
        System.out.println(Arrays.toString(res2.toArray()));
    }
}
