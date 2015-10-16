package Interview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yongyangyu on 10/15/15.
 * A question from Google interview.
 * Given an alphabet from 'A' - 'Z', and a width parameter to generate a board.
 * Starting from 'A', print all the moving direction for a given word.
 *
 */
public class VirtualBoard {
    public String movingDirections(int width, String word) {
        String direction = "";
        Map<Character, int[]> map = initBoard(width);
        char curr = 'A';
        for (int i = 0; i < word.length(); i ++) {
            direction += getPath(map, curr, word.charAt(i), width);
            curr = word.charAt(i);
        }
        return direction;
    }

    private Map<Character, int[]> initBoard(int width) {
        Map<Character, int[]> map = new HashMap<>();
        int row = 0;
        int cnt = 0;
        while (cnt < 26) {
            for (int j = 0; j < width; j ++) {
                map.put((char)('A' + cnt), new int[]{row, j});
                cnt ++;
            }
            row ++;
        }
        return map;
    }

    private String getPath(Map<Character, int[]> map, char curr, char dest, int width) {
        String res = "";
        int[] currPos = map.get(curr);
        int[] destPos = map.get(dest);
        if (curr == dest) {
            res += "X";
            return res;
        }
        int length = 26 / width;
        if (26 % width != 0) length ++;
        int x1 = currPos[0], y1 = currPos[1];
        int x2 = destPos[0], y2 = destPos[1];
        // How to know test x's first or y's first?
        if (length >= width) { // move x's first
            if (x1 < x2) {
                for (int i = 0; i < x2 - x1; i++) {
                    res += "D";
                }
            }
            if (x1 > x2) {
                for (int i = 0; i < x1 - x2; i++) {
                    res += "U";
                }
            }
            if (y1 < y2) {
                for (int j = 0; j < y2 - y1; j++) {
                    res += "R";
                }
            }
            if (y1 > y2) {
                for (int j = 0; j < y1 - y2; j++) {
                    res += "L";
                }
            }
        }
        else {
            if (y1 < y2) {
                for (int j = 0; j < y2 - y1; j++) {
                    res += "R";
                }
            }
            if (y1 > y2) {
                for (int j = 0; j < y1 - y2; j++) {
                    res += "L";
                }
            }
            if (x1 < x2) {
                for (int i = 0; i < x2 - x1; i++) {
                    res += "D";
                }
            }
            if (x1 > x2) {
                for (int i = 0; i < x1 - x2; i++) {
                    res += "U";
                }
            }
        }
        res += "X";
        return res;
    }

    public void printBoard(int width) {
        int cnt = 0;
        while (cnt < 26) {
            for (int j = 0; j < width; j ++) {
                System.out.print((char)('A' + cnt));
                System.out.print(" ");
                cnt ++;
                if (cnt >= 26) break;  // no need to print extra characters
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        VirtualBoard vb = new VirtualBoard();
        int width = 5;
        String word = "TUX";
        vb.printBoard(width);
        System.out.println(vb.movingDirections(width, word));
    }
}
