package Interview;

/**
 * Created by yongyangyu on 10/15/15.
 * Write a function to find the longest common prefix string amongst an array of strings.
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = null;
        int len = Integer.MAX_VALUE;
        // look for the shortest string in the array
        for (String str: strs) {
            if (str.length() < len) {
                len = str.length();
                prefix = str;
            }
        }
        int matchLen = Integer.MAX_VALUE;
        // compare each element with the shortest one
        for (String str: strs) {
            int match = 0;
            for (int i = 0; i < prefix.length(); i ++ ) {
                if (prefix.charAt(i) == str.charAt(i)) {
                    match ++;
                }
                else {
                    break;
                }
            }
            matchLen = Math.min(matchLen, match);
        }
        return prefix.substring(0, matchLen);
    }
}
