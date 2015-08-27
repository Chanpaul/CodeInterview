package Interview;

/**
 * Created by yongyangyu on 8/26/15.
 * Given a binary tree, determine if it is height-balanced.
 * For this problem, a height-balanced binary tree is defined as a binary
 * tree in which the depth of the two subtrees of every node never differ by more than 1.
 */
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        boolean[] notBalance = {true};
        depth(root, notBalance);
        return notBalance[0];
    }

    private int depth(TreeNode root, boolean[] notBalance) {
        if (!notBalance[0]) return -1;
        if (root == null) return 0;
        if (root.left == null && root.right == null) {
            return 1;
        }
        int leftDepth = depth(root.left, notBalance);
        int rightDepth = depth(root.right, notBalance);
        if (Math.abs(leftDepth - rightDepth) > 1) {
            notBalance[0] = false;
            return -1;
        }
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode('A');
        root.left = new TreeNode('B');
        root.right = new TreeNode('K');
        root.left.left = new TreeNode('C');
        root.left.right = new TreeNode('H');
        root.right.left = new TreeNode('L');
        root.right.right = new TreeNode('O');
        root.left.left.left = new TreeNode('D');
        root.left.left.right = new TreeNode('G');
        root.left.right.left = new TreeNode('I');
        root.left.right.right = new TreeNode('J');
        root.right.left.left = new TreeNode('M');
        root.right.left.right = new TreeNode('N');
        root.left.left.left.left = new TreeNode('E');
        root.left.left.left.right = new TreeNode('F');
        BalancedBinaryTree bbt = new BalancedBinaryTree();
        System.out.println(bbt.isBalanced(root));
    }
}
