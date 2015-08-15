package Interview;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by yongyangyu on 1/11/15.
 */
public class NumberNoRepeatDigit {
    public static List<Integer> getNoRepDigitNumbers(int[] arr) {
        List<Integer> res = new LinkedList<Integer>();
        for (int x : arr) {
            Map<Character, Boolean> map = new HashMap<Character, Boolean>();
            String curr = String.valueOf(x);
            boolean dup = false;
            for (int i = 0; i < curr.length(); i ++) {
                char ch = curr.charAt(i);
                if (map.containsKey(ch)) {
                    dup = true;
                    break;
                }
                else {
                    map.put(ch, true);
                }
            }
            if (!dup) {
                res.add(x);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] A = {123, 1222, 1223, 4353, 435};
        List<Integer> res = getNoRepDigitNumbers(A);
        for (Integer x : res) {
            System.out.println(x);
        }
    }
}
