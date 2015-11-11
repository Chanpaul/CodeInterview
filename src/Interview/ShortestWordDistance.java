package Interview;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yongyangyu on 11/11/15.
 * Given a list of words and two words word1 and word2, return the shortest distance between
 * these two words in the list.
 *
 * For example,
 * Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 *
 * Given word1 = "coding", word2 = "practice", return 3.
 * Given word1 = "makes", word2 = "coding", return 1.
 *
 * Note:
 * You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */
public class ShortestWordDistance {
    private Map<String, List<Integer>> word2idx;

    public ShortestWordDistance(String[] words) {
        word2idx = new HashMap<>();
        for (int i = 0; i < words.length; i ++) {
            String curr = words[i];
            if (!word2idx.containsKey(curr)) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                word2idx.put(curr, list);
            }
            else {
                word2idx.get(curr).add(i);
            }
        }
    }

    // just find the shortest distance between 2 sorted lists
    // use merge-sort like search
    public int shortest(String word1, String word2) {
        List<Integer> list1 = word2idx.get(word1);
        List<Integer> list2 = word2idx.get(word2);
        int i = 0, j = 0;
        int dist = Integer.MAX_VALUE;
        while (i < list1.size() && j < list2.size()) {
            dist = Math.min(dist, Math.abs(list1.get(i) - list2.get(j)));
            if (list1.get(i) > list2.get(j)) j ++;
            else i ++;
        }
        return dist;
    }

    public static int shortestDistance(String[] words, String word1, String word2) {
        int dist = Integer.MAX_VALUE;
        int idx1 = -1, idx2 = -1;
        for (int i = 0; i < words.length; i ++) {
            if (word1.equals(words[i])) idx1 = i;
            else if (word2.equals(words[i])) idx2 = i;
            if (idx1 != -1 && idx2 != -1) {
                dist = Math.min(dist, Math.abs(idx1 - idx2));
            }
        }
        return dist;
    }

    /*
     * Follow up questions:
     * The only difference is now you are given the list of words and your method will be called
     * "repeatedly" many times with different parameters. How would you optimize it?
     */

    public static void main(String[] args) {
        String[] words = {"practice", "makes", "perfect", "coding", "makes"};
        String word1 = "makes", word2 = "coding";
        System.out.println(ShortestWordDistance.shortestDistance(words, word1, word2));
        ShortestWordDistance swd =  new ShortestWordDistance(words);
        System.out.println(swd.shortest(word1, word2));
    }
}
