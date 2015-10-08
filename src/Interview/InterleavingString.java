package Interview;

/**
 * Created by yongyangyu on 10/7/15.
 * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
 *
 * For example,
 * Given:
 * s1 = "aabcc",
 * s2 = "dbbca",
 *
 * When s3 = "aadbbcbcac", return true.
 * When s3 = "aadbbbaccc", return false.
 */
public class InterleavingString {
    public boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length();
        int n = s2.length();
        if (m+n != s3.length()) return false;
        if (s1.length() == 0 && s2.length() == 0 && s3.length() == 0) return true;
        if (s1.length() != 0 && s2.length() == 0 && !s3.equals(s1)) return false;
        if (s1.length() == 0 && s2.length() != 0 && !s3.equals(s2)) return false;
        boolean[][] dp = new boolean[m+1][n+1];
        for (int i = 0; i < m+1; i ++) {
            for (int j = 0; j < n+1; j ++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                }
                else if (j > 0 && i == 0 && s2.charAt(j-1) == s3.charAt(i+j-1))
                    dp[i][j] = dp[i][j-1];
                else if (i > 0 && j == 0 && s1.charAt(i-1) == s3.charAt(i+j-1))
                    dp[i][j] = dp[i-1][j];
                else if (i > 0 && j > 0 && s1.charAt(i-1) == s3.charAt(i+j-1) &&
                        s2.charAt(j-1) != s3.charAt(i+j-1))
                    dp[i][j] = dp[i-1][j];
                else if (i > 0 && j > 0 && s1.charAt(i-1) != s3.charAt(i+j-1) &&
                        s2.charAt(j-1) == s3.charAt(i+j-1))
                    dp[i][j] = dp[i][j-1];
                else if (i > 0 && j > 0 && s1.charAt(i-1) == s3.charAt(i+j-1) &&
                        s2.charAt(j-1) == s3.charAt(i+j-1))
                    dp[i][j] = dp[i-1][j] | dp[i][j-1];
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String s1 = "a";
        String s2 = "b";
        String s3 = "ab";
        System.out.println(new InterleavingString().isInterleave(s1,s2,s3));
    }
}
