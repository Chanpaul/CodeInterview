package Interview;

/**
 * Created by yongyangyu on 11/19/14.
 */
public class DistanceSortedArray {
    public static int getMinDist(int[] a1, int[] a2) {
        int i = 0, j = 0;
        int rval = Integer.MAX_VALUE;
        while (i < a1.length && j < a2.length) {
            if (a1[i] <= a2[j]) {
                rval = Math.min(rval, a2[j]-a1[i]);
                i ++;
            }
            else {
                rval = Math.min(rval, a1[i]-a2[j]);
                j ++;
            }
        }
        return rval;
    }

    public static void main(String [] args) {
        int[] a1 = {0,1,2,3,4};
        int[] a2 = {5,6,7,8,9};
        System.out.println(getMinDist(a1, a2));
    }
}
