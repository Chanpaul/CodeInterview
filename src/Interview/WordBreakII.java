package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 10/5/15.
 * Given a string s and a dictionary of words dict, add spaces in s to construct a
 * sentence where each word is a valid dictionary word.
 *
 * Return all such possible sentences.
 *
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 *
 * A solution is ["cats and dog", "cat sand dog"].
 */
public class WordBreakII {
    public List<String> wordBreak(String s, Set<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        List<String> res = new ArrayList<>();
        if (!contains(s, wordDict)) return res;
        Map<Integer, List<String>> paths = new HashMap<>();
        if (s.length() == 0 && wordDict.size() == 0) return res;
        if (s.length() == 0 && wordDict.size() != 0) return res;
        if (wordDict.contains(s.substring(0, 1))) {
            dp[0] = true;
            List<String> elem = new ArrayList<>();
            elem.add(s.substring(0, 1));
            paths.put(0, elem);
        }
        for (int i = 1; i < s.length(); i ++) {
            List<String> elem = new ArrayList<>();
            if (wordDict.contains(s.substring(0, i + 1))) {
                dp[i] = true;
                elem.add(s.substring(0, i + 1));
            }
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && wordDict.contains(s.substring(j+1, i+1))) {
                    dp[i] = true;
                    for (String x: paths.get(j)) {
                        elem.add(x + " " + s.substring(j+1, i+1));
                    }
                }
            }
            if (dp[i]) paths.put(i, elem);
        }
        if (dp[s.length() - 1]) {
            return paths.get(s.length() - 1);
        }
        return res;
    }

    private boolean contains(String s, Set<String> wordDict) {
        Set<Character> strset = new HashSet<>();
        for (int i = 0; i < s.length(); i ++) {
            strset.add(s.charAt(i));
        }
        Set<Character> dictSet = new HashSet<>();
        for (String x: wordDict) {
            for (int i = 0; i < x.length(); i ++) {
                dictSet.add(x.charAt(i));
            }
        }
        if (strset.size() > wordDict.size()) return false;
        for (char c: strset) {
            if (!dictSet.contains(c)) return false;
        }
        for (String word: wordDict) {
            if (!s.contains(word)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aaaaa";
        String[] dict = {"a", "aa", "aaa"};
        Set<String> wordDict = new HashSet<>();
        for (String x: dict) {
            wordDict.add(x);
        }
        System.out.println(Arrays.toString(new WordBreakII().wordBreak(s, wordDict).toArray()));
    }
}
