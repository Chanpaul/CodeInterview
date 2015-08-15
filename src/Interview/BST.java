package Interview;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by yongyangyu on 7/1/15.
 * Given a binary search tree, write a function kthSmallest to find the kth smallest 
 * element in it.

 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

 * Follow up:
 * What if the BST is modified (insert/delete operations) often and you need to find 
 * the kth smallest frequently? How would you optimize the kthSmallest routine?

 * Hint:

 * Try to utilize the property of a BST.
 * What if you could modify the BST node's structure?
 * The optimal runtime complexity is O(height of BST).
 */
public class BST {
    /*static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }*/

    public int kthSmallest(TreeNode root, int k) {
        List<Integer> res = new LinkedList<>();
        findKthSmallest(root, 0, k, res);
        return res.get(0);
    }

    private int findKthSmallest(TreeNode root, int father, int target, List<Integer> result) {
        if (!result.isEmpty()) {
            return -1;          // found the result
        }
        if (root == null) {
            return father;
        }
        int lchild = findKthSmallest(root.left, father, target, result);
        if (!result.isEmpty()) {
            return -1;
        }
        if (lchild + 1 == target) {
            result.add(root.val);
            return -1;
        }
        return findKthSmallest(root.right, lchild + 1, target, result);
    }

    public void inOrderPrint(TreeNode root) {
        if (root.left != null) {
            inOrderPrint(root.left);
        }
        System.out.print(root.val + ", ");
        if (root.right != null) {
            inOrderPrint(root.right);
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10); //{5,6,7,8,9,10,11,12,13,15,16,18,20}
        root.left = new TreeNode(7);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(8);
        root.left.left.left = new TreeNode(5);
        root.left.right.right = new TreeNode(9);
        root.right.left = new TreeNode(12);
        root.right.right = new TreeNode(18);
        root.right.left.left = new TreeNode(11);
        root.right.left.right = new TreeNode(13);
        root.right.right.left = new TreeNode(16);
        root.right.right.right = new TreeNode(20);
        BST bst = new BST();
        System.out.println(bst.kthSmallest(root, 11));
    }
}
