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
    public int ways(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<String, String> numToAlphaBet = new HashMap<>();
        for (int i = 0; i < 26; i ++) {
            numToAlphaBet.put((i+1) + "", (char) ('A'+i) + "");
        }
        int[] dp = new int[s.length()+1];
        dp[0] = 1;
        for (int i = 1; i < dp.length; i ++) {
            if (numToAlphaBet.containsKey(s.charAt(i-1) + "")) {
                dp[i] += dp[i-1];
            }
            if (i >= 2 && numToAlphaBet.containsKey(s.substring(i-2, i))) {
                dp[i] += dp[i-2];
            }
        }
        return dp[dp.length-1];
    }

    /*
     * return all the possible decode patterns wrapped in a list
     */
    public List<String> decode(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }
        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < 26; i ++) {
            map.put((i+1) + "", (char) ('A' + i) + "");
        }
        List<List<String>> dp = new ArrayList<>();
        for (int i = 0; i < s.length()+1; i ++) {
            dp.add(new ArrayList<>());
        }
        dp.get(0).add("");
        for (int i = 1; i < dp.size(); i ++) {
            if (map.containsKey(s.charAt(i-1) + "")) {
                String curr = map.get(s.charAt(i-1)+"");
                for (String x: dp.get(i-1)) {
                    dp.get(i).add(x+curr);
                }
            }
            if (i >= 2 && map.containsKey(s.substring(i-2, i))) {
                String curr = map.get(s.substring(i-2, i));
                for (String x: dp.get(i-2)) {
                    dp.get(i).add(x+curr);
                }
            }
        }
        return dp.get(dp.size()-1);
    }

    public static void main(String[] args) {
        String s = "1111";
        DecodeWays dways = new DecodeWays();
        List<String> res = dways.decode(s);
        System.out.println(Arrays.toString(res.toArray()));
        System.out.println(dways.ways(s));
    }
}
