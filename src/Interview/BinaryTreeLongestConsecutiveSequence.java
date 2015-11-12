package Interview;

/**
 * Created by yongyangyu on 11/11/15.
 * Given a binary tree, find the length of the longest consecutive sequence path.
 *
 * The path refers to any sequence of nodes from some starting node to any node in
 * the tree along the parent-child connections. The longest consecutive path need to be
 * from parent to child (cannot be the reverse).
 *
 * For example,
 *
 *       1
 *        \
 *         3
 *        / \
 *       2   4
 *            \
 *             5
 * Longest consecutive sequence path is 3-4-5, so return 3.
 *
 *      2
 *       \
 *        3
 *       /
 *      2
 *     /
 *    1
 * Longest consecutive sequence path is 2-3,not3-2-1, so return 2.
 */
public class BinaryTreeLongestConsecutiveSequence {
    public int longestConsecutive(TreeNode root) {
        return longest(root, null, 0);
    }

    private int longest(TreeNode curr, TreeNode parent, int len) {
        if (curr == null) return len;
        len = (parent != null && curr.val == parent.val + 1) ? len + 1: 1;
        return Math.max(len, Math.max(longest(curr.left, curr, len),
                                      longest(curr.right, curr, len)));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);
        System.out.println(new BinaryTreeLongestConsecutiveSequence().longestConsecutive(root));
    }
}
