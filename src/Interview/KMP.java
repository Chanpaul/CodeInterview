package Interview;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yongyangyu on 10/4/15.
 * Implementation of KMP algo for matching a pattern string against
 * a long text string.
 */
public class KMP {
    // Using functional iteration to compute the pi function
    public int[] prefixFunction(String pattern) {
        int[] pi = new int[pattern.length()];
        int k = 0;
        for (int q = 1; q < pattern.length(); q ++) {
            while (k > 0 && pattern.charAt(k) != pattern.charAt(q))
                k = pi[k];
            if (pattern.charAt(k) == pattern.charAt(q))
                k ++;
            pi[q] = k;
        }
        return pi;
    }

    public List<Integer> matcher(String pattern, String target) {
        List<Integer> res = new LinkedList<>();
        int n = target.length(), m = pattern.length();
        int[] pi = prefixFunction(pattern);
        int q = 0;
        for (int i = 1; i < n; i ++) {
            while (q > 0 && pattern.charAt(q) != target.charAt(i))
                q = pi[q];
            if (pattern.charAt(q) == target.charAt(i))
                q ++;
            if (q == m) {
                res.add(i - m + 1);
                q = pi[q-1];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        String pattern = "aba";
        String target = "bacbabababbcbab";
        KMP kmp = new KMP();
        List<Integer> pos = kmp.matcher(pattern, target);
        System.out.println(Arrays.toString(pos.toArray()));
    }
}
