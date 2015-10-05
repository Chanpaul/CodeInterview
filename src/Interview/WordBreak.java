package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 1/21/15.
 * Given a string s and a dictionary of words dict, determine if s
 * can be segmented into a space-separated sequence of one or more dictionary words.
 *
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 *
 * Return true because "leetcode" can be segmented as "leet code".
 * DFS also works but time exceeds when the input dict looks like
 * ["a", "aa", "aaa", "aaaaa", "aaaaa"] and s = "aaaaaaaaaaaaaaaaaaaaaa"
 */
public class WordBreak {
    public boolean wordBreak1(String s, Set<String> wordDict) {
        boolean[] dp = new boolean[s.length()];
        if (wordDict.contains(s)) return true;
        if (s.length() == 0 && wordDict.size() == 0) return true;
        if (s.length() == 0 && wordDict.size() != 0) return false;
        if (wordDict.contains(s.substring(0, 1))) dp[0] = true;
        for (int i = 1; i < s.length(); i ++) {
            if (wordDict.contains(s.substring(0, i + 1))) {
                dp[i] = true;
                continue;
            }
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] && wordDict.contains(s.substring(j+1, i+1))) {
                    dp[i] = true;
                }
            }
        }
        return dp[s.length() - 1];
    }

    public static void main(String[] args) {
        String s = "leetcode";
        Set<String> dict = new HashSet<>();
        String[] d = {"leet", "code"};
        for (String word: d) {
            dict.add(word);
        }
       System.out.println(new WordBreak().wordBreak1(s, dict));
    }
}
