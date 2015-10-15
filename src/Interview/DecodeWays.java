package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 1/15/15.
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * Given an encoded message containing digits, determine the total number of ways to decode it.
 *
 * For example,
 * Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).
 *
 * The number of ways decoding "12" is 2.
 */
public class DecodeWays {
    /*
     * return all the possible decode patterns wrapped in a list
     */
    public static List<String> decode(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        for (int i = 1; i <= 26; i ++) {
            map.put(String.valueOf(i), String.valueOf((char)('A' + (i-1))));
        }
        List<List<String>> dp = new ArrayList<>();
        for (int i = 0; i < s.length(); i ++) {
            dp.add(new LinkedList());
        }
        if (map.containsKey(String.valueOf(s.charAt(0)))) {
            dp.get(0).add(map.get(String.valueOf(s.charAt(0))));
        }
        for (int i = 1; i < s.length(); i ++) {
            if (map.containsKey(s.substring(i, i + 1))) {
                String curr = map.get(s.substring(i, i + 1));
                if (dp.get(i-1).size() > 0) {
                    List<String> prev = dp.get(i-1);
                    for (int j = 0; j < prev.size(); j ++) {
                        dp.get(i).add(prev.get(j) + curr);
                    }
                }
            }

            if (map.containsKey(s.substring(i-1, i + 1))) {
                String curr = map.get(s.substring(i-1, i + 1));
                if (i >= 2 && dp.get(i-2).size() > 0) {
                    List<String> prev2 = dp.get(i-2);
                    for (int j = 0; j < prev2.size(); j ++) {
                        dp.get(i).add(prev2.get(j) + curr);
                    }
                }
                else if (i - 2 <= 0) {
                    dp.get(i).add(curr);
                }
            }

        }
        return dp.get(dp.size()-1);
    }

    public static void main(String[] args) {
        String s = "5223";
        List<String> res = decode(s);
        System.out.println(Arrays.toString(res.toArray()));
    }
}
