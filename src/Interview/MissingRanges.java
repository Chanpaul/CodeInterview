package Interview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by yongyangyu on 10/3/15.
 * Given a sorted integer array where the range of elements are [lower, upper] inclusive,
 * return its missing ranges.
 *
 * For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99,
 * return ["2", "4->49", "51->74", "76->99"].
 */
public class MissingRanges {
    public List<String> missing(int[] nums, int lower, int upper) {
        List<String> miss = new ArrayList<>();
        for (int i = 0; i < nums.length; i ++) {
            if (i == 0) {
                if (nums[i] > lower) {
                    if (nums[i] - lower == 1) miss.add(String.valueOf(lower));
                    else miss.add(lower + "->" + (nums[i] - 1));
                }
            }
            else { // compare nums[i] and nums[i-1]
                if (nums[i] - nums[i-1] == 1) continue;
                else if (nums[i] - nums[i-1] == 2) miss.add(String.valueOf(nums[i-1]+1));
                else {
                    miss.add((nums[i-1]+1) + "->" + (nums[i]-1));
                }
            }
        }
        if (nums[nums.length - 1] < upper) {
            if (upper - nums[nums.length - 1] == 1) miss.add(String.valueOf(upper));
            else miss.add((nums[nums.length - 1] + 1) + "->" + upper);
        }
        return miss;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 3, 50, 75};
        List<String> miss = new MissingRanges().missing(nums, -10, 100);
        System.out.println(Arrays.toString(miss.toArray()));
    }
}
