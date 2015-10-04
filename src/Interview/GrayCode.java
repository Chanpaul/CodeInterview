package Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yongyangyu on 10/3/15.
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 *
 * Given a non-negative integer n representing the total number of bits in the code, print the
 * sequence of gray code. A gray code sequence must begin with 0.
 *
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 *
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 */
public class GrayCode {
    public List<Integer> grayCode(int n) {
        List<Integer> gray = new ArrayList<>();
        List<String> bin = new ArrayList<>();
        if (n == 0) {
            gray.add(0);
            return gray;
        }
        bin.add("0");
        bin.add("1");
        while (--n >= 1) {
            List<String> tmp = new ArrayList<>();
            for (String s: bin) {
                tmp.add("0" + s);
            }
            for (int i = bin.size() - 1; i >= 0; i --) {
                tmp.add("1" + bin.get(i));
            }
            bin = tmp;
        }
        for (String s: bin) {
            gray.add(Integer.parseInt(s, 2));
        }
        return gray;
    }

    public static void main(String[] args) {
        List<Integer> gray = new GrayCode().grayCode(2);
        System.out.println(Arrays.toString(gray.toArray()));
    }
}
