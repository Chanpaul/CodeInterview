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
public class RotateSearch {
    public int search(int[] nums, int target) {
        if (nums == null) return -1;
        int lo = 0, hi = nums.length-1;
        while (lo <= hi) {
            int mid = lo + (hi-lo)/2;
            if (nums[mid] == target) return mid;
            if (nums[lo] <= nums[mid]) {
                if (nums[lo] <= target && target < nums[mid])
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }
            else {
                if (nums[mid] < target && target <= nums[hi]) {
                    lo = mid + 1;
                }
                else
                    hi = mid - 1;
            }
        }
        return -1;
    }
    public static boolean searchDup(int[] A, int target) {
        int l = 0; int r = A.length - 1;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (target == A[mid]) {
                return true;
            }
            if (A[l] == A[mid] || A[l] == A[r]) {
                l ++;
                continue;
            }
            if (A[mid] == A[r]) {
                r --;
                continue;
            }
            if (A[l] < A[mid]) {
                if (target > A[mid] || target < A[l]) {
                    l = mid + 1;
                }
                else {
                    r = mid - 1;
                }
            }
            else if (A[l] > A[mid]) {
                if (target >= A[l]) {
                    r = mid;
                }
                else if (target >= A[mid]) {
                    l = mid;
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
