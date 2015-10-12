package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 9/28/15.
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number,
 * find the duplicate one. (Pigeon hole principle)
 *
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n^2).
 * There is only one duplicate number in the array, but it could be repeated more than once.
 */
public class FindDuplicateNumber {
    // binary-search based solution
    public int find(int[] nums) {
        int len = nums.length, left = 1, right = len-1;
        while(left < right)
        {
            int mid = left + (right - left) / 2;
            int count = 0;
            for(int i = 0; i < len; ++i)
                if(nums[i] <= mid) ++count; // count LE elements
            if (count > mid) right = mid; // update left/right
            else left = mid + 1;
        }
        return left;
    }

    // slow-fast pointer based solution
    // the array nums can be viewed as a representation of a linked list
    public int find2(int[] nums) {
        int n = nums.length;
        int slow = nums[n - 1], fast = nums[slow - 1];
        while (slow != fast) {
            slow = nums[slow - 1];
            fast = nums[nums[fast - 1] - 1];
        }
        slow = n;
        // move slow to the starting position
        while (slow != fast) {
            slow = nums[slow - 1];
            fast = nums[fast - 1];
        }
        return slow;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,3};
        System.out.println(new FindDuplicateNumber().find2(nums));
        System.out.println(Arrays.toString(nums));
    }
}
