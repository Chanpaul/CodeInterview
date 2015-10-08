package Interview;

/**
 * Created by yongyangyu on 10/8/15.
 * Given a string S and a string T, find the minimum window in S
 * which will contain all the characters in T in complexity O(n).
 *
 * For example,
 * S = "ADOBECODEBANC"
 * T = "ABC"
 * Minimum window is "BANC".
 * Note:
 * If there is no such window in S that covers all characters in T,
 * return the empty string "".
 *
 * If there are multiple such windows, you are guaranteed that there will
 * always be only one unique minimum window in S.
 */
public class MinWindowSubstring {
    public String minWindow(String s, String t) {
        String res = "";
        // create two arrays as hash tables for fast access
        int[] needToFind = new int[256];
        int[] hasFound = new int[256];
        for (int i = 0; i < t.length(); i ++) {
            needToFind[t.charAt(i)] ++;
        }
        int count = 0;
        int start = 0, end = 0;
        while (end < s.length()) {
            if (needToFind[s.charAt(end)] == 0) {
                end ++;
                continue;
            }
            hasFound[s.charAt(end)] ++;
            if (hasFound[s.charAt(end)] <= needToFind[s.charAt(end)]) count ++;
            if (count == t.length()) {
                // advance start as far as possible
                while (needToFind[s.charAt(start)] == 0 ||
                       hasFound[s.charAt(start)] > needToFind[s.charAt(start)]) {
                    if (hasFound[s.charAt(start)] > needToFind[s.charAt(start)]) {
                        hasFound[s.charAt(start)] --;
                    }
                    start ++;
                }
                if (res.length() == 0) {
                    res = s.substring(start, end+1);
                }
                else {
                    String tmp = s.substring(start, end+1);
                    res = res.length() < tmp.length() ? res : tmp;
                }
            }
            end ++;
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(new MinWindowSubstring().minWindow(s, t));
    }
}
