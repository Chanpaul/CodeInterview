package Interview;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yongyangyu on 1/11/15.
 */
public class KSmallestSum {
    private static Comparator<Integer> minComp = (x, y) -> {
        if (x < y) return -1;
        else if (x > y) return 1;
        else return 0;
    };

    private static Comparator<Integer> maxComp = (x, y) -> {
        if (x > y) return -1;
        else if (x < y) return 1;
        else return 0;
    };

    public static int kmaxSum(int[] A, int k) {
        int sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, minComp); // minHeap
        if (A.length <= k) {
            for (int x : A) {
                sum += x;
            }
            return sum;
        }
        for (int x : A) {
            if (pq.size() < k) {
                pq.add(x);
            }
            else {
                if (x > pq.peek()) { // kick out any smaller element than current
                    pq.poll();
                    pq.add(x);
                }
            }
        }
        while(!pq.isEmpty()) {
            sum += pq.poll();
        }
        return sum;
    }

    public static int kminSum(int[] A, int k) {
        int sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(k, maxComp); // maxHeap
        if (A.length <= k) {
            for (int x : A) {
                sum += x;
            }
            return sum;
        }
        for (int x : A) {
            if (pq.size() < k) {
                pq.add(x);
            }
            else {
                if (x < pq.peek()) { // kick out any larger element than current
                    pq.poll();
                    pq.add(x);
                }
            }
        }
        while (!pq.isEmpty()) {
            sum += pq.poll();
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] A = {6,5,4,3,2,1};
        System.out.println(kminSum(A, 2));
        System.out.println(kmaxSum(A, 2));
    }
}
