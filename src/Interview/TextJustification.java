package Interview;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yongyangyu on 12/16/14.
 * Given an array of words and a length L, format the text such that each line has
 * exactly L characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as
 * you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of
 * spaces on a line do not divide evenly between words, the empty slots on the left will be
 * assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 *
 * For example,
 * words: ["This", "is", "an", "example", "of", "text", "justification."]
 * L: 16.
 *
 * Return the formatted lines as:
 * [
 *   "This    is    an",
 *   "example  of text",
 *   "justification.  "
 * ]
 * Note: Each word is guaranteed not to exceed L in length.
 * Corner Cases:
 * A line other than the last line might contain only one word. What should you do in this case?
 * In this case, that line should be left-justified.
 */
public class TextJustification {
    public static List<String> fullJustify(String[] words, int L) {
        List<String> res = new LinkedList<String>();
        int i = 0;
        while (i < words.length) {
            int count = words[i].length();
            int j = 1;
            int wl = 0;
            wl += count;
            while (i+j < words.length && count+1+words[i+j].length() <= L) {
                count += 1 + words[i+j].length();
                wl += words[i+j].length();
                j ++;
            }
            int slots = j - 1;
            int spaces = L - wl;
            int remains = 0;
            if (slots != 0)
                remains = spaces % slots;
            int numSpace = 0;
            if (slots == 0) {
                numSpace = spaces;
            }
            else {
                numSpace = (spaces - remains) / slots;
            }
            if (i + j < words.length) {
                StringBuilder sb = new StringBuilder();
                for (int k = i; k < i+remains; k ++) {
                    sb.append(words[k]);
                    for (int t = 0; t < numSpace + 1; t ++) {
                        sb.append(' ');
                    }
                }
                for (int k = i + remains; k < i + j; k++) {
                    sb.append(words[k]);
                    if (k != i + j - 1) {
                        for (int t = 0; t < numSpace; t++) {
                            sb.append(' ');
                        }
                    }
                }
                if (sb.length() < L) {
                    int diff = L - sb.length();
                    for (int t = 0; t < diff; t ++) {
                        sb.append(' ');
                    }
                }
                res.add(sb.toString());
            }
            else {
                StringBuilder sb = new StringBuilder();
                int cnt = 0;
                sb.append(words[i]);
                cnt += words[i].length();
                if (L > cnt) {
                    sb.append(' ');
                    cnt++;
                }
                for (int k = i + 1; k < i + j; k ++) {
                    sb.append(words[k]);
                    cnt += words[k].length();
                    sb.append(' ');
                    cnt ++;
                }
                for (int k = 0; k < L - cnt; k ++) {
                    sb.append(' ');
                }
                res.add(sb.toString());
            }
            i += j;
        }
        return res;
    }

    public static void main(String[] args) {
        int L = 6;
        String[] words = {"Listen","to","many,","speak","to","a","few."};
        List<String> res = fullJustify(words, L);
        for (String s : res) {
            System.out.print("\"");
            System.out.print(s);
            System.out.println("\"");
        }
    }
}
