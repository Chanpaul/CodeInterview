package Interview;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yongyangyu on 10/19/15.
 * Median is the middle value in an ordered integer list.
 * If the size of the list is even, there is no middle value.
 * So the median is the mean of the two middle value.
 *
 * Examples:
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5
 *
 * Design a data structure that supports the following two operations:
 *
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * For example:
 *
 * add(1)
 * add(2)
 * findMedian() -> 1.5
 * add(3)
 * findMedian() -> 2
 */
public class MedianFinder {
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        Comparator<Integer> minComp = (a, b) -> {
            if (a < b) return -1;
            else if (a == b) return 0;
            else return 1;
        };
        Comparator<Integer> maxComp = (a, b) -> {
            if (a > b) return -1;
            else if (a == b) return 0;
            else return 1;
        };
        minHeap = new PriorityQueue<>(10, minComp);
        maxHeap = new PriorityQueue<>(10, maxComp);
    }
    public void addNum(int num) {
        maxHeap.add(num);
        if (maxHeap.size() > minHeap.size()) {
            minHeap.add(maxHeap.poll());
        }
        else if (maxHeap.size() == minHeap.size()) {
            if (maxHeap.peek() > minHeap.peek()) {
                int big = maxHeap.poll();
                int small = minHeap.poll();
                maxHeap.add(small);
                minHeap.add(big);
            }
        }
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) * 1.0 / 2;
        }
        return minHeap.peek();
    }

    public static void main(String[] args) {
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println(mf.findMedian());
        mf.addNum(3);
        System.out.println(mf.findMedian());
    }
}
