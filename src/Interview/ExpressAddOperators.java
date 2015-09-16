package Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yongyangyu on 9/16/15.
 * Given a string that contains only digits 0-9 and a target value,
 * return all possibilities to add operators +, -, or * between the digits
 * so they evaluate to the target value.
 *
 * Examples:
 * "123", 6 -> ["1+2+3", "1*2*3"]
 * "232", 8 -> ["2*3+2", "2+3*2"]
 * "00", 0 -> ["0+0", "0-0", "0*0"]
 * "3456237490", 9191 -> []
 *
 */
public class ExpressAddOperators {
    public List<String> addOperators(String num, int target) {
        List<String> resNum = new ArrayList<>();
        List<String> resOps = new ArrayList<>();
        for (int i = 2; i <= num.length(); i ++) {
            generateNums(i, num, resNum, resOps, target);
        }
        return resOps;
    }

    private void generateNums(int nparts, String remains, List<String> res, List<String> resOps, int target) {
        if (remains.length() == 0) return;
        if (nparts == 1) {
            res.add(remains);
            generateOps(res.size() - 1, "", res, target, resOps);
            res.remove(res.size() - 1);
            return;
        }
        for (int i = 1; i < remains.length(); i ++) {
            res.add(remains.substring(0, i));
            generateNums(nparts - 1, remains.substring(i), res, resOps, target);
            res.remove(res.size() - 1);
        }

    }


    private void generateOps(int len, String ops, List<String> numList, int target, List<String> res) {
        if (len < 0) return;
        if (len == 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(numList.get(0));
            for (int i = 0; i < ops.length(); i ++) {
                sb.append(ops.charAt(i));
                sb.append(numList.get(i+1));
            }
            BasicCalculator bc = new BasicCalculator();
            if (bc.calculateDirect(sb.toString()) == target) {
                res.add(sb.toString());
            }
            return;
        }
        generateOps(len - 1, ops + "+", numList, target, res);
        generateOps(len - 1, ops + "-", numList, target, res);
        generateOps(len - 1, ops + "*", numList, target, res);
    }

    public static void main(String[] args) {
        ExpressAddOperators expr = new ExpressAddOperators();
        System.out.println(Arrays.toString(expr.addOperators("123", 6).toArray()));
        System.out.println(Arrays.toString(expr.addOperators("1000000009", 9).toArray()));
        System.out.println(Arrays.toString(expr.addOperators("123456789", 45).toArray()));
    }
}
