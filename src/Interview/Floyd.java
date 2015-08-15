package Interview;

import java.util.*;
/**
 * Created by yongyangyu on 3/18/15.
 *
 * Interview question from Medallia onsite.
 * Given a two dimensional array of characters, a string as input,
 * output the moving direction sequence to build the string.
 */
public class Floyd {

    private Map<Character, List<Integer>> getMap(char board[][]) {
        Map<Character, List<Integer>> charPos = new HashMap<Character, List<Integer>>();
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                List<Integer> pos = new LinkedList<Integer>();
                pos.add(i);
                pos.add(j);
                charPos.put(new Character(board[i][j]), pos);
            }
        }
        return charPos;
    }

    private String direction(List<Integer> pos1, List<Integer> pos2) {
        // two cells are not adjacent
        if ((pos1.get(0) != pos2.get(0)) && (pos1.get(1) != pos2.get(1))) {
            return null;
        }
        else if (pos1.get(0) == pos2.get(0)) {
            if (pos2.get(1) == pos1.get(1) + 1) {
                return "Right";
            }
            if (pos2.get(1) == pos1.get(1) - 1) {
                return "Left";
            }
        }
        else if (pos1.get(1) == pos2.get(1)) {
            if (pos2.get(0) == pos1.get(0) + 1) {
                return "Down";
            }
            if (pos2.get(0) == pos1.get(0) - 1) {
                return "Up";
            }
        }
        return null;
    }

    public Map<List<Character>, List<String>> floyd(char board[][]) {
        Map<Character, List<Integer>> charPos = getMap(board);
        Map<List<Character>, List<String>> map = new HashMap<List<Character>, List<String>>();
        List<Character> flattenBoard = new ArrayList<Character>();
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board.length; j ++) {
                flattenBoard.add(board[i][j]);
            }
        }
        // "for each node, set distance to itself to 0"
        for (int i = 0; i < flattenBoard.size(); i ++) {
            List<Character> key = new LinkedList<Character>();
            key.add(flattenBoard.get(i));
            key.add(flattenBoard.get(i));
            List<String> val = new LinkedList<String>();
            val.add("");
            map.put(key, val);
        }
        // "set for connected edges"
        for (int i = 0; i < flattenBoard.size(); i ++) {
            for (int j = 0; j < flattenBoard.size(); j ++) {
                if (i == j) {
                    continue;
                }
                String dir = direction(charPos.get(flattenBoard.get(i)), charPos.get(flattenBoard.get(j)));
                if (dir != null) {
                    List<Character> key = new LinkedList<Character>();
                    key.add(flattenBoard.get(i));
                    key.add(flattenBoard.get(j));
                    List<String> val = new LinkedList<String>();
                    val.add(dir);
                    map.put(key, val);
                }
            }
        }
        // "set indirect edges"
        for (int k = 0; k < flattenBoard.size(); k ++) {
            for (int i = 0; i < flattenBoard.size(); i ++) {
                for (int j = 0; j < flattenBoard.size(); j ++) {
                    List<Character> key = new LinkedList<Character>();
                    key.add(flattenBoard.get(i));
                    key.add(flattenBoard.get(j));
                    List<String> val = null;
                    if (map.containsKey(key)) {
                        val = map.get(key);
                    }
                    List<Character> key1 = new LinkedList<Character>();
                    List<Character> key2 = new LinkedList<Character>();
                    key1.add(flattenBoard.get(i));
                    key1.add(flattenBoard.get(k));
                    key2.add(flattenBoard.get(k));
                    key2.add(flattenBoard.get(j));
                    if (map.containsKey(key1) && map.containsKey(key2)) {
                        List<Character> newKey = new LinkedList<Character>();
                        newKey.add(flattenBoard.get(i));
                        newKey.add(flattenBoard.get(j));
                        List<String> newVal = new LinkedList<String>();
                        newVal.addAll(map.get(key1));
                        newVal.addAll(map.get(key2));
                        if (val == null) {
                            map.put(newKey, newVal);
                        }
                        else {
                            if (newVal.size() < val.size()) {
                                map.put(newKey, newVal);
                            }
                        }
                    }
                }
            }
        }
        return map;
    }

    public List<String> path(char board[][], char start, String target) {
        List<String> res = new LinkedList<String>();
        Map<List<Character>, List<String>> map = floyd(board);
        List<Character> curr = new LinkedList<Character>();
        curr.add(start);
        curr.add(target.charAt(0));
        res.addAll(map.get(curr));
        res.add("OK");
        for (int i = 1; i < target.length(); i ++) {
            curr.remove(0);
            curr.add(target.charAt(i));
            res.addAll(map.get(curr));
            res.add("OK");
        }
        return res;
    }

    public static void main(String[] args) {
        char board[][] = {{'A', 'B', 'C', 'D', 'E'},
                          {'F', 'G', 'H', 'I', 'J'},
                          {'K', 'L', 'M', 'N', 'O'},
                          {'P', 'Q', 'R', 'S', 'T'},
                          {'U', 'V', 'W', 'X', 'Y'}};
        String str = "AYOR";
        char start = 'A';
        Floyd f = new Floyd();
        List<String> route = f.path(board, start, str);
        for (String s : route) {
            System.out.print(s);
            System.out.print(" ");
        }
        System.out.println();
    }
}
