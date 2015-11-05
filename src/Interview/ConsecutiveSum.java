package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 12/2/14.
 * Given an array of positive integers and a target integer, find if there is a consecutive subarray
 * that sums to the target. E.g, given {5,6,4,12}, findsum(10)=true, findsum(18)=false.
 */
public class ConsecutiveSum {
    public boolean findSum(int[] A, int sum) {
        if (A == null) return false;
        Set<Integer> subSum = new HashSet<>();
        int x = A[0];
        subSum.add(x);
        for (int i = 1; i < A.length; i ++) {
            x += A[i];
            subSum.add(x);
        }
        if (subSum.contains(sum)) return true;
        for (int elem: subSum) {
            if (subSum.contains(elem + sum)) return true;
        }
        return false;
    }

    public static int[] getIndex(int[] A, int sum) {
        HashMap<Integer, Integer> map = new HashMap<>();
        if (A == null) {
            return null;
        }
        int curr = 0;
        int [] res;
        for (int i = A.length - 1; i >= 0; i --) {
            curr += A[i];
            map.put(curr, i);
        }
        res = new int[2];
        if (map.containsKey(sum)) {
            res[0] = map.get(sum);
            res[1] = A.length - 1;
        }

        for (int key: map.keySet()) {
            if (map.containsKey(key - sum)) {
                res[0] = map.get(key);
                res[1] = map.get(key - sum) - 1;
                return res;
            }
        }
        return res;
    }

    public static void main(String [] args) {
        int[] A = {2,6,1,10,15,8};
        System.out.println(Arrays.toString(getIndex(A,23)));
        ConsecutiveSum cs = new ConsecutiveSum();
        System.out.println(cs.findSum(A, 17));
        System.out.println(cs.findSum(A, 15));
        System.out.println(cs.findSum(A, 28));
    }
}
