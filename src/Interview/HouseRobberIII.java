package Interview;

/**
 * Created by yongyangyu on 3/16/16.
 * The thief has found himself a new place for his thievery again. There is only one
 * entrance to this area, called the "root." Besides the root, each house has one and
 * only one parent house. After a tour, the smart thief realized that "all houses in this
 * place forms a binary tree". It will automatically contact the police if two directly-linked
 * houses were broken into on the same night.
 *
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 *
 *   Example 1:
 *         3
 *        / \
 *       2   3
 *        \   \
 *         3   1
 *   Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 *
 *   Example 2:
 *           3
 *          / \
 *         4   5
 *        / \   \
 *       1   3   1
 *   Maximum amount of money the thief can rob = 4 + 5 = 9.
 */
public class HouseRobberIII {
    public int rob(TreeNode root) {
        if (root == null) return 0;
        int[] res = traverse(root);
        return Math.max(res[0], res[1]);
    }

    // return [i, e], where i is the max value including current node,
    // e is the max value excluding current node
    private int[] traverse(TreeNode root) {
        if (root.left == null && root.right == null) {
           return new int[]{root.val, 0};
        }
        int[] lc = null, rc = null;
        if (root.left != null) {
            lc = traverse(root.left);
        }
        if (root.right != null) {
            rc = traverse(root.right);
        }
        int i = root.val + (lc == null ? 0 : lc[1]) + (rc == null ? 0 : rc[1]);
        int e = (lc == null ? 0 : Math.max(lc[0], lc[1])) +
                (rc == null ? 0 : Math.max(rc[0], rc[1]));
        return new int[]{i, e};
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.right = new TreeNode(1);
        HouseRobberIII hr3 = new HouseRobberIII();
        System.out.println(hr3.rob(root));
    }
}
