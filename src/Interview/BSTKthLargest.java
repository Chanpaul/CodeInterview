package Interview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yongyangyu on 11/12/15.
 * Sample input:
 *          7
 *         / \
 *        6   10
 *       /   / \
 *      2   8   12
 *     / \   \
 *    1   4   9
 * Given a binary search tree (i.e. For any subtree in the BST, all nodes on the left
 * are less than the root, and all nodes on the right are greater than or equal to the root.)
 * Find the k-th largest node in the tree. That is, when k = 1, find the largest node in the tree;
 * when k = 2, find the second largest; and so on.
 *
 */
public class BSTKthLargest {
    public TreeNode largest(TreeNode root, int k) {
        if (root == null) return null;
        List<TreeNode> res = new ArrayList<>();
        traverse_large(root, new int[]{0}, k, res);
        if (res.isEmpty()) return null;
        else return res.get(0);
    }

    private void traverse_large(TreeNode root, int[] curr, int k, List<TreeNode> res) {
        if (root != null && root.left == null && root.right == null) {
            curr[0] ++;
            if (curr[0] == k) res.add(root);
            return;
        }
        if (root.right != null) {
            traverse_large(root.right, curr, k, res);
        }
        curr[0] ++;
        if (curr[0] == k) res.add(root);
        if (root.left != null) {
            traverse_large(root.left, curr, k, res);
        }
    }

    public TreeNode smallest(TreeNode root, int k) {
        if (root == null) return null;
        List<TreeNode> res = new ArrayList<>();
        traverse_small(root, new int[]{0}, k, res);
        if (res.isEmpty()) return null;
        else return res.get(0);
    }

    private void traverse_small(TreeNode root, int[] curr, int k, List<TreeNode> res) {
        if (root != null && root.left == null && root.right == null) {
            curr[0] ++;
            if (curr[0] == k) res.add(root);
            return;
        }
        if (root.left != null) {
            traverse_small(root.left, curr, k, res);
        }
        curr[0] ++;
        if (curr[0] == k) res.add(root);
        if (root.right != null) {
            traverse_small(root.right, curr, k, res);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(6);
        root.right = new TreeNode(10);
        root.left.left = new TreeNode(2);
        root.left.left.left = new TreeNode(1);
        root.left.left.right = new TreeNode(4);
        root.right.left = new TreeNode(8);
        root.right.right = new TreeNode(12);
        root.right.left.right = new TreeNode(9);
        BSTKthLargest bst = new BSTKthLargest();
        System.out.println(bst.largest(root, 5).val);
        System.out.println(bst.smallest(root, 7).val);
    }
}
