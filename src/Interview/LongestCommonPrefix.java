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
        String prefix = strs[0];
        int len = prefix.length();
        // look for the shortest string in the array
        for (int i = 1; i < strs.length; i ++) {
            if (strs[i].length() < len) {
                len = strs[i].length();
                prefix = strs[i];
            }
        }
        int matchLen = Integer.MAX_VALUE;
        // compare each element with the shortest one
        for (int i = 0; i < strs.length; i ++) {
            String curr = strs[i];
            int match = 0;
            for (int j = 0; j < prefix.length(); j ++ ) {
                if (prefix.charAt(j) == curr.charAt(j)) {
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
