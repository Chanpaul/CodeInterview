package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 8/29/15.
 * Given the traversal sequence of nodes in terms of pre-order, in-order, or post-order,
 * recover the original binary tree. Suppose all the elements are distinct.
 */
public class RecoverBinaryTree {
    public TreeNode recoverFromPreorderAndInorder(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>(); // recording position of elements in inorder traversal
        for (int i = 0; i < inorder.length; i ++) {
            inMap.put(inorder[i], i);
        }
        return buildFromPreorderInorder(preorder, 0, preorder.length - 1, 0, inorder.length - 1, inMap);
    }
    // preorder[preStart : preEnd] included and inorder[inStart, inEnd] included
    private TreeNode buildFromPreorderInorder(int[] preorder, int preStart, int preEnd,
                                              int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd) return null;
        int val = preorder[preStart];
        int inPos = inMap.get(val);
        int leftTreeSize = inPos - inStart;
        TreeNode left = buildFromPreorderInorder(preorder, preStart + 1, preStart + leftTreeSize,
                inStart, inPos - 1, inMap);
        TreeNode right = buildFromPreorderInorder(preorder, preStart + leftTreeSize + 1, preEnd,
                inPos + 1, inEnd, inMap);
        return new TreeNode(val, left, right);

    }

    public TreeNode recoverFromPostOrderAndInorder(int[] postorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i ++) {
            inMap.put(inorder[i], i);
        }
        int start = 0, end = postorder.length - 1;
        while (start < end) {
            int tmp = postorder[start];
            postorder[start] = postorder[end];
            postorder[end] = tmp;
            start ++;
            end --;
        }
        return buildFromPostorderInorder(postorder, 0, postorder.length - 1, 0, inorder.length - 1, inMap);
    }

    private TreeNode buildFromPostorderInorder(int[] postorder, int postStart, int postEnd,
                                               int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (postStart > postEnd || inStart > inEnd) return null;
        int val = postorder[postStart];
        int inPos = inMap.get(val);
        int rightTreeLen = inEnd - inPos;
        TreeNode right = buildFromPostorderInorder(postorder, postStart + 1, postEnd + rightTreeLen,
                inPos + 1, inEnd, inMap);
        TreeNode left = buildFromPostorderInorder(postorder, postStart + rightTreeLen + 1, postEnd,
                inStart, inPos - 1, inMap);
        return new TreeNode(val, left, right);
    }

    public static void main(String[] args) {
        int[] preorder = {1,2,4,5,6,7,3,8,9,10};
        int[] inorder = {4,5,2,7,6,1,3,8,10,9};
        TreeNode root = new RecoverBinaryTree().recoverFromPreorderAndInorder(preorder, inorder);
        int[] postorder = {5,4,7,6,2,10,9,8,3,1};
        List<TreeNode> post = new LinkedList<>();
        TreeNode.postOrder(root, post);
        int[] po = new int[post.size()];
        for (int i = 0; i < po.length; i ++) {
            po[i] = post.get(i).val;
        }
        System.out.println("For pre-order and in-order:");
        System.out.println(Arrays.hashCode(postorder));
        System.out.println(Arrays.hashCode(po));
        System.out.println("Now for post-order and in-order:");
        root = new RecoverBinaryTree().recoverFromPostOrderAndInorder(postorder, inorder);
        List<TreeNode> pre = new LinkedList<>();
        TreeNode.preOrder(root, pre);
        int[] pr = new int[pre.size()];
        for (int i = 0; i < pr.length; i ++) {
            pr[i] = pre.get(i).val;
        }
        System.out.println(Arrays.hashCode(preorder));
        System.out.println(Arrays.hashCode(pr));
    }
}
