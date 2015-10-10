package Interview;
/**
 * Created by yongyangyu on 10/10/15.
 */
public class SearchSequenceIn2DArray {
    public boolean match(int[][] A, int[] seq) {
        boolean[][][] cache = new boolean[A.length][A[0].length][seq.length];
        for (int i = 0; i < A.length; i ++) {
            for (int j = 0; j < A[i].length; j ++) {
                if (helper(A, seq, i, j, 0, cache)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean helper(int[][] A, int[] seq, int i, int j, int len, boolean[][][] cache) {
        if (seq.length == len) return true;
        if (i < 0 || i >= A.length || j < 0 || j >= A[i].length || cache[i][j][len]) {
            return false;
        }
        if (A[i][j] == seq[len] && (helper(A, seq, i-1, j, len+1, cache) ||
                                    helper(A, seq, i+1, j, len+1, cache) ||
                                    helper(A, seq, i, j-1, len+1, cache) ||
                                    helper(A, seq, i, j+1, len+1, cache))) {
            return true;
        }
        // cache(i, j, len) means A[i][j] matches seq[len] failed
        cache[i][j][len] = true;
        return false;
    }

    public static void main(String[] args) {
        int[][] A = {{1, 2, 3},
                     {3, 4, 5},
                     {5, 6, 7}};
        int[] seq = {1,2,4,3};
        SearchSequenceIn2DArray search = new SearchSequenceIn2DArray();
        System.out.println(search.match(A, seq));
    }
}
