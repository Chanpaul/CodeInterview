package Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yongyangyu on 11/5/15.
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return all possible palindrome partitioning of s.
 *
 * For example, given s = "aab",
 * Return
 *
 *  [
 *    ["aa","b"],
 *    ["a","a","b"]
 *  ]
 *
 */
public class PalindromePartition {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        if (s.length() == 0) {
            res.add(new ArrayList<>());
            return res;
        }
        if (s.length() == 1) {
            List<String> curr = new ArrayList<>();
            curr.add(s);
            res.add(curr);
            return res;
        }
        for (int i = 1; i <= s.length(); i ++) {
            if (isPalindrome(s.substring(0, i))) {
                String curr = s.substring(0, i);
                List<List<String>> rest = partition(s.substring(i));
                for (List<String> elem: rest) {
                    elem.add(0, curr);
                    res.add(elem);
                }
            }
        }
        return res;
    }

    private boolean isPalindrome(String s) {
        if (s.length() == 0 || s.length() == 1) return true;
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i) != s.charAt(j)) return false;
            i ++;
            j --;
        }
        return true;
    }

    public static void main(String[] args) {
        PalindromePartition pp = new PalindromePartition();
        List<List<String>> res = pp.partition("");
        for (List<String> x: res) {
            System.out.println(Arrays.toString(x.toArray()));
        }
    }
}
