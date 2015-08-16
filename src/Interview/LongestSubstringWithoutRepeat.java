package Interview;

import java.util.HashSet;
import java.util.Set;
/**
 * Created by yongyangyu on 2/10/15.
 * Given a string, find the length of the longest substring without repeating characters.
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc",
 * which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
 */
public class LongestSubstringWithoutRepeat {
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n == 0) {
            return 0;
        }
        int maxlen = 0, i = 0, j = 0;
        Set<Character> set = new HashSet<Character>();
        while (j < n) {
            if (!set.contains(s.charAt(j))) {
                set.add(s.charAt(j));
                j ++;
            }
            else {
                maxlen = Math.max(maxlen, j - i);
                // find the repeat position
                while (s.charAt(i) != s.charAt(j)) {
                    set.remove(s.charAt(i));
                    i ++;
                }
                i ++;
                j ++;
            }
        }
        maxlen = Math.max(maxlen, n - i);
        return maxlen;
    }

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        String s2 = "bbbbbbbb";
        System.out.println(lengthOfLongestSubstring(s1));
        System.out.println(lengthOfLongestSubstring(s2));
    }
}
