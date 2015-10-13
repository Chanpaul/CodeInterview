package Interview;

import java.util.*;

/**
 * Created by yongyangyu on 10/12/15.
 */
public class KWayMergeSort {
    class HeapNode {
        private final int val;
        private final int srcId;
        private final int idx;
        HeapNode(int val, int srcId, int idx) {
            this.val = val;
            this.srcId = srcId;
            this.idx = idx;
        }
    }

    public List<Integer> mergeSort(List<List<Integer>> input) {
        Comparator<HeapNode> comp = (a, b) -> {
            if (a.val < b.val) return -1;
            else if (a.val > b.val) return 1;
            return 0;
        };
        PriorityQueue<HeapNode> pq = new PriorityQueue<>(10, comp);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < input.size(); i ++) {
            List<Integer> elem = input.get(i);
            if (elem.size() > 0) {
                pq.add(new HeapNode(elem.get(0), i, 0));
            }
        }
        while (!pq.isEmpty()) {
            HeapNode root = pq.poll();
            result.add(root.val);
            int srcId = root.srcId, idx = root.idx;
            if (idx + 1 < input.get(srcId).size()) {
                pq.add(new HeapNode(input.get(srcId).get(idx+1), srcId, idx + 1));
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] e1 = {0,3,8,10};
        int[] e2 = {1,5,7,9};
        int[] e3 = {6,9,15,16};
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        List<Integer> list3 = new ArrayList<>();
        for (int x: e1) list1.add(x);
        for (int x: e2) list2.add(x);
        for (int x: e3) list3.add(x);
        List<List<Integer>> input = new ArrayList<>();
        input.add(list1); input.add(list2); input.add(list3);
        List<Integer> result = new KWayMergeSort().mergeSort(input);
        System.out.println(Arrays.toString(result.toArray()));
    }
}
