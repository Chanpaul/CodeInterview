package Interview;

/**
 * Created by yongyangyu on 8/3/15.
 * 0-1 knapsack problem
 */
public class Knapsack {
    // let K(w) = maxmum value achievable with knapsack of capacity w
    public static int maxValueWithRepeat(int W, int[] weight, int[] value) {
        int[] K = new int[W + 1];
        for (int w = 1; w <= W; w ++) {
            int tmp = 0;
            for (int i = 0; i < weight.length; i ++) {
                if (weight[i] <= w) {
                    tmp = Math.max(tmp, K[w - weight[i]] + value[i]);
                }
            }
            K[w] = tmp;
        }
        return K[W];
    }

    // let K(w, j) = maximum value achievable using knapsack of capacity w and items 1,...,j
    public static int maxValueWithoutRepeat(int W, int[] weight, int[] value) {
        int[][] K = new int[W + 1][weight.length + 1];
        for (int j = 1; j < K[0].length; j ++) {
            for (int w = 1; w < K.length; w ++) {
                if (weight[j - 1] > w) {
                    K[w][j] = K[w][j - 1];
                }
                else {
                    K[w][j] = Math.max(K[w][j - 1], K[w - weight[j - 1]][j - 1] + value[j - 1]);
                }
            }
        }
        return K[W][weight.length];
    }

    public static void main(String[] args) {
        int W = 10;
        int[] weight = {6, 3, 4, 2};
        int[] value = {30, 14, 16, 9};
        System.out.println(Knapsack.maxValueWithRepeat(W, weight, value));   // 48
        System.out.println(Knapsack.maxValueWithoutRepeat(W, weight, value));// 46
    }
}
