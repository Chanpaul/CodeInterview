package Interview;

/**
 * Created by yongyangyu on 11/18/15.
 * We have an array arr[0 . . . n-1]. We should be able to efficiently find the
 * minimum value from index qs (query start) to qe (query end) where 0 <= qs <= qe <= n-1.
 * The array is static (elements are not deleted and inserted during the series of queries).
 *
 * This problem can be viewed as an application of the segment trees from the range sum query
 * mutable problem.
 */
public class RangeMinQuery {
    int[] segTree;
    int n;

    public RangeMinQuery(int[] nums) {
        n = nums.length;
        int h = (int)Math.ceil(Math.log(n) / Math.log(2));
        int size = (int)Math.pow(2, h+1);
        segTree = new int[size];
        buildSegmentTree(nums, 0, nums.length-1, 0);
    }

    private void buildSegmentTree(int[] nums, int start, int end, int treePos) {
        if (start == end) {
            segTree[treePos] = nums[start];
            return;
        }
        int mid = start + (end-start)/2;
        buildSegmentTree(nums, start, mid, 2*treePos+1);
        buildSegmentTree(nums, mid+1, end, 2*treePos+2);
        segTree[treePos] = Math.min(segTree[treePos*2+1], segTree[treePos*2+2]);
    }

    public int minValue(int i, int j) {
        return getMin(0, n-1, i, j, 0);
    }

    private int getMin(int seg_start, int seg_end, int qstart, int qend, int si) {
        if (qstart <= seg_start && seg_end <= qend) return segTree[si];
        if (qend < seg_start || qstart > seg_end) return Integer.MAX_VALUE;
        int mid = seg_start + (seg_end-seg_start)/2;
        return Math.min(getMin(seg_start, mid, qstart, qend, si*2+1),
                        getMin(mid+1, seg_end, qstart, qend, si*2+2));
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 2, 7, 9, 11};
        RangeMinQuery rmq = new RangeMinQuery(nums);
        System.out.println(rmq.minValue(0, 5));
    }
}
