package Interview;

/**
 * Created by yongyangyu on 1/6/15.
 */
public class MaxThreeIntProd {
    private static int[] bottomTwo(int[] arr) {
        int[] res = new int[2];
        res[0] = Integer.MAX_VALUE;
        res[1] = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;
        for (int x : arr) {
            if (x < res[0]) {
                int tmp = res[0];
                res[0] = x;
                res[1] = tmp;
            }
            else if (x < res[1]) {
                res[1] = x;
            }
            if (x < min && x != res[1]) {
                min = x;
            }
        }
        if (res[0] == res[1]) {
            res[1] = min;
        }
        return res;
    }
    private static int[] topThree(int[] arr) {
        int[] res = new int[3];
        res[0] = Integer.MIN_VALUE;
        res[1] = Integer.MIN_VALUE;
        res[2] = Integer.MIN_VALUE;
        for (int x : arr) {
            if (x > res[2]) {
                int t1 = res[2];
                int t2 = res[1];
                res[2] = x; res[1] = t1; res[0] = t2;
            }
            else if (x > res[1]) {
                int t1 = res[1];
                res[1] = x; res[0] = t1;
            }
            else if (x > res[0]) {
                res[0] = x;
            }
        }
        return res;
    }
    public static int getThreeProd(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int prod = 0;
        int[] bottom = bottomTwo(arr);
        int[] top = topThree(arr);
        prod = Math.max(bottom[0]*bottom[1], top[0]*top[1]);
        return prod * top[2];
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 2, 8, 0, -1, 3};
        int[] arr2 = {0, -1, 3, 100, -70, -5};
        System.out.println(getThreeProd(arr1));
        System.out.println(getThreeProd(arr2));
    }
}
