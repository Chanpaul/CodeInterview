package Interview;

import java.util.HashMap;

/**
 * Created by yongyangyu on 11/19/14.
 * Given two strings s and t, determine if they are isomorphic.
 *
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 *
 * All occurrences of a character must be replaced with another character while
 * preserving the order of characters. No two characters may map to the same character but
 * a character may map to itself.
 *
 * For example,
 * Given "egg", "add", return true.
 *
 * Given "foo", "bar", return false.
 *
 * Given "paper", "title", return true.
 */
public class StrIsomorphic {
    public static boolean testIsomorphic(String s1, String s2) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        if (s1.length() != s2.length()) {
            return false;
        }
        for (int i = 0; i < s1.length(); i ++) {
            int ch1 = (int)s1.charAt(i);
            int ch2 = (int)s2.charAt(i);
            if (map.containsKey(ch1)) {
                if (map.get(ch1) != ch2) {
                    return false;
                }
            }
            else {
                if (map.containsValue(ch2)) {
                    return false;
                }
                map.put(ch1, ch2);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(testIsomorphic("foo", "app")); // true
        System.out.println(testIsomorphic("bar", "foo")); // false
        System.out.println(testIsomorphic("turtle", "tletur")); // true
        System.out.println(testIsomorphic("ab", "ca")); // true
        System.out.println(testIsomorphic("egg", "add")); // true
        System.out.println(testIsomorphic("paper", "title")); // true
    }
}
