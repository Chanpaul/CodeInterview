package Interview;

import java.util.Comparator;

/**
 * Created by yongyangyu on 11/18/14.
 */
public class StringLenComparator implements Comparator<String> {
    @Override
    public int compare(String s1, String s2) {
        if (s1.length() < s2.length()) {
            return -1;
        }
        if (s1.length() > s2.length()) {
            return 1;
        }
        return 0;
    }
}
