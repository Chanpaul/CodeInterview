package Interview;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by yongyangyu on 11/5/15.
 * For a binary tree, write an iterator class, which can
 * in-order traverse the binary tree, implement two operations, next() and hasnext().
 * E.g., if in-order traversing a binary tree will return 2 3 4 5 6, then first call next(),
 * it will return 2, call next() again, it will return 3, etc
 */
public class BinaryTreeIterator implements Iterator<Integer> {
    private Stack<TreeNode> stack = new Stack<>();

    private void pushLeftChildren(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            stack.push(curr);
            curr = curr.left;
        }
    }

    public BinaryTreeIterator(TreeNode root) {
        pushLeftChildren(root);
    }

    @Override
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    @Override
    public Integer next() {
        TreeNode res = stack.pop();
        pushLeftChildren(res.right);
        return res.val;
    }
}
