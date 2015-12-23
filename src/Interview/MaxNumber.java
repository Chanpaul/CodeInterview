package Interview;


import java.util.*;

/**
 * Created by yongyangyu on 12/23/15.
 * Given two arrays of length m and n with digits 0-9 representing two numbers.
 * Create the maximum number of length k <= m + n from digits of the two.
 * The relative order of the digits from the same array must be preserved.
 * Return an array of the k digits. You should try to optimize your time and space complexity.
 *
 * Example 1:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * return [9, 8, 6, 5, 3]
 *
 * Example 2:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * return [6, 7, 6, 0, 4]
 *
 * Example 3:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * return [9, 8, 9]
 */
public class MaxNumber {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] res = new int[k];
        int[] tmp = new int[k];
        for (int i = 0; i <= Math.min(k, nums1.length); i ++) {
            int[] res1 = maxK(nums1, i);
            int[] res2 = maxK(nums2, k - i);
            if (res1 == null && res2 == null) continue;
            else {
                if (res1 != null && res2 != null) {
                    int p1 = 0, p2 = 0, p = 0;
                    while (p1 < res1.length && p2 < res2.length) {
                        if (res1[p1] > res2[p2]) {
                            tmp[p++] = res1[p1++];
                        }
                        else if (res1[p1] < res2[p2]) {
                            tmp[p++] = res2[p2++];
                        }
                        else { // have to look forward to choose a pointer
                            int t1 = p1+1, t2 = p2+1;
                            int flag = 0;
                            while (t1 < res1.length && t2 < res2.length) {
                                if (res1[t1] > res2[t2]) {
                                    flag = 1; break;
                                }
                                else if (res1[t1] < res2[t2]) {
                                    flag = 2; break;
                                }
                                else {
                                    t1++; t2++;
                                }
                            }
                            if (flag == 0) {
                                if (t1 < res1.length) {
                                    tmp[p++] = res1[p1++];
                                }
                                else tmp[p++] = res2[p2++];
                            }
                            else {
                                if (flag == 1) tmp[p++] = res1[p1++];
                                else tmp[p++] = res2[p2++];
                            }
                        }
                    }
                    while (p1 < res1.length) {
                        tmp[p++] = res1[p1++];
                    }
                    while (p2 < res2.length) {
                        tmp[p++] = res2[p2++];
                    }
                    if (compArray(res, tmp) < 0) {
                        System.arraycopy(tmp, 0, res, 0, res.length);
                    }
                }
                else {
                    if (res1 != null && res1.length >= k) {
                        for (int j = 0; j < res1.length; j ++) {
                            tmp[j] = res1[j];
                        }
                        if (compArray(res, tmp) < 0) {
                            System.arraycopy(tmp, 0, res, 0, res.length);
                        }
                    }
                    else if (res2 != null && res2.length >= k) {
                        for (int j = 0; j < res2.length; j ++) {
                            tmp[j] = res2[j];
                        }
                        if (compArray(res, tmp) < 0) {
                            System.arraycopy(tmp, 0, res, 0, res.length);
                        }
                    }
                }
            }
        }
        return res;
    }

    private int[] maxK(int[] nums, int k) {
        if (k == 0 || k > nums.length) return null;
        else if (k == nums.length) return nums;
        else {
            int[] res = new int[k];
            int p = k-1; // pointer for filling res[]
            Stack<Integer> stack = new Stack<>();
            int curr = maxIdx(nums, 0, nums.length-1);
            stack.push(curr);
            k--;
            while (k > 0) {
                if (p == res.length-1) { // nothing filled in yet
                    curr = maxIdx(nums, stack.peek()+1, nums.length-1);
                    if (curr >= 0) {
                        stack.push(curr);
                        k--;
                    }
                    else {
                        res[p--] = stack.pop();
                    }
                }
                else {
                    int start = stack.empty() ? 0 : stack.peek()+1;
                    curr = maxIdx(nums, start, res[p+1]-1);
                    if (curr >= 0) {
                        stack.push(curr);
                        k--;
                    }
                    else {
                        res[p--] = stack.pop();
                    }
                }
            }
            while (p >= 0) {
                res[p--] = stack.pop();
            }
            for (int i = 0; i < res.length; i ++) {
                res[i] = nums[res[i]];
            }
            return res;
        }
    }

    private int compArray(int[] arr1, int[] arr2) {
        for (int i = 0; i < arr1.length; i ++) {
            if (arr1[i] < arr2[i]) return -1;
            else if (arr1[i] > arr2[i]) return 1;
        }
        return 0;
    }

    private int maxIdx(int[] arr, int begin, int end) {
        int res = -1;
        for (int i = begin; i <= end; i ++) {
            if (res < 0) {
                res = i;
            }
            else if (arr[i] > arr[res]) {
                res = i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums1 = {2,5,6,4,4,0};
        int[] nums2 = {7,3,8,0,6,5,7,6,2};
        MaxNumber mn = new MaxNumber();
        System.out.println(Arrays.toString(mn.maxNumber(nums1, nums2, 15)));
    }
}
