package Interview;

/**
 * Created by yongyangyu on 7/10/15.
 * Given a singly linked list, determine if it is a palindrome.
 *
 * Follow up:
 * Could you do it in O(n) time and O(1) space?
 */
public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode start = getStart(head);
        ListNode cur = head;
        while (cur.next != start) {
            cur = cur.next;
        }
        ListNode r = reverse(start);
        cur.next = r;
        ListNode p = head;
        ListNode q = r;
        while (q != null) {
            if (p.val != q.val) {
                return false;
            }
            p = p.next;
            q = q.next;
        }
        return true;
    }

    private int length(ListNode head) {
        ListNode cur = head;
        int size = 0;
        while (cur != null) {
            size ++;
            cur = cur.next;
        }
        return size;
    }

    private ListNode getStart(ListNode head) {
        int len = length(head);
        int pos;
        if (len % 2 == 0) {
            pos = len / 2;
        }
        else {
            pos = len / 2 + 1;
        }
        ListNode cur = head;
        int size = 0;
        while (cur != null) {
            if (pos == size) {
                return cur;
            }
            size ++;
            cur = cur.next;
        }
        return null;
    }

    private ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode pre = null, cur = head, next = cur.next;
        while (next != null) {
            cur.next = pre;
            pre = cur;
            cur = next;
            next = cur.next;
        }
        return cur;
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2,3,4,5};
        int[] arr2 = {1,2,3,2,1};
        PalindromeLinkedList test = new PalindromeLinkedList();
        ListNode h1 = ListNode.create(arr1);
        ListNode h2 = ListNode.create(arr2);
        //ListNode.printListNodes(test.reverse(h1));
        System.out.println(test.isPalindrome(h1));
        System.out.println(test.isPalindrome(h2));
    }
}
