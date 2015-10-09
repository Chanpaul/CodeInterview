package Interview;

import java.util.*;
/**
 * Created by yongyangyu on 3/18/15.
 * Revised on 10/04/15 for better code readability by using predecessor matrix
 * for recovering the optimal shortest path given any to points.
 *
 * Interview question from Medallia onsite.
 * Given a two dimensional array of characters, a string as input,
 * output the moving direction sequence to build the string.
 * Use Unicode to display arrows, i.e., Left("\u2190"), Up("\u2191"),
 * Right("\u2192"), Down("\u2193")
 */
public class Floyd {
    private Map<Character, int[]> initBoard(char board[][]) {
        Map<Character, int[]> charPos = new HashMap<>();
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                charPos.put(board[i][j], new int[]{i, j});
            }
        }
        return charPos;
    }

    private String direction(int[] pos1, int[] pos2) {
        // two cells are not adjacent
        if ((pos1[0] != pos2[0]) && (pos1[1] != pos2[1])) {
            return null;
        }
        else if (pos1[0] == pos2[0]) {
            if (pos2[1] == pos1[1] + 1) {
                return "\u2192"; // Right
            }
            if (pos2[1] == pos1[1] - 1) {
                return "\u2190"; // Left
            }
        }
        else if (pos1[1] == pos2[1]) {
            if (pos2[0] == pos1[0] + 1) {
                return "\u2193"; // Down
            }
            if (pos2[0] == pos1[0] - 1) {
                return "\u2191"; // Up
            }
        }
        return null;
    }

    // perform Floyd-Warshall algo and return the PI map
    // i.e., PI(char1, char2) = char3, shortest path from char1 to char2 by char3
    // PI(i, j) = PI(i, j) if dij <= dik + dkj
    // PI(i, j) = PI(j, k) otherwise
    public Map<List<Character>, Character> floyd(char board[][]) {
        Map<Character, int[]> charPos = initBoard(board);
        Map<List<Character>, Character> parent = new HashMap<>();
        Map<List<Character>, Integer> dist = new HashMap<>();
        int m = board.length, n = board[0].length;
        char[] flattenBoard = new char[m * n];
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                flattenBoard[i*n + j] = board[i][j];
            }
        }
        // "for each node, set distance to itself to 0"
        for (int i = 0; i < flattenBoard.length; i ++) {
            List<Character> key = new ArrayList<>();
            key.add(flattenBoard[i]);
            key.add(flattenBoard[i]);
            dist.put(key, 0);
            parent.put(key, null);
        }
        // "set for connected edges"
        for (int i = 0; i < flattenBoard.length; i ++) {
            for (int j = 0; j < flattenBoard.length; j ++) {
                if (i == j) {
                    continue;
                }
                String dir = direction(charPos.get(flattenBoard[i]), charPos.get(flattenBoard[j]));
                List<Character> key = new LinkedList<>();
                key.add(flattenBoard[i]);
                key.add(flattenBoard[j]);
                if (dir != null) {
                    dist.put(key, 1);
                    parent.put(key, flattenBoard[i]);
                }
                else {
                    dist.put(key, Integer.MAX_VALUE);
                    parent.put(key, null);
                }
            }
        }
        // "set indirect edges"
        for (int k = 0; k < flattenBoard.length; k ++) {
            for (int i = 0; i < flattenBoard.length; i ++) {
                for (int j = 0; j < flattenBoard.length; j ++) {
                    List<Character> key = new ArrayList<>();
                    key.add(flattenBoard[i]);
                    key.add(flattenBoard[j]);
                    int dij = dist.get(key);
                    key = new ArrayList<>();
                    key.add(flattenBoard[i]);
                    key.add(flattenBoard[k]);
                    int dik = dist.get(key);
                    key = new ArrayList<>();
                    key.add(flattenBoard[k]);
                    key.add(flattenBoard[j]);
                    int dkj = dist.get(key);
                    if (dik == Integer.MAX_VALUE || dkj == Integer.MAX_VALUE) continue;
                    else {
                        if (dij > dik + dkj) {
                            key = new ArrayList<>();
                            key.add(flattenBoard[i]);
                            key.add(flattenBoard[j]);
                            dist.put(key, dik + dkj);
                            List<Character> key2 = new ArrayList<>();
                            key2.add(flattenBoard[k]);
                            key2.add(flattenBoard[j]);
                            parent.put(key, parent.get(key2));
                        }
                    }
                }
            }
        }
        return parent;
    }

    public List<String> path(char board[][], char start, String target) {
        Map<Character, int[]> char2pos = initBoard(board);
        List<String> res = new LinkedList<>();
        Map<List<Character>, Character> parent = floyd(board);
        for (int i = 0; i < target.length(); i ++) {
            //res.addAll(getPath(char2pos, parent, start, target.charAt(i)));
            res.add(getPath(char2pos, parent, start, target.charAt(i)));
            start = target.charAt(i);
        }
        return res;
    }

    private String getPath(Map<Character, int[]>char2pos, Map<List<Character>, Character> parent, char from, char to) {
        List<Character> key = new ArrayList<>();
        key.add(from);
        key.add(to);
        if (parent.get(key) == from) {
            return direction(char2pos.get(from), char2pos.get(to)) + "OK";
        }
        else {
            String tmp = getPath(char2pos, parent, from, parent.get(key));
            return tmp.substring(0, tmp.length()-2) +
                    direction(char2pos.get(parent.get(key)), char2pos.get(to)) + "OK";
        }
    }

    public static void main(String[] args) {
        char board[][] = {{'A', 'B', 'C', 'D', 'E', 'Z'},
                          {'F', 'G', 'H', 'I', 'J', '$'},
                          {'K', 'L', 'M', 'N', 'O', '&'},
                          {'P', 'Q', 'R', 'S', 'T', '!'},
                          {'U', 'V', 'W', 'X', 'Y', ' '}};
        String str = "MATRIX";
        char start = 'A';
        Floyd f = new Floyd();
        List<String> route = f.path(board, start, str);
        for (String s : route) {
            System.out.print(s);
        }
        System.out.println();
    }
}
