package Interview;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yongyangyu on 10/26/15.
 * Serialization is the process of converting a data structure or object into a
 * sequence of bits so that it can be stored in a file or memory buffer, or transmitted
 * across a network connection link to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree. There is no restriction
 * on how your serialization/deserialization algorithm should work. You just need to ensure
 * that a binary tree can be serialized to a string and this string can be deserialized to
 * the original tree structure.
 *
 * For example, you may serialize the following tree
 *      1
 *     / \
 *    2   3
 *       / \
 *      4   5
 * as "[1,2,3,null,null,4,5]".
 * Note: Do not use class member/global/static variables to store states.
 * Your serialize and deserialize algorithms should be stateless.
 */
public class BinaryTreeSerDeser {
    public String serialize(TreeNode root) {
        StringBuffer data = new StringBuffer();
        List<TreeNode> q = new ArrayList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode curr = q.remove(0);
            if (curr == null) {
                if (data.length() == 0) data.append("#");
                else data.append(",#");
            }
            else {
                if (data.length() == 0) data.append(curr.val);
                else data.append("," + curr.val);
                q.add(curr.left);
                q.add(curr.right);
            }
        }
        return data.toString();
    }

    public TreeNode deserialized(String data) {
        if (data.length() == 1)
            return null;
        String[] arr = data.split(",");
        int i = 0;
        TreeNode root = new TreeNode(Integer.parseInt(arr[i++]));
        List<TreeNode> q = new ArrayList<>();
        q.add(root);
        while (i < arr.length) {
            TreeNode curr = q.remove(0);
            if (!arr[i].equals("#")) {
                curr.left = new TreeNode(Integer.parseInt(arr[i]));
                q.add(curr.left);
            }
            i ++;
            if (!arr[i].equals("#")) {
                curr.right = new TreeNode((Integer.parseInt(arr[i])));
                q.add(curr.right);
            }
            i ++;
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(5);
        BinaryTreeSerDeser bsd = new BinaryTreeSerDeser();
        System.out.println(bsd.serialize(root));
        TreeNode res = bsd.deserialized(bsd.serialize(root));
        res.printTree();
    }
}
