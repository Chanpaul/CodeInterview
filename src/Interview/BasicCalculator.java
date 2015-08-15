package Interview;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Created by yongyangyu on 6/9/15.
 * This code partially implements shunting-yard algorithm by Edsger Dijkstra.
 * @see <a href="http://en.wikipedia.org/wiki/Shunting-yard_algorithm">Shunting-yard algorithm</a>
 */
public class BasicCalculator {
    public List<String> parseTokens(String s) {
        List<String> res = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) == ' ') {
                continue;
            }
            sb.append(s.charAt(i));
        }
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < sb.length(); i ++) {
            if (Character.isDigit(sb.charAt(i))) {
                token.append(sb.charAt(i));
            }
            else {
                if (token.length() > 0) {
                    res.add(token.toString());
                    token = new StringBuilder();
                }
                res.add(String.valueOf(sb.charAt(i)));
            }
        }
        if (token.length() > 0) {
            res.add(token.toString());
        }
        return res;
    }

    /*
     * compute the precedence of the operator.
     */
    private int precedence(char ch) {
        if (ch == '+' || ch == '-') {
            return 0;
        }
        else if (ch == '*' || ch == '/') {
            return 1;
        }
        return -1;
    }

    public List<String> genRPN(List<String> tokens) {
        List<String> queue = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            if (Character.isDigit(token.charAt(0))) { // number
                queue.add(token);
            }
            else if (token.charAt(0) == '+' ||
                     token.charAt(0) == '-' ||
                     token.charAt(0) == '*' ||
                     token.charAt(0) == '/') { // operator
                // token operator's precedence is less than or equal to that of stack top's
                while (!stack.empty() && precedence(token.charAt(0)) <=
                        precedence(stack.peek().charAt(0))) {
                    queue.add(stack.pop());
                }
                stack.push(token);
            }
            else if (token.charAt(0) == '(') { // (
                stack.push(token);
            }
            else if (token.charAt(0) == ')') { // )
                while (!stack.empty() && (stack.peek().charAt(0) != '(')) {
                    queue.add(stack.pop());
                }
                if (!stack.empty() && stack.peek().charAt(0) == '(') {
                    stack.pop();
                }
            }
        }
        while (!stack.empty()) {
            queue.add(stack.pop());
        }
        return queue;
    }

    public int calculate(String s) {
        List<String> tokens = genRPN(parseTokens(s));
        Stack<Long> stack = new Stack<>();
        for (String token : tokens) {
            if (Character.isDigit(token.charAt(0))) {
                stack.push(Long.parseLong(token));
            }
            else if (token.charAt(0) == '+') {
                long op1 = stack.pop();
                long op2 = stack.pop();
                stack.push(op1 + op2);
            }
            else if (token.charAt(0) == '-') {
                long op2 = stack.pop();
                long op1 = stack.pop();
                stack.push(op1 - op2);
            }
            else if (token.charAt(0) == '*') {
                long op2 = stack.pop();
                long op1 = stack.pop();
                stack.push(op1 * op2);
            }
            else if (token.charAt(0) == '/') {
                long op2 = stack.pop();
                long op1 = stack.pop();
                stack.push(op1 / op2);
            }
        }
        return (int)(long)stack.peek();
    }

    public int calculateDirect(String s) {
        List<String> tokens = parseTokens(s);
        Stack<Long> output = new Stack<>();
        Stack<String> stack = new Stack<>();
        for (String token : tokens) {
            if (Character.isDigit(token.charAt(0))) { // number
                output.push(Long.parseLong(token));
            }
            else if (token.charAt(0) == '+' ||
                    token.charAt(0) == '-' ||
                    token.charAt(0) == '*' ||
                    token.charAt(0) == '/') { // operator
                while (!stack.empty() && precedence(token.charAt(0)) <=
                        precedence(stack.peek().charAt(0))) {
                    compute(output, stack);
                }
                stack.push(token);
            }
            else if (token.charAt(0) == '(') { // (
                stack.push(token);
            }
            else if (token.charAt(0) == ')') { // )
                while (!stack.empty() && (stack.peek().charAt(0) != '(')) {
                    compute(output, stack);
                }
                if (!stack.empty() && stack.peek().charAt(0) == '(') {
                    stack.pop();
                }
            }
        }
        while (!stack.empty()) {
            compute(output, stack);
        }
        return (int)(long)output.peek();
    }

    private void compute(Stack<Long> output, Stack<String> stack) {
        char op = stack.pop().charAt(0);
        long op2 = output.pop();
        long op1 = output.pop();
        switch (op) {
            case '+': output.push(op1 + op2);
                break;
            case '-': output.push(op1 - op2);
                break;
            case '*': output.push(op1 * op2);
                break;
            case '/': output.push(op1 / op2);
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        String s1 = "3+2*2";
        String s2 = " 3/2 ";
        String s3 = " 3+5 / 2 ";
        BasicCalculator bc = new BasicCalculator();
        System.out.println(bc.calculate(s1));
        System.out.println(bc.calculate(s2));
        System.out.println(bc.calculate(s3));
        System.out.println(bc.calculateDirect(s3));
        String s4 = "0-2147483648";
        System.out.println(bc.calculate(s4));
    }
}
