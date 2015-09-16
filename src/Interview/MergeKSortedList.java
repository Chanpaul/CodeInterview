package Interview;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by yongyangyu on 9/15/15.
 */
public class MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        ListNode dummy = new ListNode(0);
        Comparator<ListNode> comp = (n1, n2) -> {
            if (n1.val == n2.val) return 0;
            else if (n1.val < n2.val) return -1;
            else return 1;
        };
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, comp);
        ListNode cur = dummy;
        for (ListNode x : lists) {
            if (x != null) queue.add(x);
        }
        while (!queue.isEmpty()) {
            ListNode tmp = queue.poll();
            cur.next = tmp;
            cur = cur.next;
            if (tmp.next != null) {
                queue.add(tmp.next);
            }
        }
        cur.next = null;
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2,3};
        int[] arr2 = {2,3,4};
        int[] arr3 = {0,1,2,3,4};
        ListNode[] lists = new ListNode[] { ListNode.create(arr1),
                            ListNode.create(arr2), ListNode.create(arr3)};
        ListNode.printListNodes(new MergeKSortedList().mergeKLists(lists));
    }
}
