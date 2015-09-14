package Interview;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by yongyangyu on 9/13/15.
 * Write a program which takes as input a set of integers and returns the size of a largest
 * subset of integers in the array having the property that if two integers are in the subset,
 * then so are all integers between them.
 * Ex, input is [3,-2,7,9,8,1,2,0,-1,5,8]
 * then biggest contained range is [-2,-1,0,1,2,3], return 6.
 * O(n) time complexity since each element is added/removed only once.
 */
public class LongestContainedRange {
    public int rangeLength(int[] nums) {
        Set<Integer> hash = new HashSet<>();
        for (int x : nums) {
            hash.add(x);
        }
        int len = 0;
        while (!hash.isEmpty()) {
            Iterator<Integer> iter = hash.iterator();
            int cur = iter.next();
            int size = 1;
            int larger = cur + 1;
            int smaller = cur - 1;
            hash.remove(cur);
            while (hash.contains(larger)) {
                size ++;
                hash.remove(larger++);
            }
            while (hash.contains(smaller)) {
                size ++;
                hash.remove(smaller--);
            }
            len = Math.max(size, len);
        }
        return len;
    }

    public static void main(String[] args) {
        int[] nums = {3,-2,7,9,8,1,2,0,-1,5,8};
        LongestContainedRange lcr = new LongestContainedRange();
        System.out.println(lcr.rangeLength(nums));
    }
}
