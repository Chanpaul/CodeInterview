package Interview;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by yongyangyu on 5/25/15.
 * Given a string S, you are allowed to convert it to a palindrome by 
 * adding characters in front of it. Find and return the shortest palindrome 
 * you can find by performing this transformation.

 * For example:

 * Given "aacecaaa", return "aaacecaaa".

 * Given "abcd", return "dcbabcd".
 */
public class ShortestPalindrome {
    public static String shortestPalindrome(String s) {
        Set<Character> elemSet = new HashSet<Character>();
        for (int i = 0; i < s.length(); i ++) {
            elemSet.add(s.charAt(i));
        }
        if (elemSet.size() == 1) {
            return s;
        }
        StringBuilder sb = new StringBuilder(s);
        StringBuilder r = sb.reverse();
        List<Character> prepend = new LinkedList<Character>();
        while(!isPrefix(r.toString(), s)) {
            prepend.add(r.charAt(0));
            r.deleteCharAt(0);
        }
        StringBuilder res = new StringBuilder();
        for (char letter : prepend) {
            res.append(letter);
        }
        res.append(s);
        return res.toString();
    }

    private static boolean isPrefix(String s, String t) {
        if (s.length() == 0) {
            return true;
        }
        if (s.length() > t.length()) {
            return false;
        }
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) != t.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "aacecaaa";
        System.out.println(shortestPalindrome(s));
    }
}
