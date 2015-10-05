package Interview;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yongyangyu on 6/25/15.
 * Given a sorted integer array without duplicates, return the summary of its ranges.

 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 */
public class SummaryRanges {
    public static List<String> summaryRanges(int[] nums) {
        List<String> summary = new LinkedList<>();
        if (nums == null || nums.length == 0) {
            return summary;
        }
        int start = nums[0];
        int len = 1, curr = start;
        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] == curr + 1) {
                len ++;
                curr = nums[i];
            }
            else {
                if (len == 1) {
                    summary.add(start + "");
                }
                else if (len > 1) {
                    summary.add(start + "->" + curr);
                }
                start = nums[i];
                curr = start;
                len = 1;
            }
        }
        if (curr == start) {
            summary.add(curr + "");
        }
        else {
            summary.add(start + "->" + curr);
        }
        return summary;
    }

    public static void main(String[] args) {
        int[] nums = {0,1,2,4,5,7};
        List<String> res = summaryRanges(nums);
        System.out.println(Arrays.toString(res.toArray()));
    }
}
