package Interview;

/**
 * Created by yongyangyu on 9/15/15.
 * Sort a linked list in O(n log n) time using constant space complexity.
 */
public class MergeSortList {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = null;
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            pre = slow;
            fast = fast.next.next;
            slow = slow.next;
        }
        pre.next = null;
        return merge(sortList(slow), sortList(head));
    }

    private ListNode merge(ListNode h1, ListNode h2) {
        ListNode dummy = new ListNode(0), cur = dummy;
        while (h1 != null && h2 != null) {
            if (h1.val <= h2.val) {
                cur.next = h1;
                h1 = h1.next;
            }
            else {
                cur.next = h2;
                h2 = h2.next;
            }
            cur = cur.next;
        }
        if (h1 != null) {
            cur.next = h1;
            h1 = h1.next;
            cur = cur.next;
        }
        if (h2 != null) {
            cur.next = h2;
            h2 = h2.next;
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] nums = {2,1,3,5,2,1,9,6};
        ListNode head = ListNode.create(nums);
        head = new MergeSortList().sortList(head);
        ListNode.printListNodes(head);
    }
}
