package Interview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by yongyangyu on 12/2/14.
 */
public class PhonePad {
    public static List<String> numberToWords(String digits) {
        Map<Character, String> map = new HashMap<>();
        map.put('0', "");
        map.put('1', "");
        map.put('2', "ABC");
        map.put('3', "DEF");
        map.put('4', "GHI");
        map.put('5', "JKL");
        map.put('6', "MNO");
        map.put('7', "PQRS");
        map.put('8', "TUV");
        map.put('9', "WXYZ");
        List<String> curr = new LinkedList<>();
        for (int i = 0; i < digits.length(); i ++) {
            char c = digits.charAt(i);
            if (curr.size() == 0) {
                String t = map.get(c);
                for (int j = 0; j < t.length(); j ++) {
                    curr.add(String.valueOf(t.charAt(j)));
                }
            }
            else {
                List<String> update = new LinkedList<>();
                String chs = map.get(c);
                for (int j = 0; j < chs.length(); j ++) {
                    for (String y : curr) {
                        update.add(y + String.valueOf(chs.charAt(j)));
                    }
                }
                curr = update;
            }
        }
        return curr;
    }

    public static void main(String[] args) {
        String s = "765";
        for (String x : numberToWords(s)) {
            System.out.println(x);
        }
    }
}
