package Interview;

/**
 * Created by yongyangyu on 1/12/15.
 * Given a list of non negative integers, arrange them such that they form the largest number.
 *
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 *
 * Note: The result may be very large, so you need to return a string instead of an integer.
 */
import java.util.Comparator;
import java.util.PriorityQueue;

public class LargestNumber {
    /*
      * get the largest number representation given the numbers
      * in array num, e.g., if num = [3, 30, 34, 5, 9], return "9534330"
     **/
    class numComparator implements Comparator<Integer> {
        private int myCompare(String s1, String s2) {
            if (s1 != null && s2 == null) {
                return -1;
            }
            if (s1 == null && s2 != null) {
                return 1;
            }
            if (s1.equals(s2)) {
                return 0;
            }
            int i = 0;
            while (i < s1.length() && i < s2.length()) {
                if (s1.charAt(i) > s2.charAt(i)) {
                    return -1;
                }
                else if (s1.charAt(i) < s2.charAt(i)) {
                    return 1;
                }
                i ++;
            }
            // if one of the number is consumed, recursive compare the rest
            if (i == s1.length()) {
                return myCompare(s1, s2.substring(i, s2.length()));
            }
            if (i == s2.length()) {
                return myCompare(s1.substring(i, s1.length()), s2);
            }
            return 0;
        }

        @Override
        public int compare(Integer x, Integer y) {
            String s1 = x + "";
            String s2 = y + "";
            return myCompare(s1, s2);
        }
    }

    public String largestNumber(int[] num) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>(10, new numComparator());
        for (int x : num) {
            pq.add(x);
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()) {
            sb.append(pq.poll());
        }
        if (sb.charAt(0) == '0' && sb.charAt(sb.length() - 1) == '0') {
            return "0";
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] num = {824,938,1399,5607,6973,5703,9609,4398,8247};
        int[] num2 = {78,7879,7875};
        LargestNumber compute = new LargestNumber();
        System.out.println(compute.largestNumber(num));
    }
}
