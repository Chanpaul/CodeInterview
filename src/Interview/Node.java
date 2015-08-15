package Interview;

/**
 * Created by yongyangyu on 11/20/14.
 */
public class Node {  // single linked list node
    public int val;
    public Node next;
    public Node (int val) {
        this.val = val;
        next = null;
    }

    public static Node reverse(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node pre = head;
        Node curr = head.next;
        Node next = curr.next;
        head.next = null;
        while (curr != null) {
            curr.next = pre;
            pre = curr;
            curr = next;
            if (next != null) {
                next = next.next;
            }
        }
        return pre;
    }

    public static Node reversePair(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node rval = head.next;
        Node pre = null;
        Node curr = head;
        Node next = curr.next;
        while (curr != null && next != null) {
            if (pre != null) {
                pre.next = next;
            }
            curr.next = next.next;
            next.next = curr;
            pre = curr;
            curr = curr.next;
            if (curr != null) {
                next = curr.next;
            }
        }
        return rval;
    }

    // return the node k distance away from the curr
    public static Node kNodesAway(Node curr, int k) {
        Node res = null;
        Node tmp = curr;
        while (tmp != null && k > 0) {
            tmp = tmp.next;
            k --;
        }
        if (k == 0) {
            res = tmp;
        }
        return res;
    }

    // reverse the linked list between head and tail
    public static Node reverse(Node head, Node tail) {
        if (head == tail) {
            return head;
        }
        Node curr = head.next;
        Node next = curr.next;
        Node pre = head;
        head.next = tail.next;
        while (curr != tail) {
            next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        curr.next = pre;
        return tail;
    }

    public static Node reverseEveryKNodes(Node head, int k) {
        int length = 0;
        Node curr = head;
        while (curr != null) {
            length ++;
            if (curr != null) {
                curr = curr.next;
            }
        }
        // if total size < k, return head
        if (length < k) {
            return head;
        }
        int times = length / k; // number of sublist to reverse
        int cpTimes = times;
        curr = head;
        Node rval = null;
        Node tail = null;
        Node tmp = null;
        Node lastHead = head;
        while(times > 0) {
            tail = Node.kNodesAway(curr, k-1);
            if (tail == null) {
                System.err.println("tail equals null error!");
                break;
            }
            if (curr == head) {
                rval = tail;
            }

            tmp = tail.next;
            Node.reverse(curr, tail);
            if (times != cpTimes) {
                lastHead.next = tail;
                lastHead = curr;
            }
            curr = tmp;
            times --;
        }
        return rval;
    }

    // reverse element between position m and position n
    public static Node reverseBetween(Node head, int m, int n) {
        Node pre = null;
        Node start = null;
        Node end = null;
        Node curr = head;
        int k = 1;
        while (k <= n) {
            if (k + 1 == m) {
                pre = curr;
            }
            if (k == m) {
                start = curr;
            }
            if (k == n) {
                end = curr;
            }
            curr = curr.next;
            k ++;
        }
        if (m != 1) {
            pre.next = reverse(start, end);
            return head;
        }
        else {
            // return proper node
            return reverse(start, end);
        }
    }

    public static void print(Node head){
        if (head == null) {
            return;
        }
        Node curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
    }

    public static void main(String [] args) {
        Node head = new Node(1);
        Node curr = head;
        for (int i = 2; i < 11; i ++) {
            curr.next = new Node(i);
            curr = curr.next;
        }
        print(head);
        System.out.println("\nAfter reverse:");
        print(reverse(head));
        ///////////////////
        head = new Node(1);
        curr = head;
        for (int i = 2; i < 11; i ++) {
            curr.next = new Node(i);
            curr = curr.next;
        }
        System.out.println("\nAfter pair-reverse:");
        print(reversePair(head));
        /////////////////
        int k = 2;
        head = new Node(1);
        curr = head;
        for (int i = 2; i < 11; i ++) {
            curr.next = new Node(i);
            curr = curr.next;
        }
        System.out.println("\nAfter reverse k = " + k + ": ");
        print(reverseEveryKNodes(head, k));
        /////////////////
        int m = 1, n = 5;
        head = new Node(1);
        curr = head;
        for (int i = 2; i < 11; i ++) {
            curr.next = new Node(i);
            curr = curr.next;
        }
        System.out.println("\nAfter reverse between m = " + m + ", n = " + n + ": ");
        print(reverseBetween(head, m, n));
    }
}
