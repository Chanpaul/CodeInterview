package Interview;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yongyangyu on 12/3/14.
 */
public class Roman {
    public static int toInteger(String s) {
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        if (s == null) {  // only convert positive integers
            return -1;
        }
        else if (s.length() == 1) {
            return map.get(s.charAt(0));
        }
        else {
            char last = s.charAt(0);
            res += map.get(last);
            for (int i = 1; i < s.length(); i ++) {
                char curr = s.charAt(i);
                if (map.get(last) < map.get(curr)) {
                    res -= 2 * map.get(last);
                }
                res += map.get(curr);
                last = curr;
            }
        }
        return res;
    }

    public static String intToRoman(int num) {
        int [] L =       {1000, 900, 500, 400, 100,   90,  50,   40,   10,   9,   5,    4,   1};
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (num != 0) {
            int cnt = num / L[i];
            num %= L[i];
            while (cnt != 0) {
                sb.append(roman[i]);
                cnt --;
            }
            i ++;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s1 = "XXXXXIIIIIIIII";
        String s2 = "LVIIII";
        String s3 = "LIX";
        System.out.println(toInteger(s1)); // 59
        System.out.println(toInteger(s2)); // 59
        System.out.println(toInteger(s3)); // 59
        System.out.println(intToRoman(59));
    }
}
