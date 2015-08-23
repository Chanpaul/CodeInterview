package Interview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yongyangyu on 12/20/14.
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 *
 * For example:
 *
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 */
public class ExcelTitle {
    public static String convertToTitle(int n) {
        Map<Integer, String> map = new HashMap<Integer, String>();
        for (int i = 1; i <= 26; i ++) {
            char ch = (char)('A' + i - 1);
            map.put(i, new StringBuilder().append(ch).toString());
        }
        map.put(0, "");
        StringBuilder sb = new StringBuilder();
        while(n / 26 > 0) {
            if (n % 26 == 0) {
                sb.insert(0, map.get(26));
                n -= 26;
            } else {
                sb.insert(0, map.get(n % 26));
                n -= n % 26;
            }
            n /= 26;
        }
        sb.insert(0, map.get(n % 26));
        return sb.toString();
    }

    public static int titleToNumber(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i ++) {
            res = res * 26 + (s.charAt(i) - 'A' + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(convertToTitle(1048));
        System.out.println(titleToNumber("AB"));
    }
}
