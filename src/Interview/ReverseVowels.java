package Interview;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by yongyangyu on 5/3/16.
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * Example 1:
 * Given s = "hello", return "holle".
 *
 * Example 2:
 * Given s = "leetcode", return "leotcede".
 */
public class ReverseVowels {
    public String reverseVowels(String s) {
        Set<Character> vowels = new HashSet<>();
        vowels.add('a'); vowels.add('e'); vowels.add('i');
        vowels.add('o'); vowels.add('u');
        vowels.add('A'); vowels.add('E'); vowels.add('I');
        vowels.add('O'); vowels.add('U');
        StringBuilder sb = new StringBuilder(s);
        int i = 0, j = sb.length() - 1;
        while (i < j) {
            while (i < s.length() && !vowels.contains(sb.charAt(i))) {
                i ++;
            }
            while (j >= 0 && !vowels.contains(sb.charAt(j))) {
                j --;
            }
            if (i < j && j >= 0 && i < s.length()) {
                char tmp = sb.charAt(i);
                sb.setCharAt(i, sb.charAt(j));
                sb.setCharAt(j, tmp);
                i ++;
                j --;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "leetcode";
        ReverseVowels rv = new ReverseVowels();
        System.out.println(rv.reverseVowels(s));
    }
}
