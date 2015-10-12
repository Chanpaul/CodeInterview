package Interview;

import java.util.List;

/**
 * Created by yongyangyu on 4/23/15.
 */
public class ListNode {
    int val;
    ListNode next;
    public ListNode (int x) {
        val = x;
        next = null;
    }

    public static void printListNodes(ListNode head) {
        if (head == null) {
            return;
        }
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        else if (l1 == null && l2 != null) {
            return l2;
        }
        else if (l1 != null && l2 == null) {
            return l1;
        }
        else {
            int carry = 0;
            ListNode res = null;
            ListNode curr = null;
            while (l1 != null && l2 != null) {
                int value = (l1.val + l2.val + carry) % 10;
                carry = (l1.val + l2.val + carry) / 10;
                if (res == null) {
                    res = new ListNode(value);
                    curr = res;
                }
                else {
                    curr.next = new ListNode(value);
                    curr = curr.next;
                }
                l1 = l1.next;
                l2 = l2.next;
            }
            while (l1 != null) {
                int value = (l1.val + carry) % 10;
                carry = (l1.val + carry) / 10;
                curr.next = new ListNode(value);
                curr = curr.next;
                l1 = l1.next;
            }
            while (l2 != null) {
                int value = (l2.val + carry) % 10;
                carry = (l2.val + carry) / 10;
                curr.next = new ListNode(value);
                curr = curr.next;
                l2 = l2.next;
            }
            if (carry != 0) {
                curr.next = new ListNode(carry);
            }
            return res;
        }
    }

    public static ListNode create(int[] arr) {
        ListNode head = new ListNode(arr[0]);
        ListNode cur = head;
        for (int i = 1; i < arr.length; i ++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
        return head;
    }

    // recursive version
    public static ListNode reverse(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    // Reverse a linked list from position m to n. Do it in-place and in one-pass.
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pivot = null;
        ListNode pre = dummy;
        ListNode curr = pre.next;
        ListNode next = curr.next;
        ListNode node = null;
        for (int i = 1; i <= n; i ++) {
            if (i <= m) {
                if (i == m) {
                    pivot = pre;
                    node = curr;
                }
                pre = curr;
                curr = next;
                next = next.next;
            }
            else {
                node.next = next;
                curr.next = pivot.next;
                pivot.next = curr;
                curr = next;
                if (next != null) {
                    next = next.next;
                }
            }
        }
        return dummy.next;
    }


    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, fast = head;
        while (n != 0) {
            fast = fast.next;
            n --;
        }
        while (fast != null) {
            pre = pre.next;
            fast = fast.next;
        }
        pre.next = pre.next.next;
        return dummy.next;
    }

    public static void main(String[] args) {
        int[] testArray = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = create(testArray);
        ListNode l1 = new ListNode(3);
        l1.next = new ListNode(7);
        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(2);
        printListNodes(head);
        System.out.println();
        printListNodes(addTwoNumbers(l1, l2));
        System.out.print('\n');
        printListNodes(reverse(head));
        System.out.println();
        head = create(testArray);
        printListNodes(removeNthFromEnd(head, 2));
    }
}
