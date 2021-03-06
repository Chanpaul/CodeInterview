package Interview;

/**
 * Created by yongyangyu on 6/21/15.
 * compute the longest substring that is a palindrome
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if (s.length() == 0) {
            return s;
        }
        String longest = s.substring(0, 1);
        for (int i = 0; i < s.length() - 1; i ++) {
            String s1 = expandCenter(s, i, i);
            if (s1.length() > longest.length()) {
                longest = s1;
            }
            s1 = expandCenter(s, i, i + 1);
            if (s1.length() > longest.length()) {
                longest = s1;
            }
        }

        return longest;
    }

    private String expandCenter(String s, int l, int r) {
        while (l >= 0 && r <= s.length() - 1 && s.charAt(l) == s.charAt(r)) {
            l --;
            r ++;
        }
        return s.substring(l+1, r);
    }
}
