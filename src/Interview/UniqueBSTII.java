package Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yongyangyu on 10/9/15.
 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
 *
 * For example,
 * Given n = 3, your program should return all 5 unique BST's shown below.
 *
 *  1         3     3      2      1
 *   \       /     /      / \      \
 *    3     2     1      1   3      2
 *   /     /       \                 \
 *  2     1         2                 3
 */
public class UniqueBSTII {
    public List<TreeNode> generateTrees(int n) {
        int[] nodes = new int[n];
        for (int i = 0; i < nodes.length; i ++) {
            nodes[i] = i+1;
        }
        return buildTrees(nodes);
    }

    private List<TreeNode> buildTrees(int[] nodes) {
        List<TreeNode> res = new ArrayList<>();
        if (nodes.length == 0) {
            res.add(null);
            return res;
        }
        for (int i = 0; i < nodes.length; i ++) {
            for (TreeNode left : buildTrees(Arrays.copyOfRange(nodes, 0, i))) {
                for (TreeNode right: buildTrees(Arrays.copyOfRange(nodes, i+1, nodes.length))) {
                    TreeNode root = new TreeNode(nodes[i]);
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
