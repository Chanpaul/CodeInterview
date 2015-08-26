package Interview;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Created by yongyangyu on 8/25/15.
 * Queue with max API.
 */
public class QueueWithMax {
    private Deque<Integer> queue;
    private Deque<Integer> max;
    public QueueWithMax() {
        queue = new LinkedList<>();
        max = new ArrayDeque<>();
    }

    public int getMax() {
        if (max.isEmpty()) {
            throw new NoSuchElementException("Element not exist");
        }
        return max.peek();
    }

    public void enqueue(int x) {
        queue.addLast(x);
        if (max.isEmpty()) max.addLast(x);
        else {
            while (!max.isEmpty() && max.peek() < x) max.removeLast();
            max.addLast(x);
        }
    }

    public int dequeue() {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("Queue is empty!");
        }
        int res = queue.pollFirst();
        if (res == max.peek()) max.pollFirst();
        return res;
    }

    public static void main(String[] args) {
        QueueWithMax q = new QueueWithMax();
        q.enqueue(3);
        q.enqueue(1);
        q.enqueue(3);
        q.enqueue(2);
        q.enqueue(0);
        System.out.println(q.getMax());
        q.dequeue();
        q.dequeue();
        q.dequeue();
        System.out.println(q.getMax());
        q.enqueue(4);
        System.out.println(q.getMax());
    }
}
