package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 8/22/15.
 * Permute an array according to a given permutation in O(n) time
 * and O(1) space.
 */
public class PermuteArray {
    public void ApplyPermute(char[] A, int[] perm) {
        if (A.length != perm.length) {
            System.err.println("Cannot apply permutation to an array of different length.");
            System.exit(1);
        }
        else {
            int idx = Integer.MIN_VALUE;
            for (int x : perm) {
                idx = Math.max(x, idx);
            }
            if (idx >= perm.length) {
                System.err.println("Permute array element out of bound!");
                System.exit(1);
            }
        }
        // decompose a permutation to a set of cyclic permutations
        for (int i = 0; i < perm.length; i ++) {
            if (perm[i] >= 0) {
                CyclicPermute(A, perm, i);
            }
        }
    }

    private void CyclicPermute(char[] A, int[] perm, int i) {
        char last = '\0', cur = '\0';
        do {
            // save next character in A
            if (last != '\0') {
                cur = last;
            }
            last = A[perm[i]];
            A[perm[i]] = (cur == '\0') ? A[i] : cur;
            int j = i;
            i = perm[i];
            perm[j] -= perm.length;
        }while(perm[i] >= 0);
    }

    public static void main(String[] args) {
        char[] A = {'A', 'B', 'C', 'D', 'E', 'F'};
        int[] perm = {3, 2, 1, 0, 5, 4};
        new PermuteArray().ApplyPermute(A, perm);
        System.out.println(Arrays.toString(A));
    }
}
