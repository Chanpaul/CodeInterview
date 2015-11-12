package Interview;

import java.util.Stack;

/**
 * Created by yongyangyu on 11/12/15.
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 *
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 *
 * Some examples:
 * ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
 * ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6
 */
public class ReversePolishNotation {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String s: tokens) {
            if (s.length() == 1 && !Character.isDigit(s.charAt(0))) {
                int op2 = stack.pop(), op1 = stack.pop();
                switch (s) {
                    case "+":
                        stack.push(op1 + op2);
                        break;
                    case "-":
                        stack.push(op1 - op2);
                        break;
                    case "*":
                        stack.push(op1 * op2);
                        break;
                    case "/":
                        stack.push(op1 / op2);
                        break;
                }
            }
            else {
                stack.push(Integer.parseInt(s));
            }
        }
        return stack.peek();
    }

    public static void main(String[] args) {
        String[] tokens = {"4", "13", "5", "/", "+"};
        System.out.println(new ReversePolishNotation().evalRPN(tokens));
    }
}
