package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 5/24/15.
 * Given a 2D board and a list of words from the dictionary, find all words in the board.

 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" 
 * cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

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
    public class Tuple {
        private int _1;
        private int _2;

        public Tuple(int f1, int f2) {
            _1 = f1;
            _2 = f2;
        }

        public int get_1() {
            return _1;
        }

        public int get_2() {
            return _2;
        }

        @Override
        public int hashCode() {
            return (_1 << 3) + (_2 << 1);
        }

        @Override
        public boolean equals(Object o) {
            if (o instanceof Tuple) {
                Tuple other = (Tuple) o;
                return (_1 == other.get_1() && _2 == other.get_2());
            }
            else {
                return false;
            }
        }
    }

    public List<String> findWords(char[][] board, String[] words) {
        Map<Character, List<Tuple>> posMap = letterPos(board);
        List<String> res = new LinkedList<String>();
        Trie trie = new Trie();
        for (String word : words) {
            if (word == null || word.length() == 0) {
                continue;
            }
            if (!posMap.containsKey(word.charAt(0))) {
                continue;
            }
            if (res.size() > 0) {
                if (trie.startsWith(word)) {
                    res.add(word);
                    continue;
                }
            }
            List<Tuple> startPos = posMap.get(word.charAt(0));
            for (Tuple pos : startPos) {
                if (existPath(posMap, word.substring(1), pos, board.length, board[0].length)) {
                    res.add(word);
                    trie.insert(word);
                    break;
                }
            }
        }
        return res;
    }

    private Map<Character, List<Tuple>> letterPos(char[][] board) {
        Map<Character, List<Tuple>> posMap = new HashMap<Character, List<Tuple>>();
        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                char currLetter = board[i][j];
                if (!posMap.containsKey(currLetter)) {
                    List<Tuple> elem = new LinkedList<Tuple>();
                    elem.add(new Tuple(i, j));
                    posMap.put(currLetter, elem);
                }
                else {
                    List<Tuple> elem = posMap.get(currLetter);
                    elem.add(new Tuple(i, j));
                    posMap.put(currLetter, elem);
                }
            }
        }
        return posMap;
    }
    //startPos is the position of last matched letter
    //word is the remaining substring to be matched
    private boolean existPath(Map<Character, List<Tuple>> posMap, String word, Tuple startPos, int size_x, int size_y) {
        if (word.length() == 0) {
            return true;
        }
        List<Tuple> nextPos = adjacentPos(startPos, size_x, size_y);
        Map<Tuple, Boolean> checkMap = new HashMap<Tuple, Boolean>();
        for (Tuple pos : nextPos) {
            checkMap.put(pos, true);
        }
        if (!posMap.containsKey(word.charAt(0))) {
            return false;
        }
        List<Tuple> letterPos = posMap.get(word.charAt(0));
        for (Tuple pos : letterPos) {
            if (checkMap.containsKey(pos)) {
                if (existPath(posMap, word.substring(1), pos, size_x, size_y)) {
                    return true;
                }
            }
        }
        return false;
    }

    private List<Tuple> adjacentPos(Tuple currPos, int size_x, int size_y) {
        List<Tuple> res = new LinkedList<Tuple>();
        int x = currPos.get_1(); int y = currPos.get_2();
        if (y - 1 >= 0) {
            res.add(new Tuple(x, y - 1));
        }
        if (y + 1 <= size_y - 1) {
            res.add(new Tuple(x, y + 1));
        }
        if (x - 1 >= 0) {
            res.add(new Tuple(x - 1, y));
        }
        if (x + 1 <= size_x - 1) {
            res.add(new Tuple(x + 1, y));
        }
        return res;
    }

  /*  private List<List<Integer[]>> getPaths(Map<Character, List<Integer[]>> posMap, String word) {
        List<List<Integer[]>> nullElem = new ArrayList<List<Integer[]>>();
        if (word == null || word.length() == 0) {
            return nullElem;
        }
        List<List<Integer[]>> res = new ArrayList<List<Integer[]>>();
        for (int i = 0; i < word.length(); i ++) {
            if (!posMap.containsKey(word.charAt(i))) {  // current letter not in the map
                return nullElem;
            }
            if (res.size() == 0) { // first letter
                for (Integer[] elem : posMap.get(word.charAt(i))) {
                    List<Integer[]> firstPos = new ArrayList<Integer[]>();
                    firstPos.add(elem);
                    res.add(firstPos);
                }
            }
            else {
                List<Integer[]> currLetterPos = posMap.get(word.charAt(i));
                List<List<Integer[]>> update = new ArrayList<List<Integer[]>>();
                for (Integer[] candid : currLetterPos) {
                    for (List<Integer[]> partialPath : res) {
                        if (checkAdjcent(partialPath.get(partialPath.size() - 1), candid)) {
                            List<Integer[]> partialExtension = new ArrayList<Integer[]>(partialPath);
                            partialExtension.add(candid);
                            update.add(partialExtension);
                        }
                    }
                }
                if (update.size() > 0) {
                    res = update;
                }
                else {
                    return update;
                }
            }
        }
        return res;
    }

    private boolean checkAdjcent (Integer[] pre, Integer[] curr) {
        if (pre[0] == curr[0] && Math.abs(pre[1] - curr[1]) == 1) {
            return true;
        }
        else if (pre[1] == curr[1] && Math.abs(pre[0] - curr[0]) == 1) {
            return true;
        }
        else {
            return false;
        }
    }*/

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
