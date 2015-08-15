package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 7/27/15.
 * Given a string of numbers and operators, return all possible results from computing all the
 * different possible ways to group numbers and operators. The valid operators are `+`, `-` and `*`.
 * Example 1,
 * Input: "2-1-1".
 *   ((2-1)-1) = 0
 *   (2-(1-1)) = 2
 * Output: [0, 2]
 * Example 2.
 * Input: "2*3-4*5"
 *   (2*(3-(4*5))) = -34
 *   ((2*3)-(4*5)) = -14
 *   ((2*(3-4))*5) = -10
 *   (2*((3-4)*5)) = -10
 *   (((2*3)-4)*5) = 10
 * Output: [-34, -14, -10, -10, 10]
 */
public class DifferentWaysParentheses {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> result = new ArrayList<>();
        List<Integer> position = parseOperator(input);
        if (position.size() == 0) {
            result.add(Integer.parseInt(input));
            return result;
        }
        List<Integer> operands = parseOperand(input, position);
        List<Character> op = new ArrayList<>();
        for (int pos: position) {
            op.add(input.charAt(pos));
        }
        parentheses(op.size(), 0, "", operands, op, result);
        Collections.sort(result);
        return result;
    }

    // parse the operators for `+`, `-`, and `*`
    private List<Integer> parseOperator(String input) {
        Set<Character> op = new HashSet<>();
        op.add('+');
        op.add('-');
        op.add('*');
        List<Integer> position = new ArrayList<>();
        for (int i = 0; i < input.length(); i ++) {
            if (op.contains(input.charAt(i))) {
                position.add(i);
            }
        }
        return position;
    }

    private List<Integer> parseOperand(String input, List<Integer> position) {
        List<Integer> x = new ArrayList<>();
        for (int i = 0; i < position.size(); i ++) {
            if (i == 0) {
                x.add(Integer.parseInt(input.substring(0, position.get(i))));
            }
            else {
                x.add(Integer.parseInt(input.substring(position.get(i - 1) + 1, position.get(i))));
            }
        }
        x.add(Integer.parseInt(input.substring(position.get(position.size() - 1) + 1, input.length())));
        return x;
    }

    public void parentheses(int left, int right, String cur, List<Integer> operands, List<Character> op, List<Integer> result) {
        if (left > 0) {
            parentheses(left - 1, right + 1, cur + "(", operands, op, result);
        }
        if (right > 0) {
            parentheses(left, right - 1, cur + ")", operands, op, result);
        }
        if (left == 0 && right == 0) {
            // convert cur string of ()'s to values
            Stack<Integer> operand = new Stack<>();
            Stack<Character> operator = new Stack<>();
            int sp = 0, sq = 0;
            for (int i = 0; i < cur.length(); i ++) {
                if (cur.charAt(i) == '(') {
                    operand.push(operands.get(sp++));
                    operator.push(op.get(sq++));
                }
                else {
                    if (sp <= operands.size() - 1 && operand.size() == operator.size()) { // (())()
                        operand.push(operands.get(sp++));
                    }
                    int op2 = operand.pop();
                    int op1 = operand.pop();
                    operand.push(eval(op1, op2, operator.pop()));
                }
            }
            result.add(operand.pop());
        }
    }

    private int eval(int op1, int op2, char op) {
        switch (op) {
            case '+': return op1 + op2;
            case '-': return op1 - op2;
            case '*': return op1 * op2;
            default: return 0;
        }
    }

    public static void main(String[] args) {
        String input1 = "0+1";
        String input2 = "2*3-4*5";
        DifferentWaysParentheses ways = new DifferentWaysParentheses();
        List<Integer> res = ways.diffWaysToCompute(input1);
        System.out.println(Arrays.toString(res.toArray()));
        res = ways.diffWaysToCompute(input2);
        System.out.println(Arrays.toString(res.toArray()));

    }
}
