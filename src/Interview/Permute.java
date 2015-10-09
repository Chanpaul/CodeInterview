package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 11/26/14.
 */
public class Permute {
    public static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static List<List<Integer>> permute(int[] A) {
        Set<List<Integer>> set = new HashSet<>();
        perm(A, 0, A.length-1, set);
        List<List<Integer>> res = new ArrayList<>();
        res.addAll(set);
        return res;
    }

    private static void perm(int[] A, int i, int n, Set<List<Integer>> res) {
        if (i == n) {
            List<Integer> cur = new ArrayList<>();
            for (int x : A) {
                cur.add(x);
            }
            res.add(cur);
            return;
        }
        for (int j = i; j <= n; j ++) {
            swap(A, i, j);
            perm(A, i+1, n, res);
            swap(A, i, j);
        }
    }

    public static int[] nextPerm(int[] A) {
        // look for the longest decreasing sub-array from behind
        if (A.length < 2) return new int[A.length];
        int k = A.length - 2;
        while (k >= 0 && A[k] > A[k+1]) {
            k--;
        }
        if (k == -1) {
            Arrays.sort(A);
            return A;
        }
        // find the smallest element larger than A[k]
        int j = k + 1;
        for (int i = k + 1; i < A.length; i ++) {
            if (A[i] < A[j] && A[i] > A[k]) j = i;
        }
        swap(A, k, j);
        Arrays.sort(A, k + 1, A.length);
        return A;
    }

    public static void main(String [] args) {
        int [] A = {2, 2, 3, 0};
        List<List<Integer>> res = permute(A);
        for (List x : res) {
            System.out.println(Arrays.toString(x.toArray()));
        }
        System.out.println("================");
        System.out.println(Arrays.toString(A));
        System.out.println("================");
        int [] P = {6,2,1,5,4,3,0};
        System.out.println(Arrays.toString(nextPerm(P)));
    }
}
