package Interview;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by yongyangyu on 10/10/15.
 */
public class SearchSequenceIn2DArray {
    class CacheEntry {
        private final int i;
        private final int j;
        private final int len;
        CacheEntry(int i, int j, int len) {
            this.i = i;
            this.j = j;
            this.len = len;
        }
        @Override
        public int hashCode() {
            return Arrays.hashCode(new int[]{i, j, len});
        }
        @Override
        public boolean equals(Object other) {
            if (other instanceof CacheEntry) {
                return i == ((CacheEntry) other).i &&
                       j == ((CacheEntry) other).j &&
                       len == ((CacheEntry) other).len;
            }
            else return false;
        }
    }

    public boolean match(int[][] A, int[] seq) {
        Set<CacheEntry> cache = new HashSet<>();
        for (int i = 0; i < A.length; i ++) {
            for (int j = 0; j < A[i].length; j ++) {
                if (helper(A, seq, i, j, 0, cache)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean helper(int[][] A, int[] seq, int i, int j, int len, Set<CacheEntry> cache) {
        if (seq.length == len) return true;
        if (i < 0 || i >= A.length || j < 0 || j >= A[i].length ||
            cache.contains(new CacheEntry(i, j, len))) {
            return false;
        }
        if (A[i][j] == seq[len] && (helper(A, seq, i-1, j, len+1, cache) ||
                                    helper(A, seq, i+1, j, len+1, cache) ||
                                    helper(A, seq, i, j-1, len+1, cache) ||
                                    helper(A, seq, i, j+1, len+1, cache))) {
            return true;
        }
        // cache(i, j, len) means A[i][j] matches seq[len] failed
        cache.add(new CacheEntry(i, j, len));
        return false;
    }

    public static void main(String[] args) {
        int[][] A = {{1, 2, 3},
                     {3, 4, 5},
                     {5, 6, 7}};
        int[] seq = {1,2,3,4};
        SearchSequenceIn2DArray search = new SearchSequenceIn2DArray();
        System.out.println(search.match(A, seq));
    }
}
