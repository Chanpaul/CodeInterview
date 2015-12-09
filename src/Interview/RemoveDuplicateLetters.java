package Interview;


import java.util.*;

/**
 * Created by yongyangyu on 12/9/15.
 * Given a string which contains only lowercase letters, remove duplicate letters so that every
 * letter appear once and only once. You must make sure your result is the smallest in lexicographical
 * order among all possible results.
 *
 * Example:
 * Given "bcabc"
 * Return "abc"
 *
 * Given "cbacdcbc"
 * Return "acdb"
 */
public class RemoveDuplicateLetters {
    public String removeDup(String s) {
        Map<Character, Integer> lastPos = new HashMap<>();
        for (int i = 0; i < s.length(); i ++) { // record last position of each letter
            lastPos.put(s.charAt(i), i);
        }
        StringBuilder sb = new StringBuilder();
        int len = lastPos.keySet().size();
        int start = 0, end = findMin(lastPos); // find the smallest letter from start up to end
        for (int i = 0; i < len; i ++) { // run at most 26 times
            char curr = 'z' + 1;
            for (int pos = start; pos <= end; pos ++) {
                if (lastPos.containsKey(s.charAt(pos)) &&
                        s.charAt(pos) < curr) {
                    curr = s.charAt(pos);
                    start = pos + 1; // update for next for loop
                }
            }
            sb.append(curr);
            lastPos.remove(curr);
            if (s.charAt(end) == curr) end = findMin(lastPos);
        }
        return sb.toString();
    }

    private int findMin(Map<Character, Integer> lastPos) {
        int res = Integer.MAX_VALUE;
        for (int x : lastPos.values()) {
            if (res > x) res = x;
        }
        return res;
    }


    public static void main(String[] args) {
        RemoveDuplicateLetters rdl = new RemoveDuplicateLetters();
        System.out.println(rdl.removeDup("bbcaac"));
    }
}
