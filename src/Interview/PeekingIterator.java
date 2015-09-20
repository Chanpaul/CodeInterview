package Interview;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by yongyangyu on 9/20/15.
 * Given an Iterator class interface with methods: next() and hasNext(),
 * design and implement a PeekingIterator that support the peek() operation -- it
 * essentially peek() at the element that will be returned by the next call to next().
 *
 * Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].
 *
 * Call next() gets you 1, the first element in the list.
 *
 * Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.
 *
 * You call next() the final time and it returns 3, the last element.
 * Calling hasNext() after that should return false.
 */
public class PeekingIterator implements Iterator<Integer> {
    private final Iterator<Integer> iter;
    private boolean isPeeked = false;
    private int peekValue = 0;
    public PeekingIterator(Iterator<Integer> iterator) {
        iter = iterator;
        isPeeked = false;
    }

    public Integer peek() {
        if (isPeeked) return peekValue;
        if (iter.hasNext()) {
            isPeeked = true;
            peekValue = iter.next();
        }
        else {
            isPeeked = false;
            peekValue = Integer.MIN_VALUE;
        }
        return peekValue;
    }

    @Override public Integer next() {
        if (isPeeked) {
            isPeeked = false;
            return peekValue;
        }
        return iter.next();
    }

    @Override public boolean hasNext() {
        if (isPeeked) return true;
        return iter.hasNext();
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        PeekingIterator piter = new PeekingIterator(list.iterator());
        System.out.println(piter.next());
        System.out.println(piter.peek());
        System.out.println(piter.next());
        System.out.println(piter.next());
        System.out.println(piter.hasNext());
    }
}
