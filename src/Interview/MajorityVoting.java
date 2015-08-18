package Interview;

/**
 * Created by yongyangyu on 8/18/15.
 * The Boyer-Moore Vote Algorithm solves the majority vote problem in linear time [O(n)]
 * and constant memory [O(1)]. The majority vote problem is to determine in any given sequence
 * of choices whether there is a choice with more occurrences than all the others, and if so,
 * to determine this choice.
 */
public class MajorityVoting {
    public int majorityElement(int[] nums) {
        int candidate = nums[0], counter = 0;
        for (int x : nums) {
            if (counter == 0) {
                candidate = x;
                counter ++;
            }
            else {
                if (candidate == x) {
                    counter ++;
                }
                else {
                    counter --;
                }
            }
        }
        counter = 0;
        for (int x : nums) {
            if (x == candidate) counter ++;
        }
        if (counter >= (nums.length + 1) / 2) return candidate;
        return  - 1;
    }
}
