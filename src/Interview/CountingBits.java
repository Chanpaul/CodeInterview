package Interview;

import java.util.Arrays;

/**
 * Created by yongyangyu on 3/17/16.
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's
 * in their binary representation and return them as an array.
 *
 * Example:
 * For num = 5 you should return [0,1,1,2,1,2].
 *
 * Follow up:
 *
 * It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear
 * time O(n) /possibly in a single pass?
 * Space complexity should be O(n).
 */
public class CountingBits {
    public int[] countBits(int num) {
        int[] res = new int[num + 1];
        int pow2 = 0;
        for (int i = 1; i <= num; i ++) {
            if ((i & (i-1)) == 0) {
                res[i] = 1;
                pow2 = i;
            }
            else {
               res[i] = 1 + res[i - pow2];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int num = 12;
        CountingBits cbs = new CountingBits();
        System.out.println(Arrays.toString(cbs.countBits(num)));
    }
}

