package Interview;

import java.util.HashMap;

/**
 * Created by yongyangyu on 11/24/14.
 */
public class StrRemoveDuplicate {
    public static String removeDuplicate(String s) {
        StringBuilder sb = new StringBuilder();
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < s.length(); i ++) {
            char ch = s.charAt(i);
            if (!map.containsKey((int)ch)) {
                map.put((int)ch, 1);
                sb.append(ch);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "abaaabbcdeee";
        System.out.println(removeDuplicate(s));
    }
}
