package Interview;

/**
 * Created by yongyangyu on 12/1/14.
 * Given an input string, reverse the string word by word.
 *
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 */
public class ReverseWords {
    public static String reverseWords(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        StringBuilder res = new StringBuilder();
        int start = 0, end = 0;
        while (end < sb.length()) {
            while (end < sb.length() && sb.charAt(end) != ' ') end++;
            StringBuilder word = new StringBuilder(sb.substring(start, end));
            // test when to append a space
            if (res.length() > 0 && word.length() > 0) res.append(' ');
            res.append(word.reverse().toString());
            start = end + 1;
            end = start;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "the sky is blue";
        System.out.println(reverseWords(s));
    }
}
