package Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yongyangyu on 8/16/15.
 * Given a binary tree, return all root-to-leaf paths.
 * For example, given the following binary tree:
 *    1
 *  /   \
 * 2     3
 *  \
 *   5
 * All root-to-leaf paths are:
 * ["1->2->5", "1->3"]
 */
public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        traverse(root, paths, "");
        return paths;
    }

    private void traverse(TreeNode root, List<String> paths, String cur) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            paths.add(cur + String.valueOf(root.val));
            return;
        }
        if (root.left != null) {
            traverse(root.left, paths, cur + String.valueOf(root.val) + "->");
        }
        if (root.right != null) {
            traverse(root.right, paths, cur + String.valueOf(root.val) + "->");
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        BinaryTreePaths bsp = new BinaryTreePaths();
        List<String> res = bsp.binaryTreePaths(root);
        System.out.println(Arrays.toString(res.toArray()));
    }
}
