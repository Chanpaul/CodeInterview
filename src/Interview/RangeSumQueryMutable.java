package Interview;

import java.util.Arrays;

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
    int[] segTree;
    int[] nums;

    public RangeSumQueryMutable(int[] nums) {
        int n = nums.length;
        this.nums = nums;
        if (n == 0) return;
        int h = (int)(Math.ceil(Math.log(n) / Math.log(2)));
        int size = (int)Math.pow(2, h+1) - 1; // the binary tree maybe incomplete
        segTree = new int[size]; // assign enough memory for the complete tree to hold all possible nodes
        buildSegmentTree(nums, 0, n-1, 0);
    }

    private void buildSegmentTree(int[] nums, int start, int end, int treePos) {
        if (start == end) { // base case for single element
            segTree[treePos] = nums[start];
            return;
        }
        int mid = start + (end-start)/2;
        buildSegmentTree(nums, start, mid, treePos*2+1);
        buildSegmentTree(nums, mid+1, end, treePos*2+2);
        segTree[treePos] = segTree[treePos*2+1] + segTree[treePos*2+2]; // set current position the sum of subtrees
    }

    void update(int i, int val) {
        int diff = val - nums[i];
        nums[i] = val;
        updateValue(0, nums.length-1, diff, i, 0); // update along the path to root
    }

    private void updateValue(int seg_start, int seg_end, int diff, int qi, int si) {
        if (qi < seg_start || qi > seg_end) return; // update position not in the range
        segTree[si] += diff;
        if (seg_start != seg_end) { // for internal nodes
            int mid = seg_start + (seg_end-seg_start) / 2;
            updateValue(seg_start, mid, diff, qi, 2*si + 1);
            updateValue(mid+1, seg_end, diff, qi, 2*si + 2);
        }
    }

    public int sumRange(int i, int j) {
        return getSum(0, nums.length-1, i, j, 0);
    }

    private int getSum(int seg_start, int seg_end, int qstart, int qend, int si) {
        if (qstart <= seg_start && qend >= seg_end) return segTree[si]; // if [qstart, qend] is within [seg_start, seg_end]
        if (qend < seg_start || qstart > seg_end) return 0; // otherwise
        int mid = seg_start + (seg_end-seg_start)/2;
        return getSum(seg_start, mid, qstart, qend, si*2 + 1) +
                getSum(mid+1, seg_end, qstart, qend, si*2 + 2);
    }

    public static void main(String[] args) {
        int[] nums = {9, -8};
        RangeSumQueryMutable rsqm = new RangeSumQueryMutable(nums);
        System.out.println(Arrays.toString(rsqm.segTree));
        rsqm.update(0, 3);
        System.out.println(Arrays.toString(rsqm.segTree));
        System.out.println(rsqm.sumRange(1,1));

    }
}
