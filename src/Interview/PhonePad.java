package Interview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by yongyangyu on 12/2/14.
 */
public class PhonePad {
    public static List<String> numberToWords(String s) {
        Map<Character, String[]> map = new HashMap<Character, String[]>();
        map.put('0', new String[]{""});
        map.put('1', new String[]{""});
        map.put('2', new String[]{"A", "B", "C"});
        map.put('3', new String[]{"D", "E", "F"});
        map.put('4', new String[]{"G", "H", "I"});
        map.put('5', new String[]{"J", "k", "L"});
        map.put('6', new String[]{"M", "N", "O"});
        map.put('7', new String[]{"P", "Q", "R", "S"});
        map.put('8', new String[]{"T", "U", "V"});
        map.put('9', new String[]{"W", "X", "Y", "Z"});
        List<String> curr = new LinkedList<String>();
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (curr.size() == 0) {
                for (String x : map.get(c)) {
                    curr.add(x);
                }
            }
            else {
                List<String> update = new LinkedList<String>();
                String[] chs = map.get(c);
                for (String x : chs) {
                    for (String y : curr) {
                        update.add(y+x);
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
