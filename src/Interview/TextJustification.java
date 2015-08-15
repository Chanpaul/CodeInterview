package Interview;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yongyangyu on 12/16/14.
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
