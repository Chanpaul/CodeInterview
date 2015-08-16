package Interview;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by yongyangyu on 12/14/14.
 * Given an unsorted array, find the maximum difference between the successive
 * elements in its sorted form.
 *
 * Try to solve it in linear time/space.
 *
 * Return 0 if the array contains less than 2 elements.
 *
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit
 * signed integer range.
 */
public class MaxGap {
    public static int maximumGap(int[] num) {
        if (num == null || num.length < 2) {
            return 0;
        }
        int res = -1;
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int x : num) {
            min = Math.min(min, x);
            max = Math.max(max, x);
        }
        int d = (int)Math.ceil((max - min) * 1.0 / (num.length-1));
        int[] small = new int[num.length];
        int[] large = new int[num.length];
        for (int x : num) {
            int ind = (x - min) / d;
            if (small[ind] == 0) {
                small[ind] = x;
            } else {
                small[ind] = Math.min(small[ind], x);
            }
            if (large[ind] == 0) {
                large[ind] = x;
            } else {
                large[ind] = Math.max(large[ind], x);
            }
        }
        // compare in different buckets
        for (int i = 1; i < small.length; i ++) {
            if (small[i] == 0) {
                continue;
            }
            int tmp = -1;
            for (int j = i-1; j >= 0; j --) {
                if (large[j] != 0) {
                    tmp = large[j];
                    break;
                }

            }
            res = Math.max(res, small[i] - tmp);
        }
        return res;
    }

    public static int sortedGap(int[] num) {
        int[] copy = new int[num.length];
        for (int i = 0; i < num.length; i ++) {
            copy[i] = num[i];
        }
        Arrays.sort(copy);
        int res = -1;
        for (int i = 1; i < copy.length; i ++) {
            int tmp = copy[i] - copy[i-1];
            res = tmp > res ? tmp : res;
        }
        return res;
    }

    public static void main(String[] args) {
        List<Integer> arr = new LinkedList<Integer>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("data.dat"));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                for (String s: data) {
                    arr.add(Integer.parseInt(s));
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        int[] num = new int[arr.size()];
        int i = 0;
        for (int x : arr) {
            num[i ++] = x;
        }
        System.out.println("Sorting array:    " + sortedGap(num));
        System.out.println("linear algorithm: " + maximumGap(num));
    }
}
