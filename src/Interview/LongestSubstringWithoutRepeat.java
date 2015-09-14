package Interview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yongyangyu on 2/10/15.
 * Given a string, find the length of the longest substring without repeating characters.
 * For example, the longest substring without repeating letters for "abcabcbb" is "abc",
 * which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.
 */
public class LongestSubstringWithoutRepeat {
    public int lengthOfLongestSubstring(String s) {
        int start = 0;
        int maxLen = 0;
        Map<Character, Integer> charToPosition = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!charToPosition.containsKey(s.charAt(i))) {
                charToPosition.put(s.charAt(i), i);
            }
            else {
                maxLen = Math.max(maxLen, i - start);
                start = Math.max(start, charToPosition.get(s.charAt(i)) + 1); // start cannot get smaller
                charToPosition.put(s.charAt(i), i);
            }
        }
        maxLen = Math.max(maxLen, s.length() - start);
        return maxLen;
    }

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        String s2 = "c";
        LongestSubstringWithoutRepeat lswr = new LongestSubstringWithoutRepeat();
        System.out.println(lswr.lengthOfLongestSubstring(s1));
        System.out.println(lswr.lengthOfLongestSubstring(s2));
    }
}
