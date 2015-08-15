package Interview;

/**
 * Created by yongyangyu on 11/17/14.
 * Find Min in a rotated sorted array
 */
public class FindMinRotSortArray {
    public static int findMin(int[] num) {
        int l = 0;
        int r = num.length - 1;
        if (num.length == 1) {
            return num[0];
        }
        while (l <= r) {
            int mid = l + (r-l) / 2;
            if (l == r) {
                return num[l];
            }
            else if (l == mid) {
                return Math.min(num[mid], num[r]);
            }
            else {
                // increasing sequence
                if (num[mid] < num[r]) {
                    r = mid;
                }
                else if (num[mid] > num[r]) {
                    l = mid;
                }
                else { // num[mid] == num[r]
                    if (num[l] != num[r]) {
                        r = mid;
                    }
                    else {
                        l ++;
                    }
                }
            }
        }
        return 0;
    }

    public static void main(String [] args) {
        int[] A = {10, 1, 10, 10, 10};
        int val = findMin(A);
        System.out.println(val);
    }
}
