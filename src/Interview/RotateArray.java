package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 2/24/15.
 * Rotate an array of n elements to the right by k steps.
 */
public class RotateArray {
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void rotate(int[] nums, int k) {
        if (nums == null)
            return;
        int len = nums.length;
        if (len == 0) {
            return;
        }
        int shift = k % len;
        // swap last k elements with first k elements
        for (int i = 0; i < shift; i ++) {
            swap(nums, i, (len-shift+i) % len);
        }
        int t = shift;
        // right shift t times from shift-th position
        for (int j = 0; j < t; j ++) {
            int tmp = nums[len-1];
            for (int i = len-1; i > shift; i --) {
                nums[i] = nums[i-1];
            }
            nums[shift] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6};
        rotate(arr, 2);
        System.out.println(Arrays.toString(arr));
    }
}
