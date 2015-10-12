package Interview;

/**
 * Created by yongyangyu on 10/12/15.
 * Test if a linked list has a cycle without extra space.
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && slow != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
            if (slow == fast && fast != null) {
                return true;
            }
        }
        return false;
    }

    // Given a linked list, return the node where the cycle begins.
    // If there is no cycle, return null.
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        boolean hasCycle = false;
        while (fast != null && slow != null) {
            slow = slow.next;
            fast = fast.next;
            if (fast != null) fast = fast.next;
            if (slow == fast && fast != null) {
                hasCycle = true;
                break;
            }
        }
        if (!hasCycle) return null;
        else {
            slow = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return fast;
        }
    }
}
