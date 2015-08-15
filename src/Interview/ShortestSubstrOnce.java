package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 1/15/15.
 */
public class ShortestSubstrOnce {
    public static String shortestSubstr(String s) {
        Set<String> res = new HashSet<String>();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (int len = 1; len <= s.length(); len ++) {
            res.clear();
            map.clear();
            for (int i = 0; i <= s.length()-len; i ++) {
                String curr = s.substring(i, i + len);
                if (!map.containsKey(curr)) {
                    map.put(curr, 1);
                    res.add(curr);
                }
                else {
                    if (res.contains(curr)) {
                        res.remove(curr);
                    }
                }
            }
            if (res.size() > 0) {
                Iterator<String> iter = res.iterator();
                if (iter.hasNext()) {
                    return iter.next();
                }
            }
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(shortestSubstr("aabbabbaab"));
        System.out.println(shortestSubstr("aaaa"));
    }
}
