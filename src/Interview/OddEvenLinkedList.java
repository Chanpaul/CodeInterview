package Interview;

/**
 * Created by yongyangyu on 1/16/16.
 * Given a singly linked list, group all odd nodes together followed by the even nodes.
 * Please note here we are talking about the node number and not the value in the nodes.
 *
 * You should try to do it in place. The program should run in O(1) space complexity and
 * O(nodes) time complexity.
 *
 * Example:
 * Given 1->2->3->4->5->NULL,
 * return 1->3->5->2->4->NULL.
 *
 * Note:
 * The relative order inside both the even and odd groups should remain as it was in the input.
 * The first node is considered odd, the second node even and so on ...
 */
public class OddEvenLinkedList {
    public ListNode oddEven(ListNode head) {
        if (head == null) return head;
        ListNode dummyOdd = new ListNode(0);
        ListNode dummyEven = new ListNode(0);
        ListNode oddCurr = dummyOdd;
        ListNode evenCurr = dummyEven;
        int cnt = 0;
        while (head != null) {
            cnt ++;
            if (cnt % 2 == 1) {
                oddCurr.next = head;
                oddCurr = oddCurr.next;
            }
            else {
                evenCurr.next = head;
                evenCurr = evenCurr.next;
            }
            head = head.next;
        }
        evenCurr.next = null;
        oddCurr.next = dummyEven.next;
        return dummyOdd.next;
    }

    public static void main(String[] args) {
        int[] data = {1,2,3,4,5,6};
        ListNode head = ListNode.create(data);
        OddEvenLinkedList oel = new OddEvenLinkedList();
        ListNode res = oel.oddEven(head);
        ListNode.printListNodes(res);
    }
}
