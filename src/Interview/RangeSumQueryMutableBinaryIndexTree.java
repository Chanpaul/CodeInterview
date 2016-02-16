package Interview;

/**
 * Created by yongyangyu on 2/16/16.
 * Fenwich tree (aka binary indexed tree) is a data structure that maintains a sequence of elements,
 * and is able to compute cumulative sum of any range of consecutive elements in O(log n) time.
 * Changing value of any single element needs O(log n) time as well.
 *
 * Every index in the cumulative sum array, say i, is responsible for the cumulative sum from the
 * index i to (i - (1 << r) + 1), where r represents the last 1 bit set in index i.
 */
public class RangeSumQueryMutableBinaryIndexTree {
    private int[] tree;
    private int[] nums;
    int size;

    public RangeSumQueryMutableBinaryIndexTree(int[] nums) {
        size = nums.length;
        this.nums = new int[size];
        tree = new int[size];
        for (int i = 0; i < size; i ++) {
            update(i, nums[i]);
        }
    }

    public void update(int i, int val) {
        int delta = val - nums[i];
        nums[i] = val;
        for (; i < size; i |= i + 1) tree[i] += delta;
    }

    public int sumRange(int i, int j) {
        return sum(j) - sum(i-1);
    }

    public int sum(int idx)  {
        int ans = 0;
        while (idx >= 0) {
            ans += tree[idx];
            idx &= idx + 1;
            idx --;
        }
        return ans;
    }
}
