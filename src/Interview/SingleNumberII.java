package Interview;

/**
 * Created by yongyangyu on 8/17/15.
 * Given an array of integers, every element appears three times except for one. Find that single one.
 *
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 * Solution:
 * ones records the positions where 1 appears once; twos records the positions where 1 appears twice;
 * when the position is set in both ones and twos, set it to zero.
 */
public class SingleNumberII {
    public int singleNumber(int[] nums) {
        int ones = 0, twos = 0, threes = 0;
        for (int x : nums) {
            twos |= (ones & x);
            ones ^= x;
            threes = ~(ones & twos);
            ones &= threes;
            twos &= threes;
        }
        return ones;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,1,2,2,2,3,3,3,6};
        System.out.println(new SingleNumberII().singleNumber(nums));
    }
}
