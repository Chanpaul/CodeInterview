package Interview;

/**
 * Created by yongyangyu on 10/30/15.
 * Convert a binary tree into a circular doubly linked list.
 * The order of the elements in the linked list have to be the order of
 * elements you get when you do an in order traversal of the binary tree.
 */
public class BST2CircularList {
    public TreeNode bst2Circular(TreeNode root) {
        if (root == null) return null;
        TreeNode end = inOder(root, 0);
        while(end.right != null) // look for right end
            end = end.right;
        TreeNode begin = root;
        while (begin.left != null) { // look for left end
            begin = begin.left;
        }
        begin.left = end;
        end.right = begin;
        return begin;
    }
    // -1 left subtree, return right-most node
    // 1 right subtree, return left-most node
    private TreeNode inOder(TreeNode root, int choice) {
        if (root.left == null && root.right == null) return root;
        TreeNode n1 = inOder(root.left, -1);
        root.left = n1;
        n1.right = root;
        TreeNode n2 = inOder(root.right, 1);
        root.right = n2;
        n2.left = root;
        if (choice == -1) return n2;
        else if (choice == 1) return n1;
        else return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(9);
        BST2CircularList b2c = new BST2CircularList();
        TreeNode first = b2c.bst2Circular(root);
        TreeNode curr = first;
        System.out.println(curr.val);
        while (curr.right != first) {
            curr = curr.right;
            System.out.println(curr.val);
        }
    }
}
