package Interview;

import java.util.Stack;
/**
 * Created by yongyangyu on 2/1/16.
 * One way to serialize a binary tree is to use pre-oder traversal. When we encounter a non-null node,
 * we record the node's value. If it is a null node, we record using a sentinel value such as #.
 *
 *                 _9_
 *                /   \
 *               3     2
 *              / \   / \
 *             4   1  #  6
 *            / \ / \   / \
 *            # # # #   # #
 *
 * For example, the above binary tree can be serialized to the string "9,3,4,#,#,1,#,#,2,#,6,#,#",
 * where # represents a null node.
 *
 * Given a string of comma separated values, verify whether it is a correct preorder traversal serialization of
 * a binary tree. Find an algorithm without reconstructing the tree.
 *
 * Each comma separated value in the string must be either an integer or a character '#' representing null pointer.
 *
 * You may assume that the input format is always valid, for example it could never contain two
 * consecutive commas such as "1,,3".
 *
 * Example 1:
 * "9,3,4,#,#,1,#,#,2,#,6,#,#"
 * Return true
 *
 * Example 2:
 * "1,#"
 * Return false
 *
 * Example 3:
 * "9,#,#,1"
 * Return false
 */
public class VerifyPreorderSerializationBinaryTree {
    public boolean isValidSerialization(String preorder) {
        String[] arr = preorder.split(",");
        Stack<String> stack = new Stack<>();
        int cnt = 0;
        for (String s : arr) {
            stack.push(s);
            if (s.equals("#")) {
                cnt ++;
                if (cnt == 2) {
                    stack.pop();
                    stack.pop();
                    if (stack.isEmpty()) return false;
                    else {
                        stack.pop();
                        cnt = 1;
                        while (!stack.isEmpty() && stack.peek().equals("#")) {
                            stack.pop();
                            if (stack.isEmpty()) return false;
                            stack.pop();
                        }
                        stack.push("#"); // do not push "#" until each element is checked
                    }
                }
            }
            else cnt = 0;
        }
        if (stack.size() == 1 && stack.peek().equals("#")) return true;
        return false;
    }

    public static void main(String[] args) {
        VerifyPreorderSerializationBinaryTree vpsb = new VerifyPreorderSerializationBinaryTree();
        String preorder = "9,3,4,#,#,1,#,#,2,#,6,#,#";
        System.out.println(vpsb.isValidSerialization(preorder));
    }
}
