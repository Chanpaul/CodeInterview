package Interview;

/**
 * Created by yongyangyu on 8/3/15.
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2.
 * (each operation is counted as 1 step.)
 *
 * You have the following 3 operations permitted on a word:
 *
 * a) Insert a character
 * b) Delete a character
 * c) Replace a character
 */
public class EditDistance {
    public static int dist(String s1, String s2) {
        if (s1 == null || s2 == null) {
            if (s1 == null && s2 != null) {
                return s2.length();
            }
            if (s1 != null && s2 == null) {
                return s1.length();
            }
        }
        if (s1.length() == 0 && s2.length() == 0) {
            return 0;
        }
        int[][] edist = new int[s1.length() + 1][s2.length() + 1];
        for (int i = 0; i < edist.length; i ++) {
            edist[i][0] = i;
        }
        for (int j = 0; j < edist[0].length; j ++) {
            edist[0][j] = j;
        }
        for (int i = 1; i < edist.length; i ++) {
            for (int j = 1; j < edist[0].length; j ++) {
                int d1 = Math.min(edist[i-1][j] + 1, edist[i][j-1] + 1);
                int d2 = edist[i-1][j-1];
                if (s1.charAt(i - 1) != s2.charAt(j - 1)) {
                    d2 ++;
                }
                edist[i][j] = Math.min(d1, d2);
            }
        }
        return edist[edist.length - 1][edist[0].length - 1];
    }

    public static void main(String[] args) {
        String s1 = "exponential";
        String s2 = "polynomial";
        System.out.println(EditDistance.dist(s1, s2));
    }
}
