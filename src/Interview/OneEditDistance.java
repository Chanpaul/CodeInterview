package Interview;

/**
 * Created by yongyangyu on 10/3/15.
 * Given two strings S and T, determine if they are both one edit distance apart.
 */
public class OneEditDistance {
    public boolean isOneEditDistance(String s, String t) {
        int lens = s.length();
        int lent = t.length();
        if (lens == 0) return lent == 1;
        if (lent == 0) return lens == 1;
        if (lens == lent) { // same length, count number of different positions
            int count = 0;
            for (int i = 0; i < lens; i ++) {
                if (s.charAt(i) != t.charAt(i)) count ++;
            }
            if (count > 1) return false;
            return count == 1;
        }
        // different lengths, try removing one char from longer string to match with the shorter one
        else if (lens + 1 == lent || lent + 1 == lens) {
            String slong = lens > lent ? s : t;
            String sshort = lens < lent ? s : t;
            StringBuilder sb = new StringBuilder(slong);
            for (int i = 0; i < sb.length(); i ++) {
                char tmp = sb.charAt(i);
                sb.deleteCharAt(i);
                if (sb.toString().equals(sshort)) return true;
                sb.insert(i, tmp);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        String s = "ab";
        String t = "acb";
        System.out.println(new OneEditDistance().isOneEditDistance(s, t));
    }
}
