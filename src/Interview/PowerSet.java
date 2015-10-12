package Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yongyangyu on 10/12/15.
 */
public class PowerSet {
    public List<List<Character>> powerSet(List<Character> S) {
        int n = S.size();
        List<List<Character>> result = new ArrayList<>();
        for (int i = 0; i < (1 << n); i ++) {
            List<Character> elem = new ArrayList<>();
            for (int j = 0; j < (int)(Math.log(i) / Math.log(2))+1; j ++) {
                if (((1 << j) & i) != 0)
                    elem.add(S.get(j));
            }
            result.add(elem);
        }
        return result;
    }

    public static void main(String[] args) {
        char[] set = {'A', 'B', 'C'};
        List<Character> S = new ArrayList<>();
        for (char s: set) {
            S.add(s);
        }
        List<List<Character>> result = new PowerSet().powerSet(S);
        for (List<Character> list: result) {
            System.out.println(Arrays.toString(list.toArray()));
        }
    }
}
