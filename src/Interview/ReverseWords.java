package Interview;

/**
 * Created by yongyangyu on 12/1/14.
 */
public class ReverseWords {
    public static String reverseWordsInSentence(String s) {
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        int i = 0, j = 0;
        while (j < sb.length()) {
            if (sb.substring(j, j+1).equals(" ")) {
                int start = i;
                int end = j-1;
                while (start < end) {
                    char tmp = sb.charAt(start);
                    sb.setCharAt(start, sb.charAt(end));
                    sb.setCharAt(end, tmp);
                    start ++;
                    end --;
                }
                i = j+1;
            }
            j ++;
        }
        if (i < sb.length()) {
            int start = i;
            int end = sb.length() - 1;
            while (start < end) {
                char tmp = sb.charAt(start);
                sb.setCharAt(start, sb.charAt(end));
                sb.setCharAt(end, tmp);
                start ++;
                end --;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "Alice likes Bob! what about you, Cindy?";
        System.out.println(reverseWordsInSentence(s));
    }
}
