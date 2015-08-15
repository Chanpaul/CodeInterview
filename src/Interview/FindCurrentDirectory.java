package Interview;

import java.util.Stack;

/**
 * Created by yongyangyu on 11/23/14.
 */
public class FindCurrentDirectory {
    public static String cd(String s1, String s2) {
        String [] curr = s1.split("/");
        Stack<String> stack = new Stack<String>();
        for (int i = 0; i < curr.length; i ++) {
            stack.push(curr[i]);
        }
        String [] path = s2.split("/");
        for (String x : path) {
            if (x.equals(".")) {
                continue;
            }
            else if (x.equals("..")) {
                stack.pop();
            }
            else {
                stack.push(x);
            }
        }
        if (stack.size() > 0) {
            String res = "";
            while (stack.size() > 1) {
                if (res.equals("")) {
                    res = stack.pop() + res;
                }
                else {
                    res = stack.pop() + "/" + res;
                }
            }
            res = stack.pop() + "/" + res;
            return res;
        }
        return null;
    }

    public static void main(String [] args) {
        System.out.println(FindCurrentDirectory.cd("a/b", "c/../d/e/../f"));
    }
}
