package Interview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yongyangyu on 10/5/15.
 * Given a pattern and a string str, find if str follows the same pattern.
 *
 * Examples:
 * pattern = "abba", str = "dog cat cat dog" should return true.
 * pattern = "abba", str = "dog cat cat fish" should return false.
 * pattern = "aaaa", str = "dog cat cat dog" should return false.
 * pattern = "abba", str = "dog dog dog dog" should return false.
 * Notes:
 * Both pattern and str contains only lowercase alphabetical letters.
 * Both pattern and str do not have leading or trailing spaces.
 * Each word in str is separated by a single space.
 * Each letter in pattern must map to a word with length that is at least 1.
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> pattern2str = new HashMap<>();
        String[] strs = str.split(" ");
        if (pattern.length() != strs.length) return false;
        for (int i = 0; i < pattern.length(); i ++) {
            char curr = pattern.charAt(i);
            if (!pattern2str.containsKey(curr)) {
                if (pattern2str.containsValue(strs[i])) return false;
                pattern2str.put(curr, strs[i]);
            }
            else {
                if (!pattern2str.get(curr).equals(strs[i])) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] patterns = {"abba", "abba", "aaaa", "abba"};
        String[] strs = {"dog cat cat dog", "dog cat cat fish",
                         "dog cat cat dog", "dog dog dog dog"};
        WordPattern wp = new WordPattern();
        for (int i = 0; i < patterns.length; i ++) {
            System.out.println(wp.wordPattern(patterns[i], strs[i]));
        }
    }
}
