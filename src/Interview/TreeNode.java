package Interview;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yongyangyu on 11/17/14.
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;
    public TreeNode(int val) {
        this.val = val;
        left = null;
        right = null;
    }

    public static void postOrder(TreeNode root, List<TreeNode> post) {
        if (root != null) {
            postOrder(root.left, post);
            postOrder(root.right, post);
            post.add(root);
        }
    }
    public static TreeNode reverseTree(TreeNode root) {
        TreeNode rval = root;
        List<TreeNode> post = new LinkedList<TreeNode>();
        postOrder(root, post);
        rval = post.remove(0);
        TreeNode tmp = rval;
        while (!post.isEmpty()) {
            tmp.left = post.remove(0);
            tmp.right = post.remove(0);
            tmp.left.left = null;
            tmp.left.right = null;
            tmp = tmp.right;
            tmp.left = null;
            tmp.right = null;
        }

        return rval;
    }
    public static TreeNode reverseTree2(TreeNode root) {
        TreeNode cur = root;
        TreeNode l = cur.left, r = cur.right;
        TreeNode ll, lr;
        while (l != null) {
            ll = l.left;
            lr = l.right;
            l.left = r;
            l.right = cur;
            cur = l;
            l = ll;
            r = lr;
        }
        root.left = null;
        root.right = null;
        return cur;
    }

    public void printTree() {
        List<TreeNode> q = new LinkedList<TreeNode>();
        List<TreeNode> curq = new LinkedList<TreeNode>();
        TreeNode root = this;
        q.add(root);
        while(!q.isEmpty()) {
            TreeNode curr = q.remove(0);
            System.out.print(curr.val);
            if (!q.isEmpty()) {
                System.out.print(" ");
            }
            if (curr.left != null) {
                curq.add(curr.left);
            }
            if (curr.right != null) {
                curq.add(curr.right);
            }
            if (q.isEmpty()) {
                System.out.print("#");
                q = curq;
                curq = new LinkedList<TreeNode>();
            }
        }
    }

    public static TreeNode buildBST(int start, int end, int[] A) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        TreeNode curr = new TreeNode(A[mid]);
        TreeNode left = buildBST(start, mid - 1, A);
        TreeNode right = buildBST(mid + 1, end, A);
        if (left != null) {
            curr.left = left;
        }
        if (right != null) {
            curr.right = right;
        }
        return curr;
    }

    /**
     * Given a complete binary tree, count the number of nodes.
     * In a complete binary tree every level, except possibly the last,
     * is completely filled, and all nodes in the last level are as far left
     * as possible. It can have between 1 and 2^h nodes inclusive at the last level h.
     * @param root
     * @return #nodes in the complete binary tree
     */
    public static int countNodes(TreeNode root) {
        int levelRightmost = countLevelRightmost(root, 0);
        int count = (int)Math.pow(2, levelRightmost+1) - 1;
        int deepest = countLevelLeftmost(root, 0);
        if (levelRightmost == deepest) {
            return count;
        }
        int lastLevelCnt = lastNode(root, deepest, 0, new StringBuilder());
        return count + lastLevelCnt;
    }

    private static int countLevelRightmost(TreeNode root, int level) {
        if (root == null) {
            return level - 1;
        }
        return countLevelRightmost(root.right, level + 1);
    }

    private static int countLevelLeftmost(TreeNode root, int level) {
        if (root == null) {
            return level - 1;
        }
        return countLevelLeftmost(root.left, level + 1);
    }

    private static int lastNode(TreeNode root, int target, int level, StringBuilder code) {
        if (target == level) {
            return Integer.parseInt(code.toString(), 2) + 1;
        }
        if (root == null && level < target) {
            return -1;
        }
        if (root.right != null) {
            int rightVal = lastNode(root.right, target, level + 1, code.append('1'));
            if (rightVal > 0) {
                return rightVal;
            }
            code.deleteCharAt(code.length() - 1);
        }
        if (root.left != null) {
            int leftVal = lastNode(root.left, target, level + 1, code.append('0'));
            if (leftVal > 0) {
                return leftVal;
            }
            code.deleteCharAt(code.length() - 1);
        }
        return 0;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return root;
        }
        invertTree(root.left);
        invertTree(root.right);
        TreeNode tmp = root.right;
        root.right = root.left;
        root.left = tmp;
        return root;
    }
    public static void main(String [] args) {
        /*Treenode root = new Treenode(1);
        root.left = new Treenode(2);
        root.right = new Treenode(3);
        Treenode p = root.left;
        p.left = new Treenode(4);
        p.right = new Treenode(5);
        p = p.left;
        p.left = new Treenode(6);
        p.right = new Treenode(7);
        root.printTree();
        System.out.println();
        reverseTree2(root).printTree();
        System.out.println();
        int[] A = {1,2,3,4,5};
        Treenode bst = buildBST(0, A.length-1, A);
        bst.printTree();*/
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        TreeNode p = root.left;
        p.left = new TreeNode(4);
        p.right = new TreeNode(5);
        p = root.right;
        p.left = new TreeNode(6);
        p.right = new TreeNode(7);
        p = root.left.left;
        p.left = new TreeNode(8);
        p.right = new TreeNode(9);
        System.out.println(countNodes(root));
        root.printTree();
        System.out.println();
        root.invertTree(root);
        root.printTree();
    }
}
