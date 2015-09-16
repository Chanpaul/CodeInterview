package Interview;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yongyangyu on 11/24/14.
 */
public class Parenthesis {
    public static void genParen(int left, int right, String s, List<String> rval) {
        if (left == 0 && right == 0) {
            rval.add(s);
        }
        if (left > 0) {
            genParen(left - 1, right + 1, s + "(", rval);
        }
        if (right > 0) {
            genParen(left, right - 1, s + ")", rval);
        }
    }

    public static void main(String[] args) {
        List<String> rval = new LinkedList<>();
        genParen(3, 0, "", rval);
        for (String s : rval) {
            System.out.println(s);
        }
    }
}
