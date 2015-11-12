package Interview;

/**
 * Created by yongyangyu on 12/6/14.
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 *
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 *
 * You are given a target value to search. If found in the array return its index, otherwise return -1.
 *
 * You may assume no duplicate exists in the array.
 */
public class BinRotateSearch {
    public int search(int[] nums, int target) {
        if (nums == null) return -1;
        int lo = 0, hi = nums.length-1;
        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if (nums[mid] == target) return mid;
            if (nums[lo] <= nums[mid]) { // first half in increasing order
                if (nums[lo] <= target && target < nums[mid]) // in first half
                    hi = mid - 1;
                else // in second half
                    lo = mid + 1;
            }
            else { // second half in increasing order
                if (nums[mid] < target && target <= nums[hi]) { // in second half
                    lo = mid + 1;
                }
                else // in first half
                    hi = mid - 1;
            }
        }
        return -1;
    }
    public static boolean searchDup(int[] nums, int target) {
        int lo = 0; int hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if (target == nums[mid]) {
                return true;
            }
            if (nums[lo] == nums[mid] || nums[lo] == nums[hi]) {
                lo ++;
                continue;
            }
            if (nums[mid] == nums[hi]) {
                hi --;
                continue;
            }
            if (nums[lo] < nums[mid]) {
                if (target > nums[mid] || target < nums[lo]) {
                    lo = mid + 1;
                }
                else {
                    hi = mid - 1;
                }
            }
            else if (nums[lo] > nums[mid]) {
                if (target >= nums[lo]) {
                    hi = mid;
                }
                else if (target >= nums[mid]) {
                    hi = mid;
                }
                else {
                    return false;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] A = {10,10,10,-10,-10,-10,-10,-9,-9,-9,-9,-9,-9,-9,-8,-8,-8,-8,-8,-8,-8,-8,-7,-7,-7,-7,-6,-6,-6,-5,-5,-5,-4,-4,-4,-4,-3,-3,-2,-2,-2,-2,-2,-2,-2,-2,-1,-1,-1,-1,-1,0,0,0,0,0,0,0,1,1,1,1,2,2,2,2,2,2,2,3,3,3,4,4,4,5,5,5,5,6,6,6,7,7,7,7,7,8,8,8,8,9,9,9,9,9,9,9,10,10};
        System.out.println(searchDup(A, -6));
    }
}
