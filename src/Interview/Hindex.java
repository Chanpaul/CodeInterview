package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 9/3/15.
 * Given an array of citations (each citation is a non-negative integer) of a researcher,
 * write a function to compute the researcher's h-index.
 *
 * According to the definition of h-index on Wikipedia: "A scientist has index h if h of
 * his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each."
 *
 * For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total
 * and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers
 * with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3.
 *
 * Note: If there are several possible values for h, the maximum one is taken as the h-index.
 *
 */
public class Hindex {
    public int hIndex(int[] citations) {
        // sort the citation array in reverse order
        Arrays.sort(citations);
        int i = 0, j = citations.length - 1;
        while (i <= j) {
            int tmp = citations[i];
            citations[i] = citations[j];
            citations[j] = tmp;
            i ++; j --;
        }
        // h = max_i { min(f(i), i) }
        int h = 0;
        for (i = 0; i < citations.length; i ++) {
            h = Math.max(h, Math.min(i+1, citations[i]));
        }
        return h;
    }

    // input citations is sorted ascend
    public int hindexSorted(int[] citations, int lo, int hi) {
        if (lo > hi) return 0;
        int mid = lo + (hi - lo) / 2;
        int cur = Math.min(citations[mid], citations.length - mid);
        int tmp = Math.max(hindexSorted(citations, lo, mid - 1),
                hindexSorted(citations, mid + 1, hi));
        return Math.max(cur, tmp);
    }

    public static void main(String[] args) {
        int[] citations = {2,4,7,7,7};
        System.out.println(new Hindex().hindexSorted(citations, 0, citations.length - 1));
    }
}
