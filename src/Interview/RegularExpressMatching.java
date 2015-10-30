package Interview;

/**
 * Created by yongyangyu on 6/24/15.
 * Implement regular expression matching with support for '.' and '*'.
 *
 * '.' Matches any single character.
 * '*' Matches zero or more of the preceding element.
 *
 * The matching should cover the entire input string (not partial).
 *
 * The function prototype should be:
 * bool isMatch(const char *s, const char *p)
 *
 * Some examples:
 * isMatch("aa","a") -> false
 * isMatch("aa","aa") -> true
 * isMatch("aaa","aa") -> false
 * isMatch("aa", "a*") -> true
 * isMatch("aa", ".*") -> true
 * isMatch("ab", ".*") -> true
 * isMatch("aab", "c*a*b") -> true
 */
public class RegularExpressMatching {
    // String s is the candidate string, string p is a pattern string with '.' and '*';
    public static boolean isMatch(String s, String p) {
        int ls = s.length(), lp = p.length();
        // check empty string
        if (lp == 0) {
            return ls == 0;
        }
        // dp[i][j] means the substring with first i letters in s matches with
        // the substring with first j letters in p
        boolean[][] dp = new boolean[ls+1][lp+1];
        dp[0][0] = true;
        for (int i = 0; i < ls+1; i ++) {
            for (int j = 1; j < lp+1; j ++) {
                if (i > 0) {
                    // no '*', s[i-1] matches with p[j-1] and dp[i-1][j-1] is true
                    if (dp[i-1][j-1] && match(s.charAt(i-1), p.charAt(j-1))) {
                        dp[i][j] = true;
                        continue;
                    }
                }
                if (i > 0 && j > 1) {
                    // current '*', s[i-1] matches with p[j-2] and dp[i-1][j] is true
                    if (dp[i-1][j] && match(s.charAt(i-1), p.charAt(j-2)) && p.charAt(j-1) == '*') {
                        dp[i][j] = true;
                        continue;
                    }
                }
                if (j > 1) {
                    // current '*' has no effect, dp[i][j-2] is true
                    if (dp[i][j-2] && p.charAt(j-1) == '*') {
                        dp[i][j] = true;
                        continue;
                    }
                }
            }
        }
        return dp[ls][lp];
    }

    private static boolean match(char a, char b) {
        return a == b || b == '.';
    }

    public static void main(String[] args) {
        System.out.println(isMatch("aa","a"));
        System.out.println(isMatch("aa","aa"));
        System.out.println(isMatch("aaa","aa"));
        System.out.println(isMatch("aa", "a*"));
        System.out.println(isMatch("aa", ".*"));
        System.out.println(isMatch("ab", ".*"));
        System.out.println(isMatch("aab", "c*a*b"));
    }
}
