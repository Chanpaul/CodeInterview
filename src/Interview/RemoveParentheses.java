package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 11/4/15.
 * Remove the minimum number of invalid parentheses in order to make the input string valid.
 * Return all possible results.
 * Note: The input string may contain letters other than the parentheses "(" and ")".
 * Examples:
 * "()())()" -> ["()()()", "(())()"]
 * "(a)())()" -> ["(a)()()", "(a())()"]
 * ")(" -> [""]
 *
 */
public class RemoveParentheses {
    public List<String> removeInvalidParentheses(String s) {
        List<String> res = new ArrayList<>();
        Set<String> set = new HashSet<>();
        if (s == null) return res;
        // BFS for each possible ( and )
        List<String> q = new ArrayList<>();
        Set<String> tmp = new HashSet<>();
        q.add(s);
        while (!q.isEmpty()) {
            String curr = q.remove(0);
            if (isValid(curr)) set.add(curr);
            for (int i = 0; i < curr.length(); i ++) {
                if (curr.charAt(i) == '(' || curr.charAt(i) == ')') {
                    String x = curr.substring(0, i) + curr.substring(i+1);
                    tmp.add(x);
                }
            }
            if (q.isEmpty()) {
                if (!set.isEmpty()) {
                    break;
                }
                if (!tmp.isEmpty()) {
                    for (String x: tmp) {
                        q.add(x);
                    }
                    tmp.clear();
                }
            }
        }
        res.addAll(set);
        return res;
    }

    // check if the string is a valid sequence of ( and )
    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i ++) {
            if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;
            if (s.charAt(i) == '(') stack.push('(');
            else if (s.charAt(i) == ')') {
                if (stack.isEmpty()) return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        RemoveParentheses rp = new RemoveParentheses();
        List<String> res = rp.removeInvalidParentheses("");
        System.out.println(Arrays.toString(res.toArray()));
    }
}
