package Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yongyangyu on 12/12/15.
 * Given a list of integer, a list of symbols in [+,-,*,/], and a target number N,
 * find an expression that evaluates to N, or return that it is not possible
 */
public class CountDownExpression {
    public class Result {
        String output;
        boolean success;
    }

    public Result expressions(List<Integer> numbers, int curr, int target) {
        Result midResult = new Result();
        if (numbers.isEmpty() && curr == target) {
            midResult.success = true;
            midResult.output = "";
            return midResult;
        }
        for (Integer x: numbers) {
            List<Integer> newList = new ArrayList<>(numbers);
            newList.remove(x);
            if (newList.isEmpty()) {
                if (curr - x == target) {
                    midResult.success = true;
                    midResult.output = "-" + x;
                    return midResult;
                }
                else if (curr + x == target) {
                    midResult.success = true;
                    midResult.output = "+" + x;
                    return midResult;
                }
                else if (curr * x == target) {
                    midResult.success = true;
                    midResult.output = "*" + x;
                    return midResult;
                }
                else if (curr / x == target) {
                    midResult.success = true;
                    midResult.output = "/" + x;
                    return midResult;
                }
                else {
                    midResult.success = false;
                    midResult.output = "false" + x;
                    return midResult;
                }
            }
            else {
                midResult = expressions(newList, curr - x, target);
                if (midResult.success) {
                    midResult.output = "-" + x + midResult.output;
                    return midResult;
                }
                midResult = expressions(newList, curr + x, target);
                if (midResult.success) {
                    midResult.output = "+" + x + midResult.output;
                    return midResult;
                }
                midResult = expressions(newList, curr * x, target);
                if (midResult.success) {
                    midResult.output = "*" + x + midResult.output;
                    return midResult;
                }
                midResult = expressions(newList, curr / x, target);
                if (midResult.success) {
                    midResult.output = "/" + x + midResult.output;
                    return midResult;
                }
            }
        }
        return midResult;
    }

    public static void main(String[] args) {
        CountDownExpression cde = new CountDownExpression();
        List<Integer> list = Arrays.asList(1,50,3,6,7);
        for (Integer x: list) {
            List<Integer> run = new ArrayList<>(list);
            run.remove(x);
            Result res = cde.expressions(run, x, 60);
            if (res.success) {
                res.output = x + res.output;
                System.out.println(res.output);
                return;
            }
        }
    }
}
