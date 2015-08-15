package Interview;

/**
 * Created by yongyangyu on 4/23/15.
 */
public class RemoveLinkedListElements {
    public static ListNode removeElements(ListNode head, int val) {
        ListNode res = head;
        if (res == null) {
            return res;
        }
        while (head != null && head.val == val) {
            head = head.next;
        }
        if (head == null) {
            return head;
        }
        else {
            res = head;
            ListNode pre = head;
            ListNode curr = head.next;
            while (curr != null) {
                if (curr.val == val) {
                    pre.next = curr.next;
                    if (pre != null) {
                        curr = pre.next;
                    }
                    else {
                        curr = null;
                    }
                }
                else {
                    pre = pre.next;
                    curr = curr.next;
                }
            }

        }
        return res;
    }

    public static ListNode removeElementsByCreateNewList(ListNode head, int val) {
        ListNode res = null;
        ListNode curr = res;
        if (head == null) {
            return null;
        }
        while (head != null) {
            if (head.val == val) {
                head = head.next;
            }
            else {
                if (res == null) {
                    res = new ListNode(head.val);
                    curr = res;
                }
                else {
                    curr.next = new ListNode(head.val);
                    curr = curr.next;
                }
                head = head.next;
            }
        }
        return res;
    }
    public static void main(String[] args) {
        int[] testArray = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(testArray[0]);
        ListNode curr = head;
        for (int i = 1; i < testArray.length; i ++) {
            curr.next = new ListNode(testArray[i]);
            curr = curr.next;
        }
        ListNode res = removeElements(head, 6);
        ListNode.printListNodes(res);
        ListNode.printListNodes(removeElementsByCreateNewList(head, 6));
    }
}
