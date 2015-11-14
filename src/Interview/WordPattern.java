package Interview;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

    /*
     * follow-up question:
     * Given a pattern and a string str, find if str follows the same pattern.
     *
     * Here follow means a full match, such that there is a bijection between a letter
     * in pattern and a non-empty substring in str.
     *
     * Examples:
     *
     * pattern = "abab", str = "redblueredblue" should return true.
     * pattern = "aaaa", str = "asdasdasdasd" should return true.
     * pattern = "aabb", str = "xyzabcxzyabc" should return false.
     */
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();
        Set<String> set = new HashSet<>();
        return isMatch(str, 0, pattern, 0, map, set);
    }

    private boolean isMatch(String str, int i, String pat, int j,
                            Map<Character, String> map, Set<String> set) {
        if (i == str.length() && j == pat.length()) return true;
        if (i == str.length() || j == pat.length()) return false;
        char c = pat.charAt(j);
        if (map.containsKey(c)) {
            String s = map.get(c);
            // check if we can use it to match str[i, ..., i+s.length()]
            if (!str.startsWith(s, i)) return false;
            return isMatch(str, i+s.length(), pat, j+1, map, set);
        }
        for (int k = i; i < str.length(); k ++) {
            String p = str.substring(i, k+1);
            if (set.contains(p)) continue;
            map.put(c, p);
            set.add(p);
            if (isMatch(str, k+1, pat, j+1, map, set)) return true;
            map.remove(c);
            set.remove(p);
        }
        return false;
    }

    public static void main(String[] args) {
        String[] patterns = {"abba", "abba", "aaaa", "abba"};
        String[] strs = {"dog cat cat dog", "dog cat cat fish",
                         "dog cat cat dog", "dog dog dog dog"};
        WordPattern wp = new WordPattern();
        for (int i = 0; i < patterns.length; i ++) {
            System.out.println(wp.wordPattern(patterns[i], strs[i]));
        }
        System.out.println(wp.wordPatternMatch("abab", "redblueredblue"));
    }
}
