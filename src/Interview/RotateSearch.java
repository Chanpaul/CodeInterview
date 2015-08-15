package Interview;

/**
 * Created by yongyangyu on 12/6/14.
 */
public class RotateSearch {
    public static boolean search(int[] A, int target) {
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
        System.out.println(search(A, -6));
    }
}
