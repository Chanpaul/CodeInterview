package Interview;

/**
 * Created by yongyangyu on 8/25/15.
 * Reverse a singly list from start position to end position.
 * Suppose the head is at position 1.
 */
public class ReverseSublist {
    public static ListNode reverseFromTo(ListNode head, int start, int end) {
        int count = 1;
        ListNode dummyHead = new ListNode(0); // In case the first element is reversed
        dummyHead.next = head;
        ListNode cur = dummyHead.next;
        ListNode dummy = dummyHead;
        if (start == end) return dummyHead.next;
        while (count != start) { // dummy is one step slower than cur
            count ++;
            cur = cur.next;
            dummy = dummy.next;
        }
        while (count != end) {
            ListNode next = cur.next;
            cur.next = next.next;
            next.next = dummy.next;
            dummy.next = next;
            count ++;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] data = {1,2,3,4,5,6};
        ListNode head = ListNode.create(data);
        ListNode newHead = reverseFromTo(head, 1, 4);
        ListNode.printListNodes(newHead);
    }
}
