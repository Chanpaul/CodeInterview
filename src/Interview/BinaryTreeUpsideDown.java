package Interview;

/**
 * Created by yongyangyu on 10/3/15.
 * Given a binary tree where all the right nodes are either leaf nodes with
 * a sibling (a left node that shares the same parent node) or empty,
 *
 * Flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes.
 * Return the new root.
 *
 * For example:
 * Given a binary tree {1,2,3,4,5},
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 * return the root of the binary tree [4,5,2,#,#,3,1].
 *    4
 *   / \
 *  5   2
 *     / \
 *    3   1
 */
public class BinaryTreeUpsideDown {
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root.left == null && root.right == null) {
            return root;
        }
        TreeNode leftSubtree = upsideDownBinaryTree(root.left);
        root.left.left = root.right;
        root.left.right = root;
        root.left = null;
        root.right = null;
        return leftSubtree;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.printTree();
        System.out.println();
        TreeNode upsideDown = new BinaryTreeUpsideDown().upsideDownBinaryTree(root);
        upsideDown.printTree();
    }
}
