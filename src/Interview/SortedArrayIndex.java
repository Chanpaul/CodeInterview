package Interview;
import java.util.Arrays;

/**
 * Created by yongyangyu on 11/18/14.
 */
public class SortedArrayIndex {
    public static int[] find_range(int[] arr, int target) {
        int[] rval = new int[2];
        rval[0] = rval[1] = -1;
        int l = 0, r = arr.length-1;
        // binary search for the left
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (arr[mid] == target) {
                if (l == r) {
                    rval[0] = l;
                    break;
                }
                r = mid;
            }
            else if (arr[mid] > target) {
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        if (rval[0] != -1) {
            //binary search for the right
            l = rval[0];
            r = arr.length-1;
            while (l <= r) {
                int mid = l + (r-l)/2;
                if (arr[mid] == target) {
                    if (l == r){
                        rval[1] = r;
                        break;
                    }
                    l = mid;
                }
                else if (arr[mid] > target) {
                    r = mid - 1;
                }
                else {
                    l = mid + 1;
                }
            }
        }
        return rval;
    }

    public static void main(String[] args) {
        int [] arr = {0,2,3,3,3,10,10};
        int target1 = 3;
        int target2 = 6;
        System.out.println(Arrays.toString(find_range(arr, target1)));
        System.out.println(Arrays.toString(find_range(arr, target2)));
    }
}
