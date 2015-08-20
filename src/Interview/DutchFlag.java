package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 8/20/15.
 * Given an unsorted array and a pivot index,
 * re-arrange the array such that the elements smaller than pivot
 * come first, then equal, finally elements larger than pivot.
 */
public class DutchFlag {
    public void partition(int[] nums, int p) {
        int smaller = 0, larger = 1;
        swap(nums, 0, p); // move pivot to the first place
        int pivot = nums[0];
        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] > pivot) continue;
            else if (nums[i] == pivot) {
                swap(nums, larger++, i);
            }
            else {
                swap(nums, smaller++, i);
                swap(nums, larger++, i);
            }
        }
    }

    /*
     * NOTE: the in-place XOR version of swap does not always work for integers,
     * i.e., swap two equal integers will make one of them become zero!
     */
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {3,4,6,7,2,3,1,6,2};
        new DutchFlag().partition(nums, 2);
        System.out.println(Arrays.toString(nums));
    }
}
