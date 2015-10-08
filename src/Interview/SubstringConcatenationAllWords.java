package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 9/15/15.
 * You are given a string, s, and a list of words, words, that are all of
 * the same length. Find all starting indices of substring(s) in s that is a concatenation
 * of each word in words exactly once and without any intervening characters.
 *
 * For example, given:
 * s: "barfoothefoobarman"
 * words: ["foo", "bar"]
 *
 * You should return the indices: [0,9].
 * (order does not matter).
 */
public class SubstringConcatenationAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        int len = words[0].length();
        Set<String> set = new HashSet<>();
        int sig = 0;  // use a signature for testing containing all words
        for (String word: words) {
            sig += word.hashCode();
            set.add(word);
        }
        int total = len * words.length;

        for (int start = 0; start < s.length() - total + 1; start ++) {
            if (!set.contains(s.substring(start, start + len))) continue;
            int end = start + total - 1;
            int test = 0;
            for (int i = start; i < end + 1; i = i + len) {
                String tmp = s.substring(i, i + len);
                if (!set.contains(tmp)) break;
                test += tmp.hashCode();
                if (test == sig) {
                    res.add(start);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "bbabbbab";
        String[] words = {"ab", "bb"};
        System.out.println(Arrays.toString(new SubstringConcatenationAllWords()
                            .findSubstring(s, words).toArray()));
    }
}
