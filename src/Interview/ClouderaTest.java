package Interview;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by yongyangyu on 1/11/15.
 */
public class ClouderaTest {
    public static int commonMinHash(int[] A, int[] B) {
        Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
        for (int x : A) {
            if (!map.containsKey(x)) {
                map.put(x, true);
            }
        }
        int rval = Integer.MAX_VALUE;
        for (int x : B) {
            if (map.containsKey(x)) {
                if (x < rval) {
                    rval = x;
                }
            }
        }
        if (rval < Integer.MAX_VALUE) {
            return rval;
        }
        else {
            return -1;
        }
    }

    public static int commonMin(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        int i = 0, j = 0;
        while (i < A.length && j < B.length) {
            if (A[i] == B[j]) {
                return A[i];
            }
            else if (A[i] < B[j]) {
                i ++;
            }
            else {
                j ++;
            }
        }
        return -1;
    }

    public static int RPN(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int i = 0; i < s.length(); i ++) {
            char ch = s.charAt(i);
            if (ch >= '0' && ch <= '9') {
                stack.push((int)(ch - '0'));
            }
            else {
                int op1, op2;
                if (!stack.empty()) {
                    op2 = stack.pop();
                }
                else {
                    return -1;
                }
                if (!stack.empty()) {
                    op1 = stack.pop();
                }
                else {
                    return -1;
                }
                if (ch == '+') {
                    stack.push(op1 + op2);
                }
                else if (ch == '*') {
                    stack.push(op1 * op2);
                }
            }
        }
        if (stack.empty()) {
            return -1;
        }
        else {
            return stack.pop();
        }
    }

    public static void main(String[] args) {
        int[] A = {1, 3, 2, 1};
        int[]B = {4,2,5,3,2};
        System.out.println(commonMin(A, B));
        System.out.println(commonMinHash(A, B));

        System.out.println(RPN("13+62*7+*"));
        System.out.println(RPN("11++"));
    }
}
