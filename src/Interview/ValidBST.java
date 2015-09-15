package Interview;

/**
 * Created by yongyangyu on 9/15/15.
 * Given a binary tree, determine if it is a valid binary search tree (BST).
 *
 * Assume a BST is defined as follows:
 *
 * The left subtree of a node contains only nodes with keys less than the node's key.
 * The right subtree of a node contains only nodes with keys greater than the node's key.
 * Both the left and right subtrees must also be binary search trees.
 */
public class ValidBST {
    public boolean isValidBST(TreeNode root) {
        return isValid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // cannot use int for hi, since root.val may equal to Integer.MAX_VALUE
    private boolean isValid(TreeNode root, long lo, long hi) {
        if (root == null) return true;
        if (root.val < lo || root.val > hi) return false;
        return isValid(root.left, lo, root.val) && isValid(root.right, root.val, hi);
    }
}
