package Interview;

/**
 * Created by yongyangyu on 10/8/15.
 * Given a string s1, we may represent it as a binary tree by partitioning
 * it to two non-empty substrings recursively.
 *
 * Below is one possible representation of s1 = "great":
 *            great
 *           /    \
 *          gr    eat
 *         / \    /  \
 *        g   r  e   at
 *                   / \
 *                  a   t
 *
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 *
 * For example, if we choose the node "gr" and swap its two children,
 * it produces a scrambled string "rgeat".
 *             rgeat
 *            /    \
 *           rg    eat
 *          / \    /  \
 *         r   g  e   at
 *                    / \
 *                   a   t
 *
 * We say that "rgeat" is a scrambled string of "great".
 *
 * Similarly, if we continue to swap the children of nodes "eat" and "at",
 * it produces a scrambled string "rgtae".
 *
 *             rgtae
 *            /    \
 *           rg    tae
 *          / \    /  \
 *          r   g  ta  e
 *                 / \
 *                t   a
 *
 * We say that "rgtae" is a scrambled string of "great".
 *
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 */
public class ScrambleString {
    public boolean isScramble(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        if (s1.equals(s2)) return true;
        int n = s1.length();
        boolean[][][] dp = new boolean[n][n][n];
        // dp[i][j][k] means s1[i:i+k+1] is a scramble of s2[j:j+k+1]
        for (int i = 0; i < n; i ++) {
            for (int j = 0; j < n; j ++) {
                if (s1.charAt(i) == s2.charAt(j))
                    dp[i][j][0] = true;
            }
        }
        for (int k = 0; k < n; k ++) {
            for (int i = 0; i < n-k; i ++) {
                for (int j = 0; j < n-k; j++) {
                    for (int p = 0; p < k; p ++) {
                        if ((dp[i][j][p] && dp[i+p+1][j+p+1][k-p-1]) ||
                            (dp[i][j+k-p][p] && dp[i+p+1][j][k-p-1]))
                            dp[i][j][k] = true;
                    }
                }
            }
        }
        return dp[0][0][n-1];
    }
}
