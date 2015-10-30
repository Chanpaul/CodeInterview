package Interview;

/**
 * Created by yongyangyu on 10/30/15.
 */
public class BSTfromSortedArray {
    public TreeNode createBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return bst(nums, 0, nums.length - 1);
    }
    private TreeNode bst(int[] nums, int start, int end) {
        if (start > end) return null;
        int mid = start + (end-start) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = bst(nums, start, mid-1);
        root.right = bst(nums, mid+1, end);
        return root;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        BSTfromSortedArray bsa = new BSTfromSortedArray();
        TreeNode root = bsa.createBST(nums);
        root.printTree();
    }
}
