package Interview;

/**
 * Created by yongyangyu on 11/18/15.
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 *
 * The update(i, val) function modifies nums by updating the element at index i to val.
 * Example:
 * Given nums = [1, 3, 5]
 *
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * Note:
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange function is distributed evenly.
 *
 * Simple ideas such as creating a sum array to store the sums do not work for equal number of
 * updates and range queries. Because one of the operations will take O(n) time.
 * For the case when the number of updates equals to that of range queries, we should
 * use some data structure to achieve better performance, such as "segment trees" to achieve
 * O(log n) complexity for both operations.
 * For an array of size n, the corresponding segment tree will have n-1 internal nodes.
 * Thus, the total number of nodes is 2*n - 1. An array representation of binary tree can be used
 * for storing the segment tree, which is similar to that of binary search tree array representation.
 */
public class RangeSumQueryMutable {
    class SegmentTreeNode {
        int start, end;
        SegmentTreeNode left, right;
        int sum;

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            left = null;
            right = null;
            sum = 0;
        }
    }

    private SegmentTreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) return null;
        SegmentTreeNode res = new SegmentTreeNode(start, end);
        if (start == end) {
            res.sum = nums[start];
        }
        else {
            int mid = start + (end - start) / 2;
            res.left = buildTree(nums, start, mid);
            res.right = buildTree(nums, mid+1, end);
            res.sum = res.left.sum + res.right.sum;
        }
        return res;
    }

    private void update(SegmentTreeNode root, int pos, int val) {
        if (root.start == root.end) root.sum = val;
        else {
            int mid = root.start + (root.end - root.start) / 2;
            if (pos <= mid) {
                update(root.left, pos, val);
            }
            else {
                update(root.right, pos, val);
            }
            root.sum = root.left.sum + root.right.sum;
        }
    }

    private int sumRange(SegmentTreeNode root, int start, int end) {
        if (root.start == start && root.end == end) {
            return root.sum;
        }
        else {
            int mid = root.start + (root.end - root.start) / 2;
            if (end <= mid) { // range in 2nd half
                return sumRange(root.left, start, end);
            }
            else if (start >= mid + 1) { // range in 1st  half
                return sumRange(root.right, start, end);
            }
            else { // range across the 2 halves
                return sumRange(root.left, start, mid) +
                        sumRange(root.right, mid+1, end);
            }
        }
    }
    SegmentTreeNode root = null;

    public RangeSumQueryMutable(int[] nums) {
        root = buildTree(nums, 0, nums.length-1);
    }

    void update(int i, int val) {
        update(root, i, val);
    }

    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }

    public static void main(String[] args) {
        int[] nums = {9, -8};
        RangeSumQueryMutable rsqm = new RangeSumQueryMutable(nums);
        rsqm.update(0, 3);
        System.out.println(rsqm.sumRange(1,1));

    }
}
