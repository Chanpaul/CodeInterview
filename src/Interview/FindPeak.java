package Interview;

/**
 * Created by yongyangyu on 12/7/14.
 */
public class FindPeak {
    public static int peak(int[] A, int l, int r) {
        if (A == null) {
            return -1;
        }
        if (A.length == 1) {
            return 0;
        }
        if (A[0] > A[1]) {
            return 0;
        }
        if (A[A.length-1] > A[A.length - 2]) {
            return A.length-1;
        }
        if (l >= r) {
            return -1;
        }
        if (l+1 == r) {
            return -1;
        }
        int mid = l + (r-l) / 2;
        if (A[mid] > A[mid - 1] && A[mid] > A[mid+1]) {
            return mid;
        }
        int left = peak(A, l, mid);
        int right = peak(A, mid, r);
        if (left != -1) {
            return left;
        }
        if (right != -1) {
            return right;
        }
        return -1;
    }

    public static void main(String[] args) {
        int [] A = {1,2,3,1};
        System.out.println(peak(A, 0, A.length-1));
    }
}
