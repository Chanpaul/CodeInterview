package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 3/11/16. (LeetCode, Trie for pruning by reversing words)
 * Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list,
 * so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 * Example 1:
 *  Given words = ["bat", "tab", "cat"]
 *  Return [[0, 1], [1, 0]]
 *  The palindromes are ["battab", "tabbat"]
 *
 * Example 2:
 * Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 * Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 * The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
 */
public class PalindromePairs {
    class TrieNode {
        Map<Character, TrieNode> map;
        int index; // current node is a complete word
        List<Integer> list; // word has suffix represented by the current node and rest of it is a palindrome
        TrieNode() {
            map = new HashMap<>();
            index = -1;
            list = new ArrayList<>();
        }
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i ++) {
            insertWord(root, words[i], i);
        }
        for (int i = 0; i < words.length; i ++) {
            search(root, words, i, result);
        }
        return result;
    }

    // insert the reverse of the word into the Trie
    private void insertWord(TrieNode root, String word, int index) {
        for (int i = word.length() - 1; i >= 0; i --) {
            if (!root.map.containsKey(word.charAt(i))) {
                root.map.put(word.charAt(i), new TrieNode());
            }
            if (isPalindrome(word, 0, i)) {
                root.list.add(index);
            }
            root = root.map.get(word.charAt(i));
        }
        root.index = index;
        root.list.add(index);
    }

    private void search(TrieNode root, String[] words, int i, List<List<Integer>> res) {
        for (int j = 0; j < words[i].length(); j ++) {
            if (root.index >= 0 && root.index != i && isPalindrome(words[i], j , words[i].length()-1)) {
                res.add(Arrays.asList(i, root.index));
            }
            if (root.map.containsKey(words[i].charAt(j))) {
                root = root.map.get(words[i].charAt(j));
            }
            else return;
        }
        for (int j: root.list) {
            if (i != j) res.add(Arrays.asList(i, j));
        }
    }

    private boolean isPalindrome(String str, int i, int j) {
        while (i < j) {
            if (str.charAt(i++) != str.charAt(j--)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String[] words = {"abcd", "dcba", "lls", "s", "sssll"};
        PalindromePairs pp = new PalindromePairs();
        List<List<Integer>> pairs = pp.palindromePairs(words);
        System.out.println(Arrays.toString(pairs.toArray()));
    }
}
