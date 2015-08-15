package Interview;

/**
 * Created by yongyangyu on 11/23/14.
 */
public class MaxGold {
    public static int getMaxGold(int[] A, int l, int r, boolean firstPlay) {
        if (l == r) {
            if (firstPlay) {
                return A[l];
            }
            else {
                return 0;
            }

        }
        int lMax = getMaxGold(A, l+1, r, !firstPlay);
        int rMax = getMaxGold(A, l, r-1, !firstPlay);
        if (firstPlay) {
            lMax += A[l];
            rMax += A[r];
        }
        return Math.max(lMax, rMax);
    }

    public static void main(String[] args) {
        int[] A = {2, 10, 6};
        System.out.println(getMaxGold(A, 0, A.length-1, true));
    }
}
