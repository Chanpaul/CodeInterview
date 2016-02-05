package Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yongyangyu on 2/5/16.
 * Given the preorder access sequence of a BST, output the post-order of the node.
 * Suppose the nodes are all distinct in the labels.
 *                         10
 *                        /  \
 *                       5   15
 *                      / \  / \
 *                     3  8 12  16
 *                       / \
 *                      6   9
 * Input: [10, 5, 3, 8, 6, 9, 15, 12, 16]
 * Ouput: [3, 6, 9, 8, 5, 12, 16, 15, 10]
 */
public class BSTPreorder2Postorder {
    public List<Integer> postOrder(int[] preOrder) {
        if (preOrder.length == 0) return new ArrayList<>();
        return buildTree(0, preOrder.length-1, preOrder);
    }

    private List<Integer> buildTree(int lo, int hi, int[] preOrder) {
        List<Integer> tree = new ArrayList<>();
        if (lo == hi) {
            tree.add(preOrder[lo]);
            return tree;
        }
        int i = lo+1;
        while (preOrder[i] < preOrder[lo]) i ++;
        List<Integer> left = buildTree(lo+1, i-1, preOrder);
        List<Integer> right = buildTree(i, hi, preOrder);
        tree.addAll(left);
        tree.addAll(right);
        tree.add(preOrder[lo]);
        return tree;
    }

    public static void main(String[] args) {
        int[] preOrder = {10, 5, 3, 8, 6, 9, 15, 12, 16};
        BSTPreorder2Postorder bst2post = new BSTPreorder2Postorder();
        System.out.println(Arrays.toString(bst2post.postOrder(preOrder).toArray()));
    }
}
