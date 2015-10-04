package Interview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yongyangyu on 10/3/15.
 * Given a string, find the length of the longest substring T that
 * contains at most 2 distinct characters.
 *
 * For example, Given s = “eceba”,
 *
 * T is "ece" which its length is 3.
 */
public class LongestSubstringWithAtMostTwoDistinctChars {
    public int length(String s) {
        Map<Character, Integer> char2pos = new HashMap<>();
        int len = 0;
        for (int i = 0; i < s.length(); i ++) {
            if (char2pos.size() < 2) {
                if (!char2pos.containsKey(s.charAt(i))) {
                    char2pos.put(s.charAt(i), i);
                }
            }
            else {
                if (!char2pos.containsKey(s.charAt(i))) {
                    int minPos = Integer.MAX_VALUE;
                    for (int pos: char2pos.values()) {
                        if (pos < minPos) minPos = pos;
                    }
                    len = Math.max(len, i-minPos);
                    for (char c: char2pos.keySet()) {
                        if (minPos == char2pos.get(c)) {
                            char2pos.remove(c);
                            break;
                        }
                    }
                    char2pos.put(s.charAt(i), i);
                }
            }
        }
        int minPos = Integer.MAX_VALUE;
        for (int pos: char2pos.values()) {
            if (pos < minPos) minPos = pos;
        }
        len = Math.max(len, s.length() - minPos);
        return len;
    }

    public static void main(String[] args) {
        String s = "97778787778121112121225488";
        System.out.println(new LongestSubstringWithAtMostTwoDistinctChars().length(s));
    }
}
